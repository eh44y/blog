package pj.backend.service.implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pj.backend.config.SecurityUtil;
import pj.backend.dto.request.board.PatchBoardRequestDto;
import pj.backend.dto.request.board.PostBoardRequestDto;
import pj.backend.dto.request.board.PostCommentRequestDto;
import pj.backend.dto.response.ResponseDto;
import pj.backend.dto.response.board.DeleteBoardResponseDto;
import pj.backend.dto.response.board.GetBoardResponseDto;
import pj.backend.dto.response.board.GetCommentListResponseDto;
import pj.backend.dto.response.board.GetFavoriteListResponseDto;
import pj.backend.dto.response.board.GetLatestBoardListResponseDto;
import pj.backend.dto.response.board.GetSearchBoardListResponseDto;
import pj.backend.dto.response.board.GetTop3BoardListResponseDto;
import pj.backend.dto.response.board.GetUserBoardListResponseDto;
import pj.backend.dto.response.board.IncreaseViewCountResponseDto;
import pj.backend.dto.response.board.PatchBoardResponseDto;
import pj.backend.dto.response.board.PostBoardResponseDto;
import pj.backend.dto.response.board.PostCommentResponseDto;
import pj.backend.dto.response.board.PutFavoriteResponseDto;
import pj.backend.entity.Board;
import pj.backend.entity.BoardListView;
import pj.backend.entity.Comment;
import pj.backend.entity.Favorite;
import pj.backend.entity.Image;
import pj.backend.entity.SearchLog;
import pj.backend.entity.User;
import pj.backend.repository.BoardListViewRepository;
import pj.backend.repository.BoardRepository;
import pj.backend.repository.CommentRepository;
import pj.backend.repository.FavoriteRepository;
import pj.backend.repository.ImageRepository;
import pj.backend.repository.SearchLogRepository;
import pj.backend.repository.UserRepository;
import pj.backend.repository.resultSet.GetBoardResultSet;
import pj.backend.repository.resultSet.GetCommentListResultSet;
import pj.backend.repository.resultSet.GetFavoriteListResultSet;
import pj.backend.service.BoardService;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

  private final BoardRepository boardRepository;
  private final BoardListViewRepository boardListViewRepository;
  private final CommentRepository commentRepository;
  private final FavoriteRepository favoriteRepository;
  private final ImageRepository imageRepository;
  private final SearchLogRepository searchLogRepository;
  private final UserRepository userRepository;
  

  @Override
  public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) {
    GetBoardResultSet resultSet = null;
    List<Image> images = new ArrayList<>();

    try {
      resultSet = boardRepository.getBoard(boardNumber);
      if (resultSet == null)
        return GetBoardResponseDto.notExistBoard();
      
      images = imageRepository.findByBoardNumber(boardNumber);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetBoardResponseDto.success(resultSet, images);
  } // getBoard

  @Override
  public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber) {

    List<GetFavoriteListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!existedBoard)
        return GetFavoriteListResponseDto.notExistBoard();

      resultSets = favoriteRepository.getFavoriteList(boardNumber);
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetFavoriteListResponseDto.success(resultSets);
  } // getFavoriteList

  @Override
  public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber) {

    List<GetCommentListResultSet> resultSets = new ArrayList<>();

    try {

      boolean existedBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!existedBoard)
        return GetCommentListResponseDto.notExistBoard();

      resultSets = commentRepository.getCommentList(boardNumber);
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetCommentListResponseDto.success(resultSets);
  } // getCommentList

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
    
      List<BoardListView> boardListViews = new ArrayList<>();

    try {
      boardListViews = boardListViewRepository.findByOrderByWriteDatetimeDesc();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetLatestBoardListResponseDto.success(boardListViews);
  } // getLatestBoardList

  @Override
  public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {

    List<BoardListView> boardListViews = new ArrayList<>();

    try {
      LocalDateTime beforeWeek = LocalDateTime.now().minus(7, ChronoUnit.DAYS);
      String sevenDaysAgo = beforeWeek.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

      boardListViews = boardListViewRepository
          .findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(
              sevenDaysAgo);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetTop3BoardListResponseDto.success(boardListViews);
  } // getTop3BoardList

  @Override
  public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {
    
    List<BoardListView> boardListViews = new ArrayList<>();

    try {
      boardListViews = boardListViewRepository.findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(searchWord, searchWord);

      SearchLog searchLog = new SearchLog(searchWord, preSearchWord, false);
      searchLogRepository.save(searchLog);

      boolean relation = preSearchWord != null;
      if (relation) {
        searchLog = new SearchLog(preSearchWord, searchWord, true);
        searchLogRepository.save(searchLog);
      }

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSearchBoardListResponseDto.success(boardListViews);
  } // getSearchBoardList

  @Override
  public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(int userId) {

    @SuppressWarnings("unused")
    User user = null;
    List<BoardListView> boardListViews = new ArrayList<>();

    try {
    
      user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
      boardListViews = boardListViewRepository.findByWriterUserIdOrderByWriteDatetimeDesc(userId);
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return GetUserBoardListResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserBoardListResponseDto.success(boardListViews);
  } // getUserBoardList

  @Override
  public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto requestDto) {
    User user = null;
    try {
      user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));

      Board board = new Board(requestDto, user.getId());
      boardRepository.save(board);

      int boardNumber = board.getBoardNumber();

      List<String> boardImageList = requestDto.getBoardImageList();
      List<Image> imageList = new ArrayList<>();

      for (String i : boardImageList) {
        Image image = new Image(boardNumber, i);
        imageList.add(image);
      }

      imageRepository.saveAll(imageList);
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PostBoardResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return PostBoardResponseDto.success();
  } // postBoard

  @Override
  public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto requestDto, Integer boardNumber) {
    User user = null;
    try {
      Board board = boardRepository.findByBoardNumber(boardNumber);
      if (board == null)
        return PostCommentResponseDto.notExistBoard();

      user = userRepository.findById(SecurityUtil.getCurrentUserId())
          .orElseThrow(() -> new RuntimeException("User not found"));

      Comment comment = new Comment(requestDto, boardNumber, user.getId());
      commentRepository.save(comment);

      board.increaseCommentCount();
      boardRepository.save(board);
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PostCommentResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PostCommentResponseDto.success();
  } // postComment

  @Override
  public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber) {
    User user = null;
    try {
      user = userRepository.findById(SecurityUtil.getCurrentUserId())
          .orElseThrow(() -> new RuntimeException("User not found"));

      Board board = boardRepository.findByBoardNumber(boardNumber);
      if (board == null)
        return PutFavoriteResponseDto.notExistBoard();

      Favorite favorite = favoriteRepository.findByBoardNumberAndUserId(boardNumber, user.getId());
      if (favorite == null) {
        favorite = new Favorite(user.getId(), boardNumber);
        favoriteRepository.save(favorite);
        board.increaseFavoriteCount();
      } else {
        favoriteRepository.delete(favorite);
        board.decreaseFavoriteCount();
      }

      boardRepository.save(board);

    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PutFavoriteResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PutFavoriteResponseDto.success();
  } // putFavorite

  @Override
  public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto requestDto, Integer boardNumber) {
    User user = null;
    try {
      user = userRepository.findById(SecurityUtil.getCurrentUserId())
          .orElseThrow(() -> new RuntimeException("User not found"));
      
      Board board = boardRepository.findByBoardNumber(boardNumber);
      if (board == null)
        return PatchBoardResponseDto.notExistBoard();

      Integer writerUserId = board.getUserId();
      boolean isWriter = writerUserId.equals(user.getId());
      if (!isWriter)
        return PatchBoardResponseDto.noPermission();

      board.patchBoard(requestDto);
      boardRepository.save(board);

      imageRepository.deleteByBoardNumber(boardNumber);
      List<String> boardImageList = requestDto.getBoardImageList();
      List<Image> images = new ArrayList<>();

      for (String boardImage : boardImageList) {
        Image image = new Image(boardNumber, boardImage);
        images.add(image);
      }

      imageRepository.saveAll(images);
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PatchBoardResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchBoardResponseDto.success();
  } // deleteBoard

  @Override
  public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber) {
    try {
      Board board = boardRepository.findByBoardNumber(boardNumber);

      if (board == null)
        return IncreaseViewCountResponseDto.notExistBoard();

      board.increaseViewCount();
      boardRepository.save(board);
      
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return IncreaseViewCountResponseDto.success();
  } // increaseViewCount

  @Override
  public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber) {
    User user = null;
    try {
      user = userRepository.findById(SecurityUtil.getCurrentUserId())
          .orElseThrow(() -> new RuntimeException("User not found"));
    
      Board board = boardRepository.findByBoardNumber(boardNumber);
      if (board == null)
        return DeleteBoardResponseDto.notExistBoard();
      
      Integer writerUserId = board.getUserId();
      boolean isWriter = writerUserId.equals(user.getId());
      if (!isWriter)
        return DeleteBoardResponseDto.noPermission();

      imageRepository.deleteByBoardNumber(boardNumber);
      commentRepository.deleteByBoardNumber(boardNumber);
      favoriteRepository.deleteByBoardNumber(boardNumber);
      boardRepository.delete(board);
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return DeleteBoardResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return DeleteBoardResponseDto.success();
  }


} // BoardServiceImplement

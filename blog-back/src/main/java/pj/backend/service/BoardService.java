package pj.backend.service;

import org.springframework.http.ResponseEntity;

import pj.backend.dto.request.board.PatchBoardRequestDto;
import pj.backend.dto.request.board.PostBoardRequestDto;
import pj.backend.dto.request.board.PostCommentRequestDto;
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

public interface BoardService {

  // get
  ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

  ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

  ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

  ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();

  ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();

  ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

  ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(int userId);

  // post
  ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto requestDto);

  ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto requestDto, Integer boardNumber);
  
  // put
  ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumbber);

  // patch
  ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

  ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto requestDto, Integer boardNumber);

  // delete
  ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber);

} // BoardService;

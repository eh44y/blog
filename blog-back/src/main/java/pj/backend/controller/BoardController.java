package pj.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import pj.backend.service.BoardService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/{boardNumber}")
  public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
    return response;
  } // getBoard

  @GetMapping("/{boardNumber}/favorite-list")
  public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardNumber);
    return response;
  } // getFavoriteList

  @GetMapping("/{boardNumber}/comment-list")
  public ResponseEntity<? super GetCommentListResponseDto> getCommentList(@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardNumber);
    return response;
  } // getCommentList

  @GetMapping("{boardNumber}/increase-view-count")
  public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount (@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.increaseViewCount(boardNumber);
    return response;
  } // increaseViewCount

  @GetMapping("/latest-list")
  public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
    ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
    return response;
  } // getLatestBoardList

  @GetMapping("/top-3")
  public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
    ResponseEntity<? super GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
    return response;
  } // getTop3BoardList

  @GetMapping(value = { "/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}" })
  public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(@PathVariable("searchWord") String searchWord, @PathVariable(value = "preSearchWord", required = false) String preSearchWord) {
    ResponseEntity<? super GetSearchBoardListResponseDto> response = boardService.getSearchBoardList(searchWord, preSearchWord);
    return response;
  } // getSearchBoardList

  @GetMapping("/user-board-list/{userId}")
  public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(@PathVariable("userId") Integer userId) {
    ResponseEntity<? super GetUserBoardListResponseDto> response = boardService.getUserBoardList(userId);
    return response;
  } // getUserBoardList

  @PostMapping("")
  public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody) {
    ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody);
    return response;
  } // postBoard

  @PostMapping("/{boardNumber}/comment")
  public ResponseEntity<? super PostCommentResponseDto> postComment(@RequestBody @Valid PostCommentRequestDto requestBody, @PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardNumber);
    return response;
  } // postComment

  @PutMapping("/{boardNumber}/favorite")
  public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber);
    return response;
  } // putFavorite

  @PatchMapping("/{boardNumber}")
  public ResponseEntity<? super PatchBoardResponseDto> patchBoard(@RequestBody @Valid PatchBoardRequestDto requestDto, @PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(requestDto, boardNumber);
    return response;
  } // patchBoard

  @DeleteMapping("/{boardNumber}")
  public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(@PathVariable("boardNumber") Integer boardNumber) {
    ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardNumber);
    return response;
  } // deleteBoard
  
} // BoardController

package pj.backend.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.BoardListItem;
import pj.backend.dto.response.ResponseDto;
import pj.backend.entity.BoardListView;

@Getter
public class GetSearchBoardListResponseDto extends ResponseDto {
  
  private List<BoardListItem> searchList;

  private GetSearchBoardListResponseDto(List<BoardListView> boardListViews) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.searchList = BoardListItem.getList(boardListViews);
  } // GetSearchBoardListResponseDto

  public static ResponseEntity<GetSearchBoardListResponseDto> success(List<BoardListView> boardListViews) {
    GetSearchBoardListResponseDto result = new GetSearchBoardListResponseDto(boardListViews);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success
} // GetSearchBoardListResponseDto

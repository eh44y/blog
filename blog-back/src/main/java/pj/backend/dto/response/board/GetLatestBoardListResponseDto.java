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
public class GetLatestBoardListResponseDto extends ResponseDto {

  private List<BoardListItem> latestList;

  private GetLatestBoardListResponseDto(List<BoardListView> boards) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.latestList = BoardListItem.getList(boards);
  } // GetLatestBoardListResponseDto

  public static ResponseEntity<GetLatestBoardListResponseDto> success(List<BoardListView> boardListViews) {
    GetLatestBoardListResponseDto result = new GetLatestBoardListResponseDto(boardListViews);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success
} // GetLatestBoardListResponseDto

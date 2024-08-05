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
public class GetTop3BoardListResponseDto extends ResponseDto {
  
  private List<BoardListItem> top3List;

  private GetTop3BoardListResponseDto(List<BoardListView> boardListViews) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.top3List = BoardListItem.getList(boardListViews);
  } // GetTop3BoardListResponseDto

  public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListView> boardListViews) {
    GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(boardListViews);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success
} // GetTop3BoardListResponseDto

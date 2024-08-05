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
public class GetUserBoardListResponseDto extends ResponseDto {
  
  private List<BoardListItem> userBoardList;

  private GetUserBoardListResponseDto(List<BoardListView> boardListViews) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.userBoardList = BoardListItem.getList(boardListViews);
  } // GetUserBoardListResponseDto

  public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListView> boardListViews) {
    GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(boardListViews);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistUser

} // GetUserBoardListResponseDto

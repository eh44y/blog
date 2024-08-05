package pj.backend.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.CommentListItem;
import pj.backend.dto.response.ResponseDto;
import pj.backend.repository.resultSet.GetCommentListResultSet;

@Getter
public class GetCommentListResponseDto extends ResponseDto {
  
  private List<CommentListItem> commentList;

  private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.commentList = CommentListItem.copyList(resultSets);
  } // GetCommentListResponseDto

  public static ResponseEntity<? super GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSet) {
    GetCommentListResponseDto result = new GetCommentListResponseDto(resultSet);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistBoard

} // GetCommentListResponseDto

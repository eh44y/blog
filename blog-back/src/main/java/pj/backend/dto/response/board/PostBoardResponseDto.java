package pj.backend.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class PostBoardResponseDto extends ResponseDto {
  
  private PostBoardResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // PostBoardResponseDto

  public static ResponseEntity<PostBoardResponseDto> success() {
    PostBoardResponseDto result = new PostBoardResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  } // notExistUser
} // PostBoardResponseDto

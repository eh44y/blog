package pj.backend.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {

  private PutFavoriteResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // PutFavoriteResponseDto

  public static ResponseEntity<PutFavoriteResponseDto> success() {
    PutFavoriteResponseDto result = new PutFavoriteResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistboard

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  } // notExistUser
} // PutFavoriteResponseDto

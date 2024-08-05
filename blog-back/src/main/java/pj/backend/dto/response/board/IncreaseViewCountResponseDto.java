package pj.backend.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

public class IncreaseViewCountResponseDto extends ResponseDto {
  
  private IncreaseViewCountResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // IncreaseViewCountResponseDto

  public static ResponseEntity<IncreaseViewCountResponseDto> success() {
    IncreaseViewCountResponseDto result = new IncreaseViewCountResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistBoard

} // IncreaseViewCountResponseDto
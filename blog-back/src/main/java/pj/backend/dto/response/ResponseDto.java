package pj.backend.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;

@Getter
@AllArgsConstructor
public class ResponseDto {
  private String code;
  private String message;

  public static ResponseEntity<ResponseDto> databaseError() {
    ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
  } // databaseError

  public static ResponseEntity<ResponseDto> validationFailed() {
    ResponseDto responseBody = new ResponseDto(ResponseCode.VAILDATION_FAILED, ResponseMessage.VAILDATION_FAILED);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
  } // validationFailed
} // ResponseDto

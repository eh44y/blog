package pj.backend.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class SignUpResponseDto extends ResponseDto {
  private SignUpResponseDto(String code, String message) {
    super(code, message);
  }

  private SignUpResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  }

  public static ResponseEntity<SignUpResponseDto> success() {
    SignUpResponseDto result = new SignUpResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  public static ResponseEntity<ResponseDto> duplicateEmail() {
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  public static ResponseEntity<ResponseDto> duplicateNickname() {
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }

  public static ResponseEntity<ResponseDto> duplicateTelNumber() {
    SignUpResponseDto result = new SignUpResponseDto(ResponseCode.DUPLICATE_TEL_NUMBER, ResponseMessage.DUPLICATE_TEL_NUMBER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
}

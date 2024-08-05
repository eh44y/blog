package pj.backend.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.TokenDto;
import pj.backend.dto.response.ResponseDto;

@Getter
public class SignInResponseDto extends ResponseDto {
  private TokenDto token;
  private int expirationTime;

  private SignInResponseDto(TokenDto token) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.token = token;
    this.expirationTime = 60 * 60 * 3; // 3시간
  }

  public static ResponseEntity<SignInResponseDto> success(TokenDto token) {
    SignInResponseDto result = new SignInResponseDto(token);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> signInFail() {
    ResponseDto result = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  } // signInFail
} // SignInResponseDto

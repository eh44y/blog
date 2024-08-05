package pj.backend.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {

  private PatchNicknameResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // PatchNicknameResponseDto

  public static ResponseEntity<PatchNicknameResponseDto> success() {
    PatchNicknameResponseDto result = new PatchNicknameResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistUser

  public static ResponseEntity<ResponseDto> duplicateNickname() {
    ResponseDto result = new ResponseDto(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // duplicateNickname
  
} // PatchNicknameResponseDto

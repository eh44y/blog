package pj.backend.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto {

  private PatchProfileImageResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // PatchProfileImageResponseDto

  public static ResponseEntity<PatchProfileImageResponseDto> success() {
    PatchProfileImageResponseDto result = new PatchProfileImageResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistuser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistUser
  
} // PatchProfileImageResponseDto

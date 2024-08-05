package pj.backend.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;
import pj.backend.entity.User;

@Getter
public class GetSignInUserResponseDto extends ResponseDto {
  private int userId;
  private String email;
  private String nickname;
  private String profileImege;

  private GetSignInUserResponseDto(User user) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.userId = user.getId();
    this.email = user.getEmail();
    this.nickname = user.getNickname();
    this.profileImege = user.getProfileImage();
  } // GetSignInUserResponseDto

  public static ResponseEntity<GetSignInUserResponseDto> success(User user) {
    GetSignInUserResponseDto result = new GetSignInUserResponseDto(user);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  } // notExistUser
} // GetSignInUserResponseDto
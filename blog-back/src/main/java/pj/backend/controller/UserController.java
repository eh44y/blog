package pj.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pj.backend.dto.request.ChangePasswordRequestDto;
import pj.backend.dto.request.UserRequestDto;
import pj.backend.dto.request.user.PatchNicknameRequestDto;
import pj.backend.dto.request.user.PatchProfileImageRequestDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.user.GetSignInUserResponseDto;
import pj.backend.dto.response.user.GetUserResponseDto;
import pj.backend.dto.response.user.PatchNicknameResponseDto;
import pj.backend.dto.response.user.PatchProfileImageResponseDto;
import pj.backend.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/{userId}")
  public ResponseEntity<? super GetUserResponseDto> getUser(@PathVariable("userId") Integer UserId) {
    ResponseEntity<? super GetUserResponseDto> response = userService.getUser(UserId);
    return response;
  } // getUser

  @GetMapping("/")
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser() {
    ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser();
    return response;
  } // getSignInUser

  @PatchMapping("/nickname")
  public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
      @RequestBody @Valid PatchNicknameRequestDto requestBody) {
    ResponseEntity<? super PatchNicknameResponseDto> response = userService.patchNickname(requestBody);
    return response;
  } // patchNickname

  @PatchMapping("/profile-image")
  public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
      @RequestBody @Valid PatchProfileImageRequestDto requestBody) {
    ResponseEntity<? super PatchProfileImageResponseDto> response = userService.patchProfileImage(requestBody);
    return response;
  } // patchNickname

  /* @GetMapping("/me")
  public ResponseEntity<UserResponseDto> getUserInfo() {
    UserResponseDto userInfoBySecurity = userService.getMyInfoBySecurity();
    System.out.println(userInfoBySecurity.getNickname());
    return ResponseEntity.ok(userInfoBySecurity);
  } // getUserInfo

  @PostMapping("/nickname")
  public ResponseEntity<UserResponseDto> setuserNickname(@RequestBody UserRequestDto requestDto) {
    return ResponseEntity.ok(userService.changeUserNickname(requestDto.getEmail(), requestDto.getNickname()));
  } // setuserNickname

  @PostMapping("/password")
  public ResponseEntity<UserResponseDto> setUserPassword(@RequestBody ChangePasswordRequestDto requestDto) {
    return ResponseEntity.ok(
        userService.changeUserPassword(requestDto.getEmail(), requestDto.getExPassword(), requestDto.getNewPassword()));
  } // setUserPassword */
} // UserController

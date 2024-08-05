package pj.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pj.backend.dto.TokenDto;
import pj.backend.dto.request.UserRequestDto;
import pj.backend.dto.request.auth.SignInRequestDto;
import pj.backend.dto.request.auth.SignUpRequestDto;
import pj.backend.dto.response.ResponseDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.auth.SignInResponseDto;
import pj.backend.dto.response.auth.SignUpResponseDto;
import pj.backend.service.AuthService;
import pj.backend.service.implement.AuthServiceImplement;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthService authService;

/*   @PostMapping("signup")
public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto requestDto) {
return ResponseEntity.ok(authService.signUp(requestDto));
} // signup */
   
  /* @PostMapping("login")
  public ResponseEntity<TokenDto> login(@RequestBody UserRequestDto requestDto) {
    return ResponseEntity.ok(authService.login(requestDto));
  } */

  @PostMapping("signup")
  public ResponseEntity<? super SignUpResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
    ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestDto);
    return response;
  } // signup

  @PostMapping("signin")
  public ResponseEntity<? super SignInResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestDto){
    ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestDto);
    return response;
  } // signin
  


} // AuthController

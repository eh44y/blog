package pj.backend.service;

import org.springframework.http.ResponseEntity;

import pj.backend.dto.request.UserRequestDto;
import pj.backend.dto.request.auth.SignInRequestDto;
import pj.backend.dto.request.auth.SignUpRequestDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.auth.SignInResponseDto;
import pj.backend.dto.response.auth.SignUpResponseDto;

public interface AuthService {

  /* UserResponseDto signUp(UserRequestDto requestDto); */

  ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto requestDto);

  ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto requestDto);
} // AuthService

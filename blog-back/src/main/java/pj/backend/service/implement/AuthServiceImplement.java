package pj.backend.service.implement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pj.backend.dto.TokenDto;
import pj.backend.dto.request.UserRequestDto;
import pj.backend.dto.request.auth.SignInRequestDto;
import pj.backend.dto.request.auth.SignUpRequestDto;
import pj.backend.dto.response.ResponseDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.auth.SignInResponseDto;
import pj.backend.dto.response.auth.SignUpResponseDto;
import pj.backend.entity.User;
import pj.backend.provider.JwtProvider;
import pj.backend.repository.UserRepository;
import pj.backend.service.AuthService;
import pj.backend.service.implement.AuthServiceImplement;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImplement implements AuthService{
  private final AuthenticationManagerBuilder managerBuilder;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtProvider jwtProvider;

  /* // 회원가입
  public UserResponseDto signUp(UserRequestDto requestDto) {
    if (userRepository.existsByEmail(requestDto.getEmail())) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다.");
    }
    if (userRepository.existsByNickname(requestDto.getNickname())) {
      throw new RuntimeException("이미 존재하는 닉네임입니다.");
    }
    if (userRepository.existsByTelNumber(requestDto.getTelNumber())) {
      throw new RuntimeException("이미 존재하는 핸드폰 번호입니다.");
    }
    User user = requestDto.toUser(passwordEncoder);
    return UserResponseDto.of(userRepository.save(user));
  } // signUp */

  // 회원가입
  @Override
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto requestDto) {
    try {
      if (userRepository.existsByEmail(requestDto.getEmail())) {
        return SignUpResponseDto.duplicateEmail();
      }

      if (userRepository.existsByNickname(requestDto.getNickname())) {
        return SignUpResponseDto.duplicateNickname();
      }

      if (userRepository.existsByTelNumber(requestDto.getTelNumber())) {
        return SignUpResponseDto.duplicateTelNumber();
      }

      User user = requestDto.toUser(passwordEncoder);
      userRepository.save(user);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return SignUpResponseDto.success();
  }

  /* // 로그인
  public TokenDto login(UserRequestDto requestDto) {
    UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
    Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
    return jwtProvider.createToken(authentication);
  } // login */

  // 로그인
  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto requestDto) {
    TokenDto token = null;
    try {
      Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
      if (user.isEmpty() || user == null)
        return SignInResponseDto.signInFail();
      boolean isMatched = passwordEncoder.matches(requestDto.getPassword(), user.get().getPassword());
      if (!isMatched)
        return SignInResponseDto.signInFail();
      UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
      Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
      token = jwtProvider.createToken(authentication);
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    return SignInResponseDto.success(token);
  } // logIn

} // AuthService

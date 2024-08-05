package pj.backend.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import pj.backend.config.SecurityUtil;
import pj.backend.dto.request.user.PatchNicknameRequestDto;
import pj.backend.dto.request.user.PatchProfileImageRequestDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.user.GetSignInUserResponseDto;
import pj.backend.dto.response.user.GetUserResponseDto;
import pj.backend.dto.response.user.PatchNicknameResponseDto;
import pj.backend.dto.response.user.PatchProfileImageResponseDto;
import pj.backend.entity.User;
import pj.backend.repository.UserRepository;

public interface UserService {

  ResponseEntity<? super GetUserResponseDto> getUser(int userId);

  ResponseEntity<? super GetSignInUserResponseDto> getSignInUser();
  
  ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto requestDto);

  ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto requestDto);

  /* public UserResponseDto getMyInfoBySecurity() {
    return userRepository.findById(SecurityUtil.getCurrentUserId())
      .map(UserResponseDto::of)
      .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
  } // getMyInfoBySecurity

  @Transactional
  public UserResponseDto changeUserNickname(String email, String nickname) {
    User user = userRepository.findById(SecurityUtil.getCurrentUserId())
        .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    user.setNickname(nickname);
    return UserResponseDto.of(userRepository.save(user));
  } // changeUserNickname

  @Transactional
  public UserResponseDto changeUserPassword(String email, String exPassword, String newPassword) {
    User user = userRepository.findById(SecurityUtil.getCurrentUserId())
        .orElseThrow(() -> new RuntimeException("로그인 유저 정보다 없습니다."));
    if (!passwordEncoder.matches(exPassword, user.getPassword())) {
      throw new RuntimeException("비밀번호가 맞지 않습니다.");
    }
    user.setPassword(passwordEncoder.encode(newPassword));
    return UserResponseDto.of(userRepository.save(user));
  } */
} // UserService

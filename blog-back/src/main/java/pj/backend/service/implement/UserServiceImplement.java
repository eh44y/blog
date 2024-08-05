package pj.backend.service.implement;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pj.backend.config.SecurityUtil;
import pj.backend.dto.request.user.PatchNicknameRequestDto;
import pj.backend.dto.request.user.PatchProfileImageRequestDto;
import pj.backend.dto.response.ResponseDto;
import pj.backend.dto.response.UserResponseDto;
import pj.backend.dto.response.user.GetSignInUserResponseDto;
import pj.backend.dto.response.user.GetUserResponseDto;
import pj.backend.dto.response.user.PatchNicknameResponseDto;
import pj.backend.dto.response.user.PatchProfileImageResponseDto;
import pj.backend.entity.User;
import pj.backend.repository.UserRepository;
import pj.backend.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  /* public UserResponseDto getMyInfoBySecurity() {
    return userRepository.findById(SecurityUtil.getCurrentUserId())
      .map(UserResponseDto::of)
      .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
  } // getMyInfoBySecurity */

  @Override
  public ResponseEntity<? super GetUserResponseDto> getUser(int userId) {

    User user = null;
    
    try {
      user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return GetUserResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetUserResponseDto.success(user);
  } // getUser
  
  @Override
  public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser() {
    User user = null;
    try {
      user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));
    } catch (RuntimeException runtimeException) {
      // 유저를 찾지 못했을 경우
      runtimeException.printStackTrace();
      return GetSignInUserResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }
    
    return GetSignInUserResponseDto.success(user);
  }

  @Override
  public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto requestDto) {
    User user = null;

    try {

      user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));
      
      String nickname = requestDto.getNickname();
      boolean existedNickname = userRepository.existsByNickname(nickname);
      if (existedNickname)
        return PatchNicknameResponseDto.duplicateNickname();

      user.setNickname(nickname);
      userRepository.save(user);
      
    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PatchNicknameResponseDto.notExistUser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchNicknameResponseDto.success();
  } // patchNickname

  @Override
  public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto requestDto) {
    User user = null;
    
    try {

      user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new RuntimeException("User not found"));
    
      String profileImage = requestDto.getProfileImage();

      user.setProfileImage(profileImage);
      userRepository.save(user);

    } catch (RuntimeException runtimeException) {
      runtimeException.printStackTrace();
      return PatchProfileImageResponseDto.notExistuser();
    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchProfileImageResponseDto.success();
  } // patchProfileImage

  
} // UserServiceImplement

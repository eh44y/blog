package pj.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
  private String email;
  private String nickname;

  public static UserResponseDto of(User user) {
    return UserResponseDto.builder()
      .email(user.getEmail())
      .nickname(user.getNickname())
      .build();
  }
} // UserResponseDto

package pj.backend.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.entity.Authority;
import pj.backend.entity.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(min = 8, max = 20)
  private String password;
  @NotBlank
  private String nickname;
  @NotBlank
  @Pattern(regexp =  "^[0-9]{11,13}$")
  private String telNumber;
  private String profileImage;
  @NotNull
  @AssertTrue
  /* private boolean agreedPersonal; */

  public User toUser(PasswordEncoder passwordEncoder) {
    return User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .nickname(nickname)
        .telNumber(telNumber)
        .profileImage(profileImage)
        .authority(Authority.ROLE_USER)
        /* .agreedPersonal(agreedPersonal) */
        .build();
  }
  
  // email, password 일치하는지 검증
  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  } // toAuthentication
} // UserRequestDto

package pj.backend.dto.request.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pj.backend.entity.Authority;
import pj.backend.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
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
  @NotNull
  @AssertTrue
  private boolean agreedPersonal;

   public User toUser(PasswordEncoder passwordEncoder) {
    return User.builder()
        .email(email)
        .password(passwordEncoder.encode(password))
        .nickname(nickname)
        .telNumber(telNumber)
        .authority(Authority.ROLE_USER)
        .agreedPersonal(agreedPersonal)
        .build();
  }
} // SignUpRequestDto

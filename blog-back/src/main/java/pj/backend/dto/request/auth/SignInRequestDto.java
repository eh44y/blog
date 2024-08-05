package pj.backend.dto.request.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDto {
  @NotBlank
  private String email;
  @NotBlank
  private String password;

  // email, password 일치하는지 검증
  public UsernamePasswordAuthenticationToken toAuthentication() {
    return new UsernamePasswordAuthenticationToken(email, password);
  } // toAuthentication
} // SignInRequestDto

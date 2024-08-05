package pj.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
@Builder
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String email;

  private String password;

  private String nickname;

  private String telNumber;
  
  private String profileImage;

  @Enumerated(EnumType.STRING)
  private Authority authority;

  private boolean agreedPersonal;

  public void setNickname(String nickname) {
    this.nickname = nickname;
  } // setNickname

  public void setProfileImage(String profileImage) {
    this.profileImage = profileImage;
  } // setProfileImage

  public void setPassword(String password) {
    this.password = password;
  } // setPassword

  /* @Builder
  public User(int id, String email, String password, String nickname, String telNumber, String profileImage,
      Authority authority) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.telNumber = telNumber;
    this.profileImage = profileImage;
    this.authority = authority;
  } */
} // User

package pj.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class Image {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int imageId;

  private int boardNumber;

  private String image;

  public Image(int boardNumber, String image) {
    this.boardNumber = boardNumber;
    this.image = image;
  } // Image
  
} // Image

package pj.backend.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.dto.request.board.PostCommentRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
@Table(name = "comment")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentNumber;

  private String content;

  private String writeDatetime;

  private int userId;

  private int boardNumber;

  public Comment(PostCommentRequestDto requestDto, Integer boardNumber, int userId) {

    LocalDateTime now = LocalDateTime.now();
    String writeDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    this.content = requestDto.getContent();
    this.writeDatetime = writeDateTime;
    this.userId = userId;
    this.boardNumber = boardNumber;
    
  } // comment

} // Comment

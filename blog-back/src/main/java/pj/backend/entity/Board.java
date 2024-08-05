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
import pj.backend.dto.request.board.PatchBoardRequestDto;
import pj.backend.dto.request.board.PostBoardRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "board")
@Table(name = "board")
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardNumber;

  private String title;

  private String content;

  private String writeDatetime;

  private int favoriteCount;

  private int commentCount;

  private int viewCount;

  private int userId;

  public Board(PostBoardRequestDto requestDto, int id) {

    LocalDateTime now = LocalDateTime.now();
    String writeDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
    this.writeDatetime = writeDateTime;
    this.favoriteCount = 0;
    this.commentCount = 0;
    this.viewCount = 0;
    this.userId = id;
    
  } // Board

  public void increaseViewCount() {
    this.viewCount++;
  } // increaseViewCount

  public void increaseFavoriteCount() {
    this.favoriteCount++;
  } // increaseFavoriteCount

  public void increaseCommentCount() {
    this.commentCount++;
  } // increaseCommentCount

  public void decreaseFavoriteCount() {
    this.favoriteCount--;
  } // decreaseFavoriteCount

  public void decreaseCommentCount() {
    this.commentCount--;
  } // decreaseCommentCount

  public void patchBoard(PatchBoardRequestDto requestDto) {
    this.title = requestDto.getTitle();
    this.content = requestDto.getContent();
  } // patchBoard

} // Board

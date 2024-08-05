package pj.backend.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;
import pj.backend.entity.Image;
import pj.backend.repository.resultSet.GetBoardResultSet;

@Getter
public class GetBoardResponseDto extends ResponseDto {

  private int boardNumber;

  private String title;

  private String content;

  private List<String> boardImageList;

  private String writeDatetime;

  private int userId;

  private String writerNickname;

  private String writerProfileImage;

  private GetBoardResponseDto(GetBoardResultSet resultSet, List<Image> images) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> boardImageList = new ArrayList<>();

    for(Image image : images) {
      boardImageList.add(image.getImage());
    }

    this.boardNumber = resultSet.getBoardNumber();
    this.title = resultSet.getTitle();
    this.content = resultSet.getContent();
    this.boardImageList = boardImageList;
    this.writeDatetime = resultSet.getWriteDatetime();
    this.userId = resultSet.getWriterUserId();
    this.writerNickname = resultSet.getWriterNickname();
    this.writerProfileImage = resultSet.getWriterProfileImage();
  } // GetBoardResponseDto

  public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<Image> images) {
    GetBoardResponseDto result = new GetBoardResponseDto(resultSet, images);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistBoard
} // GetBoardResponseDto

package pj.backend.dto.response.board;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;

@Getter
public class DeleteBoardResponseDto extends ResponseDto {

  private DeleteBoardResponseDto() {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
  } // DeleteBoardResponseDto

  public static ResponseEntity<? super DeleteBoardResponseDto> success() {
    DeleteBoardResponseDto result = new DeleteBoardResponseDto();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistBoard

  public static ResponseEntity<ResponseDto> notExistUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
  } // notExistUser

  public static ResponseEntity<ResponseDto> noPermission() {
    ResponseDto result = new ResponseDto(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
  } // noPermission
} // DeleteBoardResponseDto

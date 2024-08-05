package pj.backend.dto.response.board;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.FavoriteListItem;
import pj.backend.dto.response.ResponseDto;
import pj.backend.repository.resultSet.GetFavoriteListResultSet;

@Getter
public class GetFavoriteListResponseDto extends ResponseDto {
  
  private List<FavoriteListItem> favoriteList;

  private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    this.favoriteList = FavoriteListItem.copyList(resultSets);
  } // GetFavoriteListResponseDto

  public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets) {
    GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

  public static ResponseEntity<ResponseDto> notExistBoard() {
    ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  } // notExistBoard

} // GetFavoriteListResponseDto

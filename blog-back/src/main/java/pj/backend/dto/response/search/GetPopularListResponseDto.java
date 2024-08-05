package pj.backend.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;
import pj.backend.repository.resultSet.GetPopularListResultSet;

@Getter
public class GetPopularListResponseDto extends ResponseDto {

  private List<String> popularWordList;
  
  private GetPopularListResponseDto(List<GetPopularListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> popularWordList = new ArrayList<>();
    for(GetPopularListResultSet resultSet : resultSets) {
      String popularWord = resultSet.getSearchWord();
      popularWordList.add(popularWord);
    }

    this.popularWordList = popularWordList;
  } // GetPopularListResponseDto

  public static ResponseEntity<GetPopularListResponseDto> success(List<GetPopularListResultSet> resultSets) {
    GetPopularListResponseDto result = new GetPopularListResponseDto(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

} // GetPopularListResponseDto

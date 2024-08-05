package pj.backend.dto.response.search;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;
import pj.backend.common.ResponseCode;
import pj.backend.common.ResponseMessage;
import pj.backend.dto.response.ResponseDto;
import pj.backend.repository.resultSet.GetRelationListResultSet;

@Getter
public class  GetRelationListResponseDto extends ResponseDto {
  
  private List<String> relativeWordList;

  private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets) {
    super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

    List<String> relativeWordList = new ArrayList<>();
    for (GetRelationListResultSet resultSet : resultSets) {
      String relativeWord = resultSet.getSearchWord();
      relativeWordList.add(relativeWord);
    }

    this.relativeWordList = relativeWordList;
  } // GetRelationListResponseDto

  public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets) {
    GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  } // success

} // GetRelationListResponseDto

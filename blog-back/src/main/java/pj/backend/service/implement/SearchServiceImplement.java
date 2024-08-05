package pj.backend.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pj.backend.dto.response.ResponseDto;
import pj.backend.dto.response.search.GetPopularListResponseDto;
import pj.backend.dto.response.search.GetRelationListResponseDto;
import pj.backend.repository.SearchLogRepository;
import pj.backend.repository.resultSet.GetPopularListResultSet;
import pj.backend.repository.resultSet.GetRelationListResultSet;
import pj.backend.service.SearchService;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

  private final SearchLogRepository searchLogRepository;
  
  @Override
  public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

    List<GetPopularListResultSet> resultSets = new ArrayList<>();

    try {
      resultSets = searchLogRepository.getPopularList();

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetPopularListResponseDto.success(resultSets);
  }

  @Override
  public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

    List<GetRelationListResultSet> resultSets = new ArrayList<>();

    try {
      resultSets = searchLogRepository.getRelationList(searchWord);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetRelationListResponseDto.success(resultSets);
  } // getPopularList
  
} // SearchServiceImplement

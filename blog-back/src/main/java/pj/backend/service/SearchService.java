package pj.backend.service;

import org.springframework.http.ResponseEntity;

import pj.backend.dto.response.search.GetPopularListResponseDto;
import pj.backend.dto.response.search.GetRelationListResponseDto;

public interface SearchService {
  // get
  ResponseEntity<? super GetPopularListResponseDto> getPopularList();

  ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
} // SerchService

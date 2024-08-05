package pj.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pj.backend.dto.response.search.GetPopularListResponseDto;
import pj.backend.dto.response.search.GetRelationListResponseDto;
import pj.backend.service.SearchService;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {
  
  private final SearchService searchService;

  @GetMapping("/popular-list")
  public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
    ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
    return response;
  } // getPopularList

  @GetMapping("/{searchWord}/relation-list")
  public ResponseEntity<? super GetRelationListResponseDto> getRelationList(@PathVariable("searchWord") String searchWord) {
    ResponseEntity<? super GetRelationListResponseDto> response = searchService.getRelationList(searchWord);
    return response;
  } // getRelationList

} // SearchController

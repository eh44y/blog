import React, { useEffect, useState } from "react";
import "assets/css/search/Search.css";
import { useNavigate, useParams } from "react-router-dom";
import { BoardListItem } from "types/interface";
import BoardItem from "components/BoardItem";
import { SEARCH_PATH } from "constant/Path-name";
import Pagination from "components/pagination/Pagination";
import { GetRelationListRequest, GetSearchBoardListRequest } from "apis";
import {
  GetRelationListResponseDto,
  GetSearchBoardListResponseDto,
  ResponseDto,
} from "apis/response";
import { usePagination } from "hooks";

// component: Search
export default function Search() {
  // state: searchWord path variable 상태
  const { searchWord } = useParams();
  // state: 페이지네이션 관련 상태
  const {
    currentPage,
    setCurrentPage,
    currentSection,
    setCurrentSection,
    viewList,
    viewPageList,
    totalSection,
    setTotalList,
  } = usePagination<BoardListItem>(5);
  // state: 검색 게시물 개수 상태
  const [count, setCount] = useState<number>(0);
  // state: 이전 검색어 상태
  const [preSearchWord, setPreSearchWord] = useState<string | null>(null);
  // state: 관련 검색어 리스트 상태
  const [relationWordList, setRelationWordList] = useState<string[]>([]);

  // function: navigate
  const navigate = useNavigate();

  // function: get search board list response 처리
  const getSearchBoardListResponse = (
    responseBody: GetSearchBoardListResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) return;

    const { code } = responseBody;

    if (code === "DBE") alert("데이터베이스 오류입니다.");
    if (code !== "SU") return;

    if (!searchWord) return;
    const { searchList } = responseBody as GetSearchBoardListResponseDto;
    setTotalList(searchList);
    setCount(searchList.length);
    setPreSearchWord(searchWord);
  }; // getSearchBoardListResponse

  // function: get relation list response 처리
  const getRelationListResponse = (
    responseBody: GetRelationListResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) return;

    const { code } = responseBody;

    if (code === "DBE") alert("데이터베이스 오류입니다.");
    if (code !== "SU") return;

    if (!searchWord) return;
    const { relativeWordList } = responseBody as GetRelationListResponseDto;
    setRelationWordList(relativeWordList);
  }; // getRelationListResponse

  // event handler: 연관 검색어 클릭 이벤트
  const onRelationWordClickHandler = (word: string) => {
    navigate(SEARCH_PATH(word));
  }; // onRelationWordClickHandler

  // effect: search word 싱태 변경시 실행할 함수
  useEffect(() => {
    if (!searchWord) return;
    GetSearchBoardListRequest(searchWord, preSearchWord).then(
      getSearchBoardListResponse
    );
    GetRelationListRequest(searchWord).then(getRelationListResponse);
  }, [searchWord]);

  // render: User render
  if (!searchWord) return <></>;
  return (
    <div id="search-wrapper">
      <div className="search-container">
        <div className="search-title-area">
          <div className="search-title">
            <span className="search-title-emphasis">{searchWord}</span>
            {`에 대한 검색 결과 입니다.`}
          </div>
          <div className="search-count">{count}</div>
        </div>
        <div className="search-contents-area">
          {count === 0 ? (
            <div className="search-contents-nothing">
              {"검색 결과가 없습니다."}
            </div>
          ) : (
            <div className="search-contents">
              {viewList.map((boardListItem) => (
                <BoardItem boardListItem={boardListItem} />
              ))}
            </div>
          )}
          <div className="search-relation-area">
            <div className="search-relation-card">
              <div className="search-relation-card-container">
                <div className="search-relation-card-title">
                  {"관련 검색어"}
                </div>
                {relationWordList.length === 0 ? (
                  <div className="search-relation-card-contents-nothing">
                    {"관련 검색어가 없습니다."}
                  </div>
                ) : (
                  <div className="search-relation-card-contents">
                    {relationWordList.map((word) => (
                      <div
                        className="word-badge"
                        onClick={() => onRelationWordClickHandler(word)}
                      >
                        {word}
                      </div>
                    ))}
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
        <div className="search-pagination-area">
          {count !== 0 && (
            <Pagination
              currentPage={currentPage}
              currentSection={currentSection}
              setCurrentPage={setCurrentPage}
              setCurrentSection={setCurrentSection}
              viewPageList={viewPageList}
              totalSection={totalSection}
            />
          )}
        </div>
      </div>
    </div> // #search-wrapper
  );
} // Search

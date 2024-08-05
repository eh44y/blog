import React, { useEffect, useState } from "react";
import "assets/css/main/Main.css";
import Top3Item from "components/Top3Item";
import { BoardListItem } from "types/interface";
import top3BoardListMock from "mocks/top-3-board-list.mock";
import BoardItem from "components/BoardItem";
import latestBoardListMock from "mocks/latest-board-list.mock";
import Pagination from "components/pagination/Pagination";
import { useNavigate } from "react-router-dom";
import { SEARCH_PATH } from "constant/Path-name";
import {
  GetLatestBoardListRequest,
  GetPopularListRequest,
  GetTop3BoardListRequest,
} from "apis";
import {
  GetLatestBoardListResponseDto,
  GetPopularListResponseDto,
  GetTop3BoardListResponseDto,
  ResponseDto,
} from "apis/response";
import { usePagination } from "hooks";

// component: main
export default function Main() {
  // function: 네비게이터
  const navigate = useNavigate();

  // component: 메인 화면 상단
  const MainTop = () => {
    // state: 주간 top3 게시물 리스트 상태
    const [top3BoardList, setTop3BoardList] = useState<BoardListItem[]>([]);

    // function: get top 3 board list 처리
    const getTop3BoardListResponse = (
      responseBody: GetTop3BoardListResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) return;

      const { code } = responseBody;
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;

      const { top3List } = responseBody as GetTop3BoardListResponseDto;
      setTop3BoardList(top3List);
    }; // getTop3BoardListResponse

    // effect: 첫 마운트시 실행
    useEffect(() => {
      GetTop3BoardListRequest().then(getTop3BoardListResponse);
    }, [top3BoardList]);
    // render: 메인 화면 상단 render
    return (
      <div id="main-top-wrapper">
        <div className="main-top-container">
          <div className="main-top-intro">
            {"44y blog에서 \n다양한 이야기를 나눠보세요."}
          </div>
          <div className="main-top-contents-area">
            <div className="main-top-contents-title">{"주간 TOP 3 게시물"}</div>
            <div className="main-top-contents">
              {top3BoardList.map((top3ListItem) => (
                <Top3Item top3ListItem={top3ListItem} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }; // MainTop

  // component: 메인 화면 상단
  const MainBottom = () => {
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
    // state: 인기 검색어 리스트 상태
    const [popularWordList, setPopularWordList] = useState<string[]>([]);

    // function: get latest board list response 처리
    const getLatestBoardListResponse = (
      responseBody: GetLatestBoardListResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) return;

      const { code } = responseBody;
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;

      const { latestList } = responseBody as GetLatestBoardListResponseDto;
      setTotalList(latestList);
    }; // getLatestBoardListResponse

    // function: get popular list response 처리
    const getPopularListResponse = (
      responseBody: GetPopularListResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) return;

      const { code } = responseBody;
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;

      const { popularWordList } = responseBody as GetPopularListResponseDto;
      setPopularWordList(popularWordList);
    }; // getLatestBoardListResponse

    // event handler: 인기 검색어 클릭 이벤트 처리
    const onPopularWordClickHandler = (word: string) => {
      navigate(SEARCH_PATH(word));
    }; // onPopularWordClickHandler

    // effect: 첫 마운트 시 살행
    useEffect(() => {
      GetLatestBoardListRequest().then(getLatestBoardListResponse);
      GetPopularListRequest().then(getPopularListResponse);
    }, []);

    // render: 메인 화면 하단 render
    return (
      <div id="main-bottom-wrapper">
        <div className="main-bottom-container">
          <div className="main-bottom-title">{"최신 게시물"}</div>
          <div className="main-bottom-contents-area">
            <div className="main-bottom-current-contents">
              {viewList.map((boardListItem, i) => (
                <BoardItem key={i} boardListItem={boardListItem} />
              ))}
            </div>
            <div className="main-bottom-popular-area">
              <div className="main-bottom-popular-card">
                <div className="main-bottom-popular-card-area">
                  <div className="main-bottom-popular-card-title">
                    {"인기 검색어"}
                  </div>
                  <div className="main-bottom-popular-card-contents">
                    {popularWordList.map((word, i) => (
                      <div
                        key={i}
                        className="word-badge"
                        onClick={() => onPopularWordClickHandler(word)}
                      >
                        {word}
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div className="main-bottom-pagination-area">
            <Pagination
              currentPage={currentPage}
              currentSection={currentSection}
              setCurrentPage={setCurrentPage}
              setCurrentSection={setCurrentSection}
              viewPageList={viewPageList}
              totalSection={totalSection}
            />
          </div>
        </div>
      </div>
    );
  }; // MainBottom

  // render: Main render
  return (
    <>
      <MainTop />
      <MainBottom />
    </>
  );
} // Main
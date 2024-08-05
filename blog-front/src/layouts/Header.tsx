import React, {
  ChangeEvent,
  KeyboardEvent,
  useEffect,
  useRef,
  useState,
} from "react";
import "assets/css/header/Header.css";
import { useLocation, useParams, useNavigate } from "react-router-dom";
import {
  AUTH_PATH,
  BOARD_DETTAIL_PATH,
  BOARD_PATH,
  BOARD_UPDATE_PATH,
  BOARD_WRITE_PATH,
  MAIN_PATH,
  SEARCH_PATH,
  USER_PATH,
} from "constant/Path-name";
import { useCookies } from "react-cookie";
import useLoginUserStore from "stores/login-user.store";
import useBoardStore from "stores/board.store";
import { FileUploadRequest, PatchBoardRequest, PostBoardRequest } from "apis";
import { PatchBoardRequestDto, PostBoardRequestDto } from "apis/request";
import {
  PatchBoardResponseDto,
  PostBoardResponseDto,
  ResponseDto,
} from "apis/response";

// component: 헤더
export default function Header() {
  // state: 로그인 유저 상태
  const { loginUser, setLoginUser, resetLoginUser } = useLoginUserStore();
  // state: path 상태
  const { pathname } = useLocation();
  // state: 쿠키 상태
  const [cookie, setCookie] = useCookies();
  // state: 로그인 상태
  const [isLogin, setLogin] = useState<boolean>(false);
  // state: 인증 페이지 상태
  const [isAuthPage, setAuthPage] = useState<boolean>(false);
  // state: 메인 페이지 상태
  const [isMainPage, setMainPage] = useState<boolean>(false);
  // state: 검색 페이지 상태
  const [isSearchPage, setSearchPage] = useState<boolean>(false);
  // state: 게시물 상세 페이지 상태
  const [isBoardDetailPage, setBoardDetailPage] = useState<boolean>(false);
  // state: 게시물 작성 페이지 상태
  const [isBoardWritePage, setBoardWritePage] = useState<boolean>(false);
  // state: 게시물 수정 페이지 상태
  const [isBoardUpdatePage, setBoardUpdatePage] = useState<boolean>(false);
  // state: 유저 페이지 상태
  const [isUserPage, setUserPage] = useState<boolean>(false);

  // function: 네비게이트
  const navigate = useNavigate();

  // event handler: 로고 클릭
  const onLogoClickHandler = () => {
    navigate(MAIN_PATH());
  }; // fn end onLogoClickHandler

  // component: 검색 버튼
  const SearchButton = () => {
    // state: 검색 단어 Ref
    const searchButtonRef = useRef<HTMLDivElement | null>(null);
    // state: 검색 버튼 state
    const [status, setStatus] = useState<boolean>(false);
    // state: 검색 단어 state
    const [word, setWord] = useState<string>("");
    // state: 검색어 path
    const { searchWord } = useParams();

    // event handler: 검색 아이콘 handler
    const onSearchButtonClickHandler = () => {
      if (!status) {
        setStatus(!status);
        return;
      }
      navigate(SEARCH_PATH(word));
    }; // fn end onSearchButtonClickHandler

    // event handler: 검색 단어 변경 handler
    const onSearchWordChangeHandler = (
      event: ChangeEvent<HTMLInputElement>
    ) => {
      const value = event.target.value;
      setWord(value);
    }; // onSearchWordChangeHandler

    // event handler: 검섹 단어 키 handler
    const onSearchWordKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      if (!searchButtonRef.current) return;
      searchButtonRef.current.click();
    }; // onSearchWordKeyDownHandler

    // effect: 검색어 고정
    useEffect(() => {
      if (searchWord) {
        setWord(searchWord);
        setStatus(true);
      }
    }, [searchWord]);

    if (!status)
      // render: SearchButton render - click false
      return (
        <div className="icon-button" onClick={onSearchButtonClickHandler}>
          <div className="icon search-button-icon"></div>
        </div>
      );
    // render: SearchButton render - click true
    return (
      <div className="header-search-input-area">
        <input
          type="text"
          className="header-search-input"
          placeholder="검색어를 입력해주세요."
          value={word}
          onChange={onSearchWordChangeHandler}
          onKeyDown={onSearchWordKeyDownHandler}
        />
        <div
          className="icon-button"
          onClick={onSearchButtonClickHandler}
          ref={searchButtonRef}
        >
          <div className="icon search-button-icon"></div>
        </div>
      </div>
    );
  }; // fn end SearchButton

  // component: 로그인 or 마이페이지 버튼
  const MyPageButton = () => {
    const { userId } = useParams();
    // event handler: 마이페이지 버튼 클릭 이벤트
    const onMyPageButtonClickHandler = () => {
      if (!loginUser) return;
      const { userId } = loginUser;
      navigate(USER_PATH(userId));
    }; // fn end onMyPageButtonClickHandler

    // event handler: 로그아웃 버튼 클릭 이벤트
    const onLogoutButtonClickHandler = () => {
      resetLoginUser();
      setCookie("accessToken", "", { path: MAIN_PATH(), expires: new Date() });
      navigate(MAIN_PATH());
    }; // fn end onLogoutButtonClickHandler

    // event handler: 로그인 버튼 클릭 이벤트
    const onSignInButtonClickHandler = () => {
      navigate(AUTH_PATH());
    }; // fn end onSignInButtonClickHandler

    // render: 로그아웃 버튼 render
    if (isLogin && userId === String(loginUser?.userId))
      return (
        <div className="white-button" onClick={onLogoutButtonClickHandler}>
          {"로그아웃"}
        </div>
      );
    // render: 마이페이지 버튼 render
    if (isLogin)
      return (
        <div className="white-button" onClick={onMyPageButtonClickHandler}>
          {"마이페이지"}
        </div>
      );
    // render: 로그인 버튼 render
    return (
      <div className="black-button" onClick={onSignInButtonClickHandler}>
        {"로그인"}
      </div>
    );
  }; // fn end MyPageButton

  // component: 업로드 버튼
  const UploadButton = () => {
    // state: 게시물 번호 path variable 상태
    const { boardNumber } = useParams();
    // state: 게시물 상태
    const { title, content, boardImageFileList, resetBoard } = useBoardStore();

    // function: post board response 처리 함수
    const PostBoardResponse = (
      responseBody: PostBoardResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) return;
      const { code } = responseBody;
      if (code === "AF" || code === "NU") navigate(AUTH_PATH());
      if (code === "VF") alert("제목과 내용을 필수입니다.");
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;

      resetBoard();
      if (!loginUser) return;
      const { userId } = loginUser;
      navigate(USER_PATH(userId));
    }; // PostBoardResponse

    // function: patch board response 처리
    const patchboardResponse = (
      responseBody: PatchBoardResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) return;
      const { code } = responseBody;
      if (code === "AF" || code === "NU" || code === "NB" || code === "NP")
        navigate(AUTH_PATH());
      if (code === "VF") alert("제목과 내용을 필수입니다.");
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code !== "SU") return;

      if (!boardNumber) return;
      navigate(BOARD_PATH() + "/" + BOARD_DETTAIL_PATH(boardNumber));
    }; // patchboardResponse

    // event handler: 업로드 버튼 클릭 이벤트
    const onUploadButtonClickHandler = async () => {
      const accessToken = cookie.accessToken;
      if (!accessToken) return;

      const boardImageList: string[] = [];

      for (const file of boardImageFileList) {
        const data = new FormData();
        data.append("file", file);

        const url = await FileUploadRequest(data);
        if (url) boardImageList.push(url);
      }

      const isWriterPage = pathname === BOARD_PATH() + "/" + BOARD_WRITE_PATH();

      if (isWriterPage) {
        const requestBody: PostBoardRequestDto = {
          title,
          content,
          boardImageList,
        };
        PostBoardRequest(requestBody, accessToken).then(PostBoardResponse);
      } else {
        if (!boardNumber) return;
        const requestBody: PatchBoardRequestDto = {
          title,
          content,
          boardImageList,
        };
        PatchBoardRequest(boardNumber, requestBody, accessToken).then(
          patchboardResponse
        );
      }
    }; // onUploadButtonClickHandler

    // render: 업로드 버튼 render
    if (title && content)
      return (
        <div className="black-button" onClick={onUploadButtonClickHandler}>
          {"업로드"}
        </div>
      );
    // render: 업로드 불가 버튼 render
    return <div className="disable-button">{"업로드"}</div>;
  }; // UploadButton

  // effect: path 변경시 실행
  useEffect(() => {
    const isAuthPage = pathname.startsWith(AUTH_PATH());
    setAuthPage(isAuthPage);
    const isMainPage = pathname === MAIN_PATH();
    setMainPage(isMainPage);
    const isSearchPage = pathname.startsWith(SEARCH_PATH(""));
    setSearchPage(isSearchPage);
    const isBoardDetailPage = pathname.startsWith(
      BOARD_PATH() + "/" + BOARD_DETTAIL_PATH("")
    );
    setBoardDetailPage(isBoardDetailPage);
    const isBoardWritePage = pathname.startsWith(
      BOARD_PATH() + "/" + BOARD_WRITE_PATH()
    );
    setBoardWritePage(isBoardWritePage);
    const isBoardUpdatePage = pathname.startsWith(
      BOARD_PATH() + "/" + BOARD_UPDATE_PATH("")
    );
    setBoardUpdatePage(isBoardUpdatePage);
    const isUserPage = pathname.startsWith(USER_PATH(""));
    setUserPage(isUserPage);
  }, [pathname]);

  // effect: 로그인 유저 변경시 실행
  useEffect(() => {
    setLogin(loginUser !== null);
  }, [loginUser]);

  // render: Header render
  return (
    <div id="header">
      <div className="header-container">
        <div className="header-left-area" onClick={onLogoClickHandler}>
          <div className="icon-box">
            <div className="icon logo-dark-icon"></div>
          </div>
          <div className="header-logo">{"44y Board"}</div>
        </div>
        <div className="header-right-area">
          {(isAuthPage || isMainPage || isSearchPage || isBoardDetailPage) && (
            <SearchButton />
          )}
          {(isMainPage || isSearchPage || isBoardDetailPage || isUserPage) && (
            <MyPageButton />
          )}

          {(isBoardWritePage || isBoardUpdatePage) && <UploadButton />}
        </div>
      </div>
    </div>
  ); // #header
} // Header

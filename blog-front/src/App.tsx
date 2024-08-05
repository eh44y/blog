import React, { useEffect } from "react";
import "assets/css/App.css";
import { Route, Routes } from "react-router-dom";
import Main from "views/Main";
import Authentication from "views/auth/Authenticaion";
import Search from "views/Search";
import UserPage from "views/User";
import BoardDetail from "views/board/BoardDetail";
import BoardWrite from "views/board/BoardWrite";
import BoardUpdate from "views/board/BoardUpdate";
import Layouts from "layouts/Layouts";
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
import { GetSignInUserRequest } from "apis";
import { GetSignInUserResponseDto, ResponseDto } from "apis/response";
import { User } from "types/interface";

// component: app
function App() {
  // state: 로그인 유저 상태
  const { setLoginUser, resetLoginUser } = useLoginUserStore();
  // state: 쿠키 상태
  const [cookies, setCookies] = useCookies();

  // function: getSignInUserResponse 함수
  const getSignInUserResponse = (
    responseBody: GetSignInUserResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) return;
    const { code } = responseBody;
    if (code === "AF" || code === "NU" || code === "DBE") {
      resetLoginUser();
      return;
    }
    const loginUser: User = { ...(responseBody as GetSignInUserResponseDto) };
    setLoginUser(loginUser);
  }; // getSignInUserResponse

  // effect: accessToken cookies 값이 변경될 때 실행
  useEffect(() => {
    if (!cookies.accessToken) {
      resetLoginUser();
      return;
    }
    GetSignInUserRequest(cookies.accessToken).then(getSignInUserResponse);
  }, [cookies.accessToken]);
  // render: app render
  // description: Main - '/'
  // description: Authentication - '/auth'
  // description: Search - '/search'
  // description: User - '/user/:userId'
  // description: BoardDetail - '/board/detail/:boardNumber'
  // description: BoardWrite - '/board/write'
  // description: BoardUpdate - '/board/update/:boardNumber'
  return (
    <Routes>
      <Route element={<Layouts />}>
        <Route path={MAIN_PATH()} element={<Main />} />
        <Route path={AUTH_PATH()} element={<Authentication />} />
        <Route path={SEARCH_PATH(":searchWord")} element={<Search />} />
        <Route path={USER_PATH(":userId")} element={<UserPage />} />
        <Route path={BOARD_PATH()}>
          <Route path={BOARD_WRITE_PATH()} element={<BoardWrite />} />
          <Route
            path={BOARD_DETTAIL_PATH(":boardNumber")}
            element={<BoardDetail />}
          />
          <Route
            path={BOARD_UPDATE_PATH(":boardNumber")}
            element={<BoardUpdate />}
          />
        </Route>
        <Route path="*" element={<h1>404 Not Found</h1>} />
      </Route>
    </Routes>
  );
}

export default App;

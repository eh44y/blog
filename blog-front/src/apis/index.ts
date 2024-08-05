import axios from "axios";
import {
  PatchBoardRequestDto,
  PatchNicknameRequestDto,
  PatchProfileImageRequestDto,
  PostBoardRequestDto,
  PostCommentRequestDto,
  SignInRequestDto,
  SignUpRequestDto,
} from "./request";
import {
  DeleteBoardResponseDto,
  GetBoardResponseDto,
  GetCommentListResponseDto,
  GetFavoriteListResponseDto,
  GetLatestBoardListResponseDto,
  GetPopularListResponseDto,
  GetRelationListResponseDto,
  GetSearchBoardListResponseDto,
  GetSignInUserResponseDto,
  GetTop3BoardListResponseDto,
  GetUserBoardListResponseDto,
  GetUserResponseDto,
  IncreaseViewCountResponseDto,
  PatchBoardResponseDto,
  PatchNicknameResponseDto,
  PatchProfileImageResponseDto,
  PostBoardResponseDto,
  PostCommentResponseDto,
  PutFavoriteResponseDto,
  ResponseDto,
  SignInResponseDto,
  SignUpResponseDto,
} from "./response";

const DOMAIN = "http://localhost:4000";
const API_DOMAIN = `${DOMAIN}/api`;

const authorization = (accessToken: string) => {
  return {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  };
}; // authorization

const SIGN_IN_URL = () => `${API_DOMAIN}/auth/signin`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/signup`;

export const SignInRequest = async (requestBody: SignInRequestDto) => {
  const result = await axios
    .post(SIGN_IN_URL(), requestBody)
    .then((response) => {
      const responseBody: SignInResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // SignInRequest

export const SignUpRequest = async (requestBody: SignUpRequestDto) => {
  const result = await axios
    .post(SIGN_UP_URL(), requestBody)
    .then((response) => {
      const responseBody: SignUpResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response.data) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // SignUpRequest

const GET_POPULAR_LIST_URL = () => `${API_DOMAIN}/search/popular-list`;
const GET_RELATION_LIST_URL = (searchWord: string) =>
  `${API_DOMAIN}/search/${searchWord}/relation-list`;

export const GetPopularListRequest = async () => {
  const result = await axios
    .get(GET_POPULAR_LIST_URL())
    .then((response) => {
      const responseBody: GetPopularListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetPopularListRequest

export const GetRelationListRequest = async (searchWord: string) => {
  const result = await axios
    .get(GET_RELATION_LIST_URL(searchWord))
    .then((response) => {
      const responseBody: GetRelationListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetRelationListRequest

// get
const GET_USER_URL = (userId: number) => `${API_DOMAIN}/user/${userId}`;
const GET_SIGN_IN_USER_URL = () => `${API_DOMAIN}/user/`;
// patch
const PATCH_NICKNAME_URL = () => `${API_DOMAIN}/user/nickname`;
const PATCH_PROFILE_IMAGE_URL = () => `${API_DOMAIN}/user/profile-image`;

export const GetUserRequest = async (userId: number) => {
  const result = await axios
    .get(GET_USER_URL(userId))
    .then((response) => {
      const responseBody: GetUserResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetUserRequest

export const GetSignInUserRequest = async (accessToken: string) => {
  const result = await axios
    .get(GET_SIGN_IN_USER_URL(), authorization(accessToken))
    .then((response) => {
      const responseBody: GetSignInUserResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetSignInUserRequest

export const PatchNicknameRequest = async (
  requestBody: PatchNicknameRequestDto,
  accessToken: string
) => {
  const result = await axios
    .patch(PATCH_NICKNAME_URL(), requestBody, authorization(accessToken))
    .then((response) => {
      const responseBody: PatchNicknameResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PatchNicknameRequest

export const PatchProfileImageRequest = async (
  requestBody: PatchProfileImageRequestDto,
  accessToken: string
) => {
  const result = await axios
    .patch(PATCH_PROFILE_IMAGE_URL(), requestBody, authorization(accessToken))
    .then((response) => {
      const responseBody: PatchProfileImageResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PatchProfileImageRequest

// get
const GET_BOARD_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}`;
const GET_LATEST_BOARD_LIST_URL = () => `${API_DOMAIN}/board/latest-list`;
const GET_TOP_3_BOARD_URL = () => `${API_DOMAIN}/board/top-3`;
const GET_SEARCH_BOARD_LIST_URL = (
  searchWord: string,
  preSearchWord: string | null
) =>
  `${API_DOMAIN}/board/search-list/${searchWord}${
    preSearchWord ? "/" + preSearchWord : ""
  }`;
const GET_USER_BOARD_LIST_URL = (userId: number) =>
  `${API_DOMAIN}/board/user-board-list/${userId}`;
const INCREASE_VIEW_COUNT_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}/increase-view-count`;
const GET_FAVORITE_LIST_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}/favorite-list`;
const GET_COMMENT_LIST_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}/comment-list`;

// post
const POST_BOARD_URL = () => `${API_DOMAIN}/board`;
const POST_COMMENT_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}/comment`;

// put
const PUT_FAVORITE_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}/favorite`;

// patch
const PATCH_BOARD_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}`;

// delete
const DELETE_BOARD_URL = (boardNumber: number | string) =>
  `${API_DOMAIN}/board/${boardNumber}`;

export const GetBoardRequest = async (boardNumber: number | string) => {
  const result = await axios
    .get(GET_BOARD_URL(boardNumber))
    .then((response) => {
      const responseBody: GetBoardResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });
  return result;
}; // GetBoardRequest

export const GetLatestBoardListRequest = async () => {
  const result = await axios
    .get(GET_LATEST_BOARD_LIST_URL())
    .then((response) => {
      const responseBody: GetLatestBoardListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.respo == null) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetLatestBoardListRequest

export const GetTop3BoardListRequest = async () => {
  const result = await axios
    .get(GET_TOP_3_BOARD_URL())
    .then((response) => {
      const responseBody: GetTop3BoardListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.respo == null) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetTop3BoardListRequest

export const GetSearchBoardListRequest = async (
  searchWord: string,
  preSearchWord: string | null
) => {
  const result = await axios
    .get(GET_SEARCH_BOARD_LIST_URL(searchWord, preSearchWord))
    .then((response) => {
      const responseBody: GetSearchBoardListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.respo == null) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetSearchBoardListRequest

export const GetUserBoardListRequest = async (userId: number) => {
  const result = await axios
    .get(GET_USER_BOARD_LIST_URL(userId))
    .then((response) => {
      const responseBody: GetUserBoardListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.respo == null) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetUserBoardListRequest

export const IncreaseViewCountRequest = async (
  boardNumber: number | string
) => {
  const result = await axios
    .get(INCREASE_VIEW_COUNT_URL(boardNumber))
    .then((response) => {
      const responseBody: IncreaseViewCountResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.respo == null) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // IncreaseViewCountRequest

export const GetFavoriteListRequest = async (boardNumber: number | string) => {
  const result = await axios
    .get(GET_FAVORITE_LIST_URL(boardNumber))
    .then((response) => {
      const responseBody: GetFavoriteListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetFavoriteListRequest

export const GetCommentListRequest = async (boardNumber: number | string) => {
  const result = await axios
    .get(GET_COMMENT_LIST_URL(boardNumber))
    .then((response) => {
      const responseBody: GetCommentListResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // GetCommentListRequest

export const PostBoardRequest = async (
  requestBody: PostBoardRequestDto,
  accessToken: string
) => {
  const result = await axios
    .post(POST_BOARD_URL(), requestBody, authorization(accessToken))
    .then((response) => {
      const ResponseBody: PostBoardResponseDto = response.data;
      return ResponseBody;
    })
    .catch((error) => {
      if (error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PostBoardRequest

export const PostCommentRequest = async (
  boardNumber: number | string,
  requestBody: PostCommentRequestDto,
  accessToken: string
) => {
  const result = await axios
    .post(
      POST_COMMENT_URL(boardNumber),
      requestBody,
      authorization(accessToken)
    )
    .then((response) => {
      const responseBody: PostCommentResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (!error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PostCommentRequest

export const PutFavoriteRequest = async (
  boardNumber: number | string,
  accessToken: string
) => {
  const result = await axios
    .put(PUT_FAVORITE_URL(boardNumber), {}, authorization(accessToken))
    .then((response) => {
      const responseBody: PutFavoriteResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PutFavoriteRequest

export const PatchBoardRequest = async (
  boardNumber: number | string,
  requestBody: PatchBoardRequestDto,
  accessToken: string
) => {
  const result = await axios
    .patch(
      PATCH_BOARD_URL(boardNumber),
      requestBody,
      authorization(accessToken)
    )
    .then((response) => {
      const responseBody: PatchBoardResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // PatchBoardRequest

export const DeleteBoardRequest = async (
  boaradNumber: number | string,
  accessToken: string
) => {
  const result = await axios
    .delete(DELETE_BOARD_URL(boaradNumber), authorization(accessToken))
    .then((response) => {
      const responseBody: DeleteBoardResponseDto = response.data;
      return responseBody;
    })
    .catch((error) => {
      if (error.response) return null;
      const responseBody: ResponseDto = error.response.data;
      return responseBody;
    });

  return result;
}; // DeleteBoardRequest

const FILE_DOMAIN = `${DOMAIN}/file`;
const FILE_UPLOAD_URL = () => `${FILE_DOMAIN}/upload`;
const multipartFormData = {
  headers: { "Content-Type": "multipart/form-data" },
};

export const FileUploadRequest = async (data: FormData) => {
  const result = await axios
    .post(FILE_UPLOAD_URL(), data, multipartFormData)
    .then((response) => {
      const responseBody: string = response.data;
      return responseBody;
    })
    .catch((error) => {
      return null;
    });

  return result;
}; // FileUploadRequest

import React, {
  useState,
  KeyboardEvent,
  useRef,
  ChangeEvent,
  useEffect,
} from "react";
import "assets/css/auth/Authentication.css";
import InputBox from "components/InputBox";
import { SignInRequestDto, SignUpRequestDto } from "apis/request";
import { SignInRequest, SignUpRequest } from "apis";
import SignInResponseDto from "apis/response/auth/sign-in.response.dto";
import { ResponseDto, SignUpResponseDto } from "apis/response";
import { useCookies } from "react-cookie";
import { MAIN_PATH } from "constant/Path-name";
import { useNavigate } from "react-router-dom";

// component: Authentication
export default function Authentication() {
  // state: 화면 상태
  const [view, setView] = useState<"signin" | "signup">("signin");
  // state: 쿠키 상태
  const [cookies, setCookies] = useCookies();

  // function: 네비게이터
  const navigate = useNavigate();

  // component: sign in card
  const SignInCard = () => {
    // state: 이메일 참조 상태
    const emailRef = useRef<HTMLInputElement | null>(null);
    // state: 비밀번호 참조 상태
    const passwordRef = useRef<HTMLInputElement | null>(null);
    // state: 이메일 상태
    const [email, setEmail] = useState<string>("");
    // state: 비밀번호 상태
    const [password, setPassword] = useState<string>("");
    // state: 비밀번호 타입 상태
    const [passwordType, setPasswordType] = useState<"text" | "password">(
      "password"
    );
    // state: 에러 상태
    const [error, setError] = useState<boolean>(false);
    // state: 비밀번호 버튼 아이콘 상태
    const [passwordButtonIcon, setPasswordButtonIcon] = useState<
      "eye-light-off-icon" | "eye-light-on-icon"
    >("eye-light-off-icon");

    // function: sign in response 처리
    const signInResponse = (
      responseBody: SignInResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) {
        alert("네트워크 이상입니다.");
        return;
      }
      const { code } = responseBody;
      if (code === "DBE") alert("데이터베이스 오류입니다.");
      if (code === "SF" || code === "VF") setError(true);
      if (code !== "SU") return;

      const { token, expirationTime } = responseBody as SignInResponseDto;
      const now = new Date().getTime();
      const expires = new Date(now + expirationTime * 1000);

      setCookies("accessToken", token.accessToken, {
        expires,
        path: MAIN_PATH(),
      });
      navigate(MAIN_PATH());
    }; // fn end signInResponse

    // event handler: 이메일 변경 이벤트
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const { value } = event.target;
      setEmail(value);
    }; // onEmailChangeHandler

    // event handler: 비밀번호 변경 이벤트
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      setError(false);
      const { value } = event.target;
      setPassword(value);
    }; // onPasswordChangeHandler

    // event handler: 로그인 버튼 클릭 이벤트
    const onSignInButtonClickHandler = () => {
      const requestBody: SignInRequestDto = { email, password };
      SignInRequest(requestBody).then(signInResponse);
    }; // onSignInButtonClickHandler
    // event handler: 회원가입 버튼 클릭 이벤트
    const onSignUpLinkClickHandler = () => {
      setView("signup");
    }; // onSignUpLinkClickHandler

    // event handler: 이메일 인풋 키 다운 이벤트
    const onEmailKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== "Enter") return;
      if (!passwordRef.current) return;
      passwordRef.current.focus();
    }; // onEmailKeyDownHandler
    // event handler: 비밀번호 인풋 키 다운 이벤트
    const onPasswordKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      onSignInButtonClickHandler();
    }; // onPasswordKeyDownHandler

    // event handler: 비밀번호 버튼 클릭 이벤트
    const onPasswordButtonClickHandler = () => {
      if (passwordType === "text") {
        setPasswordType("password");
        setPasswordButtonIcon("eye-light-off-icon");
      } else {
        setPasswordType("text");
        setPasswordButtonIcon("eye-light-on-icon");
      }
    }; // fn end onPasswordButtonClickHandler

    // render: sign in card render
    return (
      <div className="auth-card">
        <div className="auth-card-area">
          <div className="auth-card-top">
            <div className="auth-card-title-area">
              <div className="auth-card-title">{"로그인"}</div>
            </div>
            <InputBox
              ref={emailRef}
              label="이메일 주소"
              type="text"
              placeholder="이메일 주소를 입력해주세요."
              error={error}
              value={email}
              onChange={onEmailChangeHandler}
              onKeyDown={onEmailKeyDownHandler}
            />
            <InputBox
              ref={passwordRef}
              label="비밀번호"
              type={passwordType}
              placeholder="비밀번호를 입력해주세요."
              error={error}
              value={password}
              onChange={onPasswordChangeHandler}
              icon={passwordButtonIcon}
              onButtonClick={onPasswordButtonClickHandler}
              onKeyDown={onPasswordKeyDownHandler}
            />
          </div>
          <div className="auth-card-bottom">
            {error && (
              <div className="auth-signin-error-area">
                <div className="auth-signin-error-message">
                  {
                    "이메일 주소 또는 비밀번호를 잘못 입력했습니다. \n입력하신 내용을 다시 확인해주세요."
                  }
                </div>
              </div>
            )}
            <div
              className="black-large-button"
              onClick={onSignInButtonClickHandler}
            >
              {"로그인"}
            </div>
            <div className="auth-desc-area">
              <div className="auth-desc">
                {"신규 사용자인가요?"}
                <span
                  className="auth-desc-link"
                  onClick={onSignUpLinkClickHandler}
                >
                  {" 회원가입"}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }; // SignInCard

  // component: sign up card
  const SignUpCard = () => {
    // state: 이메일 참조 상태
    const emailRef = useRef<HTMLInputElement | null>(null);
    // state: 비밀번호 참조 상태
    const passwordRef = useRef<HTMLInputElement | null>(null);
    // state: 비밀번호 확인 참조 상태
    const passwordCheckRef = useRef<HTMLInputElement | null>(null);
    // state: 닉네임 참조 상태
    const nicknameRef = useRef<HTMLInputElement | null>(null);
    // state: 핸드폰 번호 침조 싱태
    const telNumberRef = useRef<HTMLInputElement | null>(null);

    // state: 페이지 번호 상태
    const [page, setPage] = useState<1 | 2>(1);
    // state: 이메일 상태
    const [email, setEmail] = useState<string>("");
    // state: 비밀번호 상태
    const [password, setPassword] = useState<string>("");
    // state: 비밀번호 확인 상태
    const [passwordCheck, setPasswordCheck] = useState<string>("");

    // state: 비밀번호 타입 상태
    const [passwordType, setPasswordType] = useState<"text" | "password">(
      "password"
    );
    // state: 비밀번호 확인 타입 상태
    const [passwordCheckType, setPasswordCheckType] = useState<
      "text" | "password"
    >("password");
    // state: 닉네임 상태
    const [nickname, setNickname] = useState<string>("");
    // state: 핸드폰 번호 상태
    const [telNumber, setTelNumber] = useState<string>("");
    // state: 개인 정보 동의 상태
    const [agreedPersonal, setAgreedPersonal] = useState<boolean>(false);

    // state: 이메일 에러 상태
    const [isEmailError, setEmailError] = useState<boolean>(false);
    // state: 비밀번호 에러 상태
    const [isPasswordError, setPasswordError] = useState<boolean>(false);
    // state: 비밀번호 확인 에러 상태
    const [isPasswordCheckError, setPasswordCheckError] =
      useState<boolean>(false);
    // state: 닉네임 에러 상태
    const [isNicknameError, setNicknameError] = useState<boolean>(false);
    // state: 핸드폰 번호 에러 상태
    const [isTelNumberError, setTelNumberError] = useState<boolean>(false);
    // state: 개인 정보 동의 에러 상태
    const [isAgreedPersonalError, setAgreedPersonalError] =
      useState<boolean>(false);

    // state: 이메일 에러 메세지 상태
    const [emailErrorMessage, setEmailErrorMessage] = useState<string>("");
    // state: 비밀번호 에러 메세지 상태
    const [passwordErrorMessage, setPasswordErrorMessage] =
      useState<string>("");
    // state: 비밀번호 확인 에러 메세지 상태
    const [passwordCheckErrorMessage, setPasswordCheckErrorMessage] =
      useState<string>("");
    // state: 닉네임 에러 메세지 상태
    const [nicknameErrorMessage, setNicknameErrorMessage] =
      useState<string>("");
    // state: 핸드폰 번호 에러 메세지 상태
    const [telNumberErrorMessage, setTelNumberErrorMessage] =
      useState<string>("");

    //  state: 비밀번호 버튼 아이콘 상태
    const [passwordButtonIcon, setPasswordButtonIcon] = useState<
      "eye-light-off-icon" | "eye-light-on-icon"
    >("eye-light-off-icon");
    //  state: 비밀번호 확인 버튼 아이콘 상태
    const [passwordCheckButtonIcon, setPasswordCheckButtonIcon] = useState<
      "eye-light-off-icon" | "eye-light-on-icon"
    >("eye-light-off-icon");

    // function: signUpResponse 처리 함수
    const signUpResponse = (
      responseBody: SignUpResponseDto | ResponseDto | null
    ) => {
      if (!responseBody) {
        alert("네트워크 이상입니다.");
        return;
      }

      const { code } = responseBody;
      if (code === "DE") {
        setEmailError(true);
        setEmailErrorMessage("중복되는 이메일입니다.");
      }
      if (code === "DN") {
        setNicknameError(true);
        setNicknameErrorMessage("중복되는 닉네임입니다.");
      }
      if (code === "DT") {
        setTelNumberError(true);
        setTelNumberErrorMessage("중복되는 핸드폰 번호입니다.");
      }
      if (code === "VF") alert("모든 값을 입력해주세요.");
      if (code === "DBE") alert("데이더베이스 오류입니다.");
      if (code !== "SU") return;
      setView("signin");
    }; // signUpResponse

    // event handler: 이메일 변경 이벤트
    const onEmailChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setEmail(value);
      setEmailError(false);
      setEmailErrorMessage("");
    }; // onEmailChangeHandler

    // event handler: 비밀번호 변경 이벤트
    const onPasswordChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPassword(value);
      setPasswordError(false);
      setPasswordErrorMessage("");
    }; // onPasswordChangeHandler

    // event handler: 비밀번호 확인 변경 이벤트
    const onPasswordCheckHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setPasswordCheck(value);
      setPasswordCheckError(false);
      setPasswordCheckErrorMessage("");
    }; // onPasswordCheckHandler

    // event handler: 닉네임 변경 이벤트
    const onNicnameChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setNickname(value);
      setNicknameError(false);
      setNicknameErrorMessage("");
    }; // onNicnameChangeHandler

    // event handler: 핸드폰 번호 변경 이벤트
    const onTelNumberChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
      const { value } = event.target;
      setTelNumber(value);
      setTelNumberError(false);
      setTelNumberErrorMessage("");
    }; // onTelNumberChangeHandler

    // event handler: 개인 정보 동의 클릭 이벤트
    const onAgreedPersonalClickHandler = () => {
      setAgreedPersonal(!agreedPersonal);
      setAgreedPersonalError(false);
    }; // onAgreedPersonalClickHandler

    // event handler: 비밀번호 버튼 클릭 이벤트
    const onPasswordButtonClickHandler = () => {
      if (passwordButtonIcon === "eye-light-off-icon") {
        setPasswordButtonIcon("eye-light-on-icon");
        setPasswordType("text");
      } else {
        setPasswordButtonIcon("eye-light-off-icon");
        setPasswordType("password");
      }
    }; // onPasswordButtonClickHandler

    // event handler: 비밀번호 확인 버튼 클릭 이벤트
    const onPasswordCheckButtonClickHandler = () => {
      if (passwordCheckButtonIcon === "eye-light-off-icon") {
        setPasswordCheckButtonIcon("eye-light-on-icon");
        setPasswordCheckType("text");
      } else {
        setPasswordCheckButtonIcon("eye-light-off-icon");
        setPasswordCheckType("password");
      }
    }; // onPasswordCheckButtonClickHandler

    // event handler: 다음 버튼 클릭 이벤트
    const onNextButtonClickHandler = () => {
      const emailPattern =
        /^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\.[a-zA-Z0-9]{2,4}$/;

      const isEmailPattern = emailPattern.test(email);
      if (!isEmailPattern) {
        setEmailError(true);
        setEmailErrorMessage("이메일 주소가 올바르지 않습니다.");
      }

      const isCheckedPassword = password.trim().length >= 8;
      if (!isCheckedPassword) {
        setPasswordError(true);
        setPasswordErrorMessage("비밀번호를 8자 이상 입력해주세요.");
      }

      const isEqualPassword = password === passwordCheck;
      if (!isEqualPassword) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage("비밀번호가 일치하지 않습니다.");
      }

      if (!isEmailPattern || !isCheckedPassword || !isEqualPassword) return;
      setPage(2);
    }; // onNextButtonClickHandler

    // event handler: 회원가입 버튼 클릭 이벤트
    const onSignUpButtonClickHandler = () => {
      const emailPattern =
        /^[a-zA-Z0-9]*@([-.]?[a-zA-Z0-9])*\.[a-zA-Z0-9]{2,4}$/;

      const isEmailPattern = emailPattern.test(email);
      if (!isEmailPattern) {
        setEmailError(true);
        setEmailErrorMessage("이메일 주소가 올바르지 않습니다.");
      }

      const isCheckedPassword = password.trim().length >= 8;
      if (!isCheckedPassword) {
        setPasswordError(true);
        setPasswordErrorMessage("비밀번호를 8자 이상 입력해주세요.");
      }

      const isEqualPassword = password === passwordCheck;
      if (!isEqualPassword) {
        setPasswordCheckError(true);
        setPasswordCheckErrorMessage("비밀번호가 일치하지 않습니다.");
      }

      if (!isEmailPattern || !isCheckedPassword || !isEqualPassword) {
        setPage(1);
        return;
      }

      const hasNickname = nickname.trim().length > 0;
      if (!hasNickname) {
        setNicknameError(true);
        setNicknameErrorMessage("닉네임을 입력해주세요.");
      }

      const TelNumberPattern = /[0-9]{11,13}$/;
      const isTelNumberPattern = TelNumberPattern.test(telNumber);
      if (!isTelNumberPattern) {
        setTelNumberError(true);
        setTelNumberErrorMessage("숫자만 입력해주세요.");
      }

      if (!agreedPersonal) setAgreedPersonal(true);

      if (!hasNickname || !isTelNumberPattern || !agreedPersonal) return;

      const requestBody: SignUpRequestDto = {
        email,
        password,
        nickname,
        telNumber,
        agreedPersonal,
      };

      SignUpRequest(requestBody).then(signUpResponse);
    }; // onSignUpButtonClickHandler

    // event handler: 로그인 링크 클릭 이벤트
    const onSignInLinkClickHandler = () => {
      setView("signin");
    }; // onSignInLinkClickHandler

    // event handler: 이메일 키 다운 이벤트
    const onEmailKeyDownHandler = (event: KeyboardEvent<HTMLInputElement>) => {
      if (event.key !== "Enter") return;
      if (!passwordRef.current) return;
      passwordRef.current.focus();
    }; // onEmailKeyDownHandler

    // event handler: 비밀번호 키 다운 이벤트
    const onPasswordKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      if (!passwordCheckRef.current) return;
      passwordCheckRef.current.focus();
    }; // onPasswordKeyDownHandler

    // event handler: 비밀번호 확인 키 다운 이벤트
    const onPasswordCheckKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      //if (!nicknameRef.current) return;
      onNextButtonClickHandler();
      //nicknameRef.current.focus();
    }; // onPasswordCheckKeyDownHandler

    // event handler: 닉네임 키 다운 이벤트
    const onNicknameKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      if (!telNumberRef.current) return;
      telNumberRef.current.focus();
    }; // onNicknameKeyDownHandler

    // event handler: 핸드폰 번호 키 다운 이벤트
    const onTelNumberKeyDownHandler = (
      event: KeyboardEvent<HTMLInputElement>
    ) => {
      if (event.key !== "Enter") return;
      onSignUpButtonClickHandler();
    }; // onTelNumberKeyDownHandler

    // effect: 페이지 변경시 실행될 함수
    useEffect(() => {
      if (page === 2) {
        if (!nicknameRef.current) return;
        nicknameRef.current.focus();
      }
    }, [page]);

    // render: sign up card render
    return (
      <div className="auth-card">
        <div className="auth-card-area">
          <div className="auth-card-top">
            <div className="auth-card-title-area">
              <div className="auth-card-title">{"회원가입"}</div>
              <div className="auth-card-page">{`${page}/2`}</div>
            </div>
            {page === 1 && (
              <>
                <InputBox
                  ref={emailRef}
                  label="이메일 주소*"
                  type="text"
                  placeholder="이메일 주소를 입력해주세요."
                  value={email}
                  onChange={onEmailChangeHandler}
                  error={isEmailError}
                  message={emailErrorMessage}
                  onKeyDown={onEmailKeyDownHandler}
                />
                <InputBox
                  ref={passwordRef}
                  label="비밀번호*"
                  type={passwordType}
                  placeholder="비밀번호를 입력해주세요."
                  value={password}
                  onChange={onPasswordChangeHandler}
                  error={isPasswordError}
                  message={passwordErrorMessage}
                  icon={passwordButtonIcon}
                  onButtonClick={onPasswordButtonClickHandler}
                  onKeyDown={onPasswordKeyDownHandler}
                />
                <InputBox
                  ref={passwordCheckRef}
                  label="비밀번호 확인*"
                  type={passwordCheckType}
                  placeholder="비밀번호를 다시 입려해주세요."
                  value={passwordCheck}
                  onChange={onPasswordCheckHandler}
                  error={isPasswordCheckError}
                  message={passwordCheckErrorMessage}
                  icon={passwordCheckButtonIcon}
                  onButtonClick={onPasswordCheckButtonClickHandler}
                  onKeyDown={onPasswordCheckKeyDownHandler}
                />
              </>
            )}
            {page === 2 && (
              <>
                <InputBox
                  ref={nicknameRef}
                  label="닉네임*"
                  type="text"
                  placeholder="닉네임을 입력해주세요."
                  value={nickname}
                  onChange={onNicnameChangeHandler}
                  error={isNicknameError}
                  message={nicknameErrorMessage}
                  onKeyDown={onNicknameKeyDownHandler}
                />
                <InputBox
                  ref={telNumberRef}
                  label="핸드폰 번호*"
                  type="text"
                  placeholder="핸드폰 번호를 입력해주세요."
                  value={telNumber}
                  onChange={onTelNumberChangeHandler}
                  error={isTelNumberError}
                  message={telNumberErrorMessage}
                  onKeyDown={onTelNumberKeyDownHandler}
                />
              </>
            )}
          </div>
          <div className="auth-card-bottom">
            {page === 1 && (
              <div
                className="black-large-button"
                onClick={onNextButtonClickHandler}
              >
                {"다음"}
              </div>
            )}
            {page === 2 && (
              <>
                <div className="auth-consent-area">
                  <div
                    className="auth-check-box"
                    onClick={onAgreedPersonalClickHandler}
                  >
                    <div
                      className={`icon ${
                        agreedPersonal
                          ? "check-round-fill-icon"
                          : "check-ring-light-icon"
                      }`}
                    ></div>
                  </div>
                  <div
                    className={
                      isAgreedPersonalError
                        ? "auth-consent-title-error"
                        : "auth-consent-title"
                    }
                  >
                    {"개인정보 동의"}
                  </div>
                  <div className="auth-consent-link">{"더보기 >"}</div>
                </div>
                <div
                  className="black-large-button"
                  onClick={onSignUpButtonClickHandler}
                >
                  {"회원가입"}
                </div>
              </>
            )}
            <div className="auth-desc-area">
              <div className="auth-desc">
                {"이미 계정이 있으신가요?"}
                <span
                  className="auth-desc-link"
                  onClick={onSignInLinkClickHandler}
                >
                  {" 로그인"}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }; // SignUpCard

  // redner: Authentication redner
  return (
    <div id="auth-wrapper">
      <div className="auth-container">
        <div className="auth-intro-area">
          <div className="auth-intro-contents">
            <div className="auth-logo-icon"></div>
            <div className="auth-intro-text-area">
              <div className="auth-intro-text">{"안녕하세요."}</div>
              <div className="auth-intro-text">{"44y BOARD 입니다."}</div>
            </div>
          </div>
        </div>
        {view === "signin" && <SignInCard />}
        {view === "signup" && <SignUpCard />}
      </div>
    </div> // #auth-wrapper
  );
} // Authentication

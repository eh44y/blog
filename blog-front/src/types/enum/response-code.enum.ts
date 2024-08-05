enum ResponseCode {
  // http Status 200
  SUCCESS = "SU",

  // http Status 400
  VAILDATION_FAILED = "VF",
  DUPLICATE_EMAIL = "DE",
  DUPLICATE_NICKNAME = "DN",
  DUPLICATE_TEL_NUMBER = "DT",
  NOT_EXISTED_USER = "NU",
  NOT_EXISTED_BOARD = "NB",

  // http Status 401
  SIGN_IN_FAIL = "SF",
  AUTHORIZATION_FAIL = "AF",

  // http Sstatus 403,
  NO_PERMISSION = "NP",

  // http Status 500
  DATABASE_ERROR = "DBE",
} // ResponseDto
export default ResponseCode;

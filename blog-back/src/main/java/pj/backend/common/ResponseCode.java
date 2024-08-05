package pj.backend.common;

public interface ResponseCode {
  // http Status 200
  String SUCCESS = "SU";

  // http Status 400
  String VAILDATION_FAILED = "VF";
  String DUPLICATE_EMAIL = "DE";
  String DUPLICATE_NICKNAME = "DN";
  String DUPLICATE_TEL_NUMBER = "DT";
  String NOT_EXISTED_USER = "NU";
  String NOT_EXISTED_BOARD = "NB";

  // http Status 401
  String SIGN_IN_FAIL = "SF";
  String AUTHORIZATION_FAIL = "AF";

  // http Sstatus 403;
  String NO_PERMISSION = "NP";

  // http Status 500
  String DATABASE_ERROR = "DBE";
} // ResponseCode

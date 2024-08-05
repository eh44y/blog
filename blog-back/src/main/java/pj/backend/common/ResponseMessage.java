package pj.backend.common;

public interface ResponseMessage {
  // http Status 200
  String SUCCESS = "Success.";

  // http Status 400
  String VAILDATION_FAILED = "Validation failed.";
  String DUPLICATE_EMAIL = "Duplicate email.";
  String DUPLICATE_NICKNAME = "Duplicate nickname.";
  String DUPLICATE_TEL_NUMBER = "Duplicate tel number.";
  String NOT_EXISTED_USER = "This user does not exist.";
  String NOT_EXISTED_BOARD = "This board does not exist.";

  // http Status 401
  String SIGN_IN_FAIL = "Login information mismatch.";
  String AUTHORIZATION_FAIL = "Authorization failed.";

  // http Sstatus 403;
  String NO_PERMISSION = "Do not hava permission.";

  // http Status 500
  String DATABASE_ERROR = "Database error.";
} // ResponseMessage

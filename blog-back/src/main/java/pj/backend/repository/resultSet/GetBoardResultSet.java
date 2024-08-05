package pj.backend.repository.resultSet;

public interface GetBoardResultSet {
  
  Integer getBoardNumber();

  String getTitle();

  String getContent();

  String getWriteDatetime();

  int getWriterUserId();

  String getWriterNickname();;

  String getWriterProfileImage();

} // GetBoardResultSet

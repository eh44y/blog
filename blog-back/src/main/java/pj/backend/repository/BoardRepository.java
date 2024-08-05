package pj.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pj.backend.entity.Board;
import pj.backend.repository.resultSet.GetBoardResultSet;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

  Board findByBoardNumber(Integer boardNumber);

  boolean existsByBoardNumber(Integer boardNumber);


  @Query(value =
    "SELECT " +
    "B.board_number AS boardNumber, " +
    "B.title AS title, " +
    "B.content AS content, " +
    "B.write_datetime AS writeDatetime, " +
    "B.user_id AS writerUserId, " +
    "U.nickname AS writerNickname, " +
    "U.profile_image AS writerProfileImage " +
    "FROM board AS B " +
    "INNER JOIN user AS U " +
    "ON B.user_id = U.id " +
    "WHERE board_number = ?1 ",
    nativeQuery = true
  )
  GetBoardResultSet getBoard(Integer boardNumber);
  
} // BoardRepository

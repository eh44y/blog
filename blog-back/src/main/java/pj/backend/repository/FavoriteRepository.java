package pj.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pj.backend.entity.Favorite;
import pj.backend.entity.primaryKey.FavoritePK;
import pj.backend.repository.resultSet.GetFavoriteListResultSet;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {

  Favorite findByBoardNumberAndUserId(Integer boardNumber, Integer userId);

  @Query(value = 
    "SELECT " +
    "U.id AS id, " +
    "U.email As email," +
    "U.nickname AS nickname, " +
    "U.profile_image AS profileImage " +
    "FROM favorite AS F " +
    "INNER JOIN user AS U " +
    "ON F.user_id = U.id " +
    "WHERE F.board_number = ?1 ",
    nativeQuery = true
  )
  List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);

  @Transactional
  void deleteByBoardNumber(Integer boardNumber);
  
} // FavoriteRepository

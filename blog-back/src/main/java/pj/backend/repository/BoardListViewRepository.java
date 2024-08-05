package pj.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pj.backend.entity.BoardListView;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListView, Integer> {
  
  List<BoardListView> findByOrderByWriteDatetimeDesc();

  List<BoardListView> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
  
  List<BoardListView> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);

  List<BoardListView> findByWriterUserIdOrderByWriteDatetimeDesc(int writerUserId);

} // BoardListViewRepository

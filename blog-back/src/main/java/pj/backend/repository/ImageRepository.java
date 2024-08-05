package pj.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import pj.backend.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
  
  List<Image> findByBoardNumber(Integer boardNumber);

  @Transactional
  void deleteByBoardNumber(Integer boardNumber);
  
} // ImageRepository

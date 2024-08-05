package pj.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pj.backend.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByEmail(String email);
  Optional<User> findByNickname(String nickname);
  Optional<User> findByTelNumber(String telNumber);
  boolean existsByEmail(String email);
  boolean existsByNickname(String nickname);
  boolean existsByTelNumber(String telNumber);
} // UserRepository

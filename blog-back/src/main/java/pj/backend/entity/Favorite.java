package pj.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.entity.primaryKey.FavoritePK;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favorite")
@Table(name = "favorite")
@IdClass(FavoritePK.class)
public class Favorite {
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  private int boardNumber;
} // Favorite

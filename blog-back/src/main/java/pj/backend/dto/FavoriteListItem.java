package pj.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.repository.resultSet.GetFavoriteListResultSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteListItem {

  private Integer userId;
  private String email;
  private String nickname;
  private String profileImage;

  public FavoriteListItem(GetFavoriteListResultSet resultSet) {
    this.userId = resultSet.getId();
    this.email = resultSet.getEmail();
    this.nickname = resultSet.getNickname();
    this.profileImage = resultSet.getProfileImage();
  } // FavoriteListItem

  public static List<FavoriteListItem> copyList(List<GetFavoriteListResultSet> resultSets) {
    List<FavoriteListItem> list = new ArrayList<>();

    for (GetFavoriteListResultSet resultSet : resultSets) {
      FavoriteListItem favoriteListItem = new FavoriteListItem(resultSet);
      list.add(favoriteListItem);
    }

    return list;
  } // copyList
} // FavoriteListItem

package pj.backend.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pj.backend.repository.resultSet.GetCommentListResultSet;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentListItem {

  private String nickname;
  private String profileImage;
  private String writeDatetime;
  private String content;

  public CommentListItem(GetCommentListResultSet resultSet) {
    this.nickname = resultSet.getNickname();
    this.profileImage = resultSet.getProfileImage();
    this.writeDatetime = resultSet.getWriteDatetime();
    this.content = resultSet.getContent();
  } // CommentListItem

  public static List<CommentListItem> copyList(List<GetCommentListResultSet> resultSets) {
    List<CommentListItem> list = new ArrayList<>();

    for (GetCommentListResultSet resultSet : resultSets) {
      CommentListItem commentListItem = new CommentListItem(resultSet);
      list.add(commentListItem);
    }

    return list;
  } // copyList

} // CommentListItem

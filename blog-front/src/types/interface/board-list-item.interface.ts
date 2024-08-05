export default interface BoardItem {
  boardNumber: number;
  title: String;
  content: String;
  boardTitleImage: String | null;
  favoriteCount: number;
  commentCount: number;
  viewCount: number;
  writeDateTime: String;
  writerNickname: String;
  writerProfileImage: String | null;
} // BoardListItem

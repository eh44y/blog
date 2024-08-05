export default interface Board {
  boardNumber: number;
  title: string;
  content: string;
  boardImageList: string[];
  writeDatetime: string;
  userId: number;
  writerNickname: string;
  writerProfileImage: string | null;
}

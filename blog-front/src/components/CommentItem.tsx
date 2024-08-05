import React from "react";
import "assets/css/board/commentItem/CommentItem.css";
import { CommentListItem } from "types/interface";
import DefaultProfileImage from "assets/img/default-profile-image.png";
import dayjs from "dayjs";

interface Props {
  commentListItem: CommentListItem;
}

// component: 코멘트 리스트
export default function CommentItem({ commentListItem }: Props) {
  // state: properties
  const { nickname, profileImage, writeDatetime, content } = commentListItem;

  // function: 작성일 경과 시간 처리
  const getElapsedTime = () => {
    const now = dayjs();
    const writeTime = dayjs(writeDatetime);

    const gap = now.diff(writeTime, "s");
    if (gap < 60) return `${gap}초 전`;
    if (gap < 3600) return `${Math.floor(gap / 60)}분 전`;
    if (gap < 3600 * 24) return `${Math.floor(gap / 3600)}시간 전`;
    return `${Math.floor(gap / (3600 * 24))}일 전`;
  }; // getElapsedTime

  // render: Comment List Item
  return (
    <div className="comment-list-item">
      <div className="comment-list-item-top">
        <div className="comment-list-item-profile-area">
          <div
            className="comment-list-item-profile-image"
            style={{
              backgroundImage: `url(${
                profileImage ? profileImage : DefaultProfileImage
              })`,
            }}
          ></div>
        </div>
        <div className="comment-list-item-nickname">{nickname}</div>
        <div className="comment-list-item-divider">{"|"}</div>
        <div className="comment-list-item-time">{getElapsedTime()}</div>
      </div>
      <div className="comment-list-item-main-area">
        <div className="comment-list-item-content">{content}</div>
      </div>
    </div> // comment-list-item
  );
} // fn CommentItem

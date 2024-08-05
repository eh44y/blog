import React from "react";
import "assets/css/top3Item/Top3Item.css";
import { BoardListItem } from "types/interface";
import { useNavigate } from "react-router-dom";
import DefaultProfileImage from "assets/img/default-profile-image.png";
import { BOARD_DETTAIL_PATH, BOARD_PATH } from "constant/Path-name";

interface Props {
  top3ListItem: BoardListItem;
}

// component: Top3 List Item
export default function Top3Item({ top3ListItem }: Props) {
  // properties
  const {
    boardNumber,
    title,
    content,
    boardTitleImage,
    favoriteCount,
    commentCount,
    viewCount,
    writeDateTime,
    writerNickname,
    writerProfileImage,
  } = top3ListItem;

  // function: 네비게이터
  const navigate = useNavigate();

  // event handler
  const onClickHandler = () => {
    navigate(BOARD_PATH() + "/" + BOARD_DETTAIL_PATH(boardNumber));
  };

  // render: 상위 3개 리스트
  return (
    <div
      className="top-3-list-item"
      style={{ backgroundImage: `url(${boardTitleImage})` }}
      onClick={onClickHandler}
    >
      <div className="top-3-list-item-main-area">
        <div className="top-3-list-item-top">
          <div className="top-3-list-item-profile-area">
            <div
              className="top-3-list-item-profile-image"
              style={{
                backgroundImage: `url(${
                  writerProfileImage ? writerProfileImage : DefaultProfileImage
                })`,
              }}
            ></div>
          </div>
          <div className="top-3-list-item-write-area">
            <div className="top-3-list-item-nickname">{writerNickname}</div>
            <div className="top-3-list-item-write-date">{writeDateTime}</div>
          </div>
        </div>
        <div className="top-3-list-item-middle">
          <div className="top-3-list-item-title">{title}</div>
          <div className="top-3-list-item-content">{content}</div>
        </div>
        <div className="top-3-list-item-bottom">
          <div className="top-3-list-item-counts">{`댓글 ${commentCount} · 좋아요 ${favoriteCount} · 조회수 ${viewCount}`}</div>
        </div>
      </div>
    </div> // top-3-list-item
  );
} // fn Top3Item

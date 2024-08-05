import React from "react";
import "assets/css/board/boardListItem/BoardListItem.css";
import { BoardListItem } from "types/interface";
import { useNavigate } from "react-router-dom";
import DefaultProfileImage from "assets/img/default-profile-image.png";
import { BOARD_DETTAIL_PATH, BOARD_PATH } from "constant/Path-name";

interface Props {
  boardListItem: BoardListItem;
}

// component: 보드 리스트
export default function BoardItem({ boardListItem }: Props) {
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
  } = boardListItem;

  // function: 네비게이터
  const navigate = useNavigate();

  // event handler
  const onClickHandler = () => {
    navigate(BOARD_PATH() + "/" + BOARD_DETTAIL_PATH(boardNumber));
  };

  // render: Board List Item
  return (
    <div className="board-list-item" onClick={onClickHandler}>
      <div className="board-list-item-main-area">
        <div className="board-list-item-top">
          <div className="board-list-item-profile-area">
            <div
              className="board-list-item-profile-image"
              style={{
                backgroundImage: `url(${
                  writerProfileImage ? writerProfileImage : DefaultProfileImage
                })`,
              }}
            ></div>
          </div>
          <div className="board-list-item-write-area">
            <div className="board-list-item-nickname">{writerNickname}</div>
            <div className="board-list-item-write-date">{writeDateTime}</div>
          </div>
        </div>
        <div className="board-list-item-middle">
          <div className="board-list-item-title">{title}</div>
          <div className="board-list-item-content">{content}</div>
        </div>
        <div className="board-list-item-bottom">
          <div className="board-list-item-counts">
            {`댓글 ${commentCount} · 좋아요 ${favoriteCount} · 조회수 ${viewCount}`}
          </div>
        </div>
      </div>
      {boardTitleImage !== null && (
        <div className="board-list-item-image-area">
          <div
            className="board-list-item-image"
            style={{
              backgroundImage: `url(${boardTitleImage})`,
            }}
          ></div>
        </div>
      )}
    </div> // board-list-item
  );
} // fn BoardListItem

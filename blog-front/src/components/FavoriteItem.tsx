import React from "react";
import "assets/css/board/favoriteItem/FavoriteItem.css";
import { FavoriteListItem } from "types/interface";
import DefaultProfileImage from "assets/img/default-profile-image.png";

interface Props {
  favoriteListItem: FavoriteListItem;
}

// component: 좋아요 리스트
export default function FavoriteItem({ favoriteListItem }: Props) {
  // properties
  const { profileImage, nickname } = favoriteListItem;

  // render: Favorite List Item
  return (
    <div className="favorite-list-item">
      <div className="favorite-list-item-profile-area">
        <div
          className="favorite-list-item-profile-image"
          style={{
            backgroundImage: `url(${
              profileImage ? profileImage : DefaultProfileImage
            })`,
          }}
        ></div>
      </div>
      <div className="favorite-list-item-nickname">{nickname}</div>
    </div>
  );
} // fn FavoriteItem

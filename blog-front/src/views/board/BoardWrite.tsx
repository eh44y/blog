import React, { ChangeEvent, useEffect, useRef, useState } from "react";
import "assets/css/board/boardWrite/BoardWrite.css";
import useBoardStore from "stores/board.store";
import { useNavigate } from "react-router-dom";
import { MAIN_PATH } from "constant/Path-name";
import { useCookies } from "react-cookie";

// component: BoardWrite
export default function BoardWrite() {
  // state: 제목 참조 상태
  const titleRef = useRef<HTMLTextAreaElement | null>(null);
  // state: 본문 참조 상태
  const contentRef = useRef<HTMLTextAreaElement | null>(null);
  // state: 이미지 파일 참조 상태
  const imageInputRef = useRef<HTMLInputElement | null>(null);

  //  state: 게시물 상태
  const { title, setTitle } = useBoardStore();
  const { content, setContent } = useBoardStore();
  const { boardImageFileList, setBoardImageFileList } = useBoardStore();
  const { resetBoard } = useBoardStore();

  // state: 쿠키 상태
  const [cookies, setCookies] = useCookies();
  // state: 게시물 이미지 미리보기 URL 상태
  const [imageUrls, setImageUrls] = useState<string[]>([]);

  // function: 네비게이트
  const navigate = useNavigate();

  // event handler: 제목 변경 이벤트
  const onTitleChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = event.target;
    setTitle(value);

    if (!titleRef.current) return;
    titleRef.current.style.height = "auto";
    titleRef.current.style.height = `${titleRef.current.scrollHeight}px`;
  }; // onTitleChangeHandler

  // event handler: 내용 변경 이벤트
  const onContentChangeHandler = (event: ChangeEvent<HTMLTextAreaElement>) => {
    const { value } = event.target;
    setContent(value);
    if (!contentRef.current) return;
    contentRef.current.style.height = "auto";
    contentRef.current.style.height = `${contentRef.current.scrollHeight}px`;
  }; // onContentChangeHandler

  // event handler: 이미지 변경 이벤트
  const onImageChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    if (!event.target.files || !event.target.files.length) return;
    const file = event.target.files[0];
    const imageUrl = URL.createObjectURL(file);
    const newImageUrls = imageUrls.map((item) => item);
    newImageUrls.push(imageUrl);
    setImageUrls(newImageUrls);

    const newBoardImageFileList = boardImageFileList.map((item) => item);
    newBoardImageFileList.push(file);
    setBoardImageFileList(newBoardImageFileList);

    if (!imageInputRef.current) return;
    imageInputRef.current.value = "";
  }; // onImageChangeHandler

  // event handler: 이미지 업로드 버튼 클릭 이벤트
  const onImageUploadButtonClickHandler = () => {
    if (!imageInputRef.current) return;
    imageInputRef.current.click();
  }; // onImageUploadButtonClickHandler

  // event handler: 이미지 닫기 버튼 클릭 이벤트
  const onImageCloseButtonClickHandler = (deleteIndex: number) => {
    if (!imageInputRef.current) return;
    imageInputRef.current.value = "";

    const newImageUrls = imageUrls.filter(
      (url, index) => index !== deleteIndex
    );
    setImageUrls(newImageUrls);

    const newBoardImageFileList = boardImageFileList.filter(
      (file, index) => index !== deleteIndex
    );
    setBoardImageFileList(newBoardImageFileList);
  }; // onImageCloseButtonClickHandler

  // effect: 마운트 시 실행할 함수
  useEffect(() => {
    const accessToken = cookies.accessToken;
    if (!accessToken) {
      navigate(MAIN_PATH());
      return;
    }
    resetBoard();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  // render: BoardWrite render
  return (
    <div id="board-write-wrapper">
      <div className="board-write-container">
        <div className="board-write-area">
          <div className="board-write-title-box">
            <textarea
              ref={titleRef}
              className="board-write-title-textarea"
              rows={1}
              //type="text"
              placeholder="제목을 작성해주세요."
              value={title}
              onChange={onTitleChangeHandler}
            />
          </div>
          <div className="divider"></div>
          <div className="board-write-content-area">
            <textarea
              ref={contentRef}
              className="board-write-content-textarea"
              placeholder="본문을 작성해주세요."
              value={content}
              onChange={onContentChangeHandler}
            />
            <div
              className="icon-button"
              onClick={onImageUploadButtonClickHandler}
            >
              <div className="icon image-box-light-icon"></div>
            </div>
            <input
              ref={imageInputRef}
              type="file"
              accept="image/*"
              style={{ display: "none" }}
              onChange={onImageChangeHandler}
            />
          </div>
          <div className="board-write-images-area">
            {imageUrls.map((imageUrl, index) => (
              <div className="board-write-image-box">
                <img className="board-write-image" src={imageUrl} alt="" />
                <div
                  className="icon-button image-close"
                  onClick={() => onImageCloseButtonClickHandler(index)}
                >
                  <div className="icon close-icon"></div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  ); // board-write-wrapper
} // BoardWrite

import React from "react";
import "assets/css/footer/Footer.css";

// component: Footer
export default function Footer() {
  // event handler: insta button click
  const onInstaIconButtonClickHandler = () => {
    window.open("https://www.instagram.com");
  };
  // event handler: git button click
  const onGitIconButtonClickHandler = () => {
    window.open("https://www.github.com");
  };
  // render: Footer render
  return (
    <div id="footer">
      <div className="footer-container">
        <div className="footer-top">
          <div className="footer-logo-box">
            <div className="icon-box">
              <div className="icon logo-light-icon"></div>
            </div>
            <div className="footer-logo-text">{"44y Board"}</div>
          </div>
          <div className="footer-link-box">
            <div className="footer-email-link">{"zaqxsw5467@naver.com"}</div>
            <div
              className="icon-button"
              onClick={onInstaIconButtonClickHandler}
            >
              <div className="icon insta-icon"></div>
            </div>
            <div className="icon-button" onClick={onGitIconButtonClickHandler}>
              <div className="icon git-icon"></div>
            </div>
          </div>
        </div>
        <div className="footer-bottom">
          <div className="footer-copyright">
            {"Copyright ⓒ 2024 44y. All Right Reserved."}
          </div>
        </div>
      </div>
    </div>
  );
} // fn Footer

import React from "react";
import Header from "./Header";
import Footer from "./Footer";
import { Outlet, useLocation } from "react-router-dom";
import { AUTH_PATH } from "constant/Path-name";

// component: Layouts
export default function Layouts() {
  // state: pages path name
  const { pathname } = useLocation();
  // render: Layouts render
  return (
    <>
      <Header />
      <Outlet />
      {pathname !== AUTH_PATH() && <Footer />}
    </>
  );
} // Layouts

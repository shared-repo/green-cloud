import React from "react";
import "./Header.css";

const Header = (props) => {
  return (
    <div className="Header">
      <h3>오늘은 📅</h3>
      <h1>{ new Date().toDateString() }</h1>
    </div>
  );
};
// export default Header;
export default React.memo(Header); // property가 변경될 때 re-render

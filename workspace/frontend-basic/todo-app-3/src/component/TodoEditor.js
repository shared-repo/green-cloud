import { useContext, useState } from "react";
import "./TodoEditor.css";
import { TodoDispatchContext } from "../App";

const TodoEditor = () => {

  const { onCreate } = useContext(TodoDispatchContext);


  const [content, setContent] = useState("");

  const changeHandler = (event) => {
    setContent(event.target.value);
  };

  const addHandler = (event) => {
    if (!content) {
      return;
    }
    // todos 목록에 추가    
    onCreate(content);
    setContent("");

  };

  return (
    <div className="TodoEditor">
      <h4>새로운 Todo 작성하기 ✏ </h4>
      <div className="editor_wrapper">
        <input  value={content} 
                onChange={changeHandler}
                placeholder="새로운 Todo..." />
        <button onClick={addHandler}>추가</button>
      </div>
    </div>
  );
};

export default TodoEditor;

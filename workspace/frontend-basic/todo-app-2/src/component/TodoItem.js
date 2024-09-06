import React from "react";
import "./TodoItem.css";

const TodoItem = ({ todo, onDelete, onUpdate }) => {

  console.log(`---------------> todo(${todo.id}) is updated`);

  const checkedChangeHandler = (event) => {
    // onUpdate({ ...todo, isDone: !todo.isDone });
    onUpdate(todo.id);
  };

  const deleteHandler = () => {
    onDelete(todo.id);
  };
 
  return (
    <div className="TodoItem">
      <div className="checkbox_col">
        <input  type="checkbox" 
                onChange={checkedChangeHandler}
                checked={ todo.isDone } />
      </div>
      <div className="title_col">{ todo.content }</div>
      <div className="date_col">
        { todo.createdDate }
      </div>
      <div className="btn_col">
        <button onClick={ deleteHandler } >삭제</button>
      </div>
    </div>
  );
};

// export default TodoItem;
export default React.memo(TodoItem); // property가 변경될 때 update

import "./TodoItem.css";

const TodoItem = ({ todo }) => {
 
  return (
    <div className="TodoItem">
      <div className="checkbox_col">
        <input type="checkbox" checked={ todo.isDone } />
      </div>
      <div className="title_col">{ todo.content }</div>
      <div className="date_col">
        { todo.createdDate }
      </div>
      <div className="btn_col">
        <button >삭제</button>
      </div>
    </div>
  );
};

export default TodoItem;

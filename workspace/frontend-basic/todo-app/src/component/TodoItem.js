import "./TodoItem.css";

const TodoItem = ({ todo, onDelete }) => {

  const checkedChangeHandler = (event) => {
    // 선택된 todo 객체의 isDone 값 toggle -> 부모 객체에서 처리
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

export default TodoItem;

import TodoItem from "./TodoItem";
import "./TodoList.css";

// const TodoList = (props) => {
//   const { todos } = props;
const TodoList = ({ todos, onDelete }) => {

  return (
    <div className="TodoList">
      <h4>Todo List 🌱</h4>
      <input  className="searchbar"
              placeholder="검색어를 입력하세요"
      />
      <div className="list_wrapper">
      {/* 할 일 목록이 표시되는 영역 : 반복문 처리 */}
      {
        todos.map( (todo) => {
          return (
            <TodoItem key={ todo.id } todo={ todo } onDelete={ onDelete } />
          );
        })
      }  
      </div>
    </div>
  );
};

export default TodoList;

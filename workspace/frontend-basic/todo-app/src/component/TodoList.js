import { useState } from "react";
import TodoItem from "./TodoItem";
import "./TodoList.css";

// const TodoList = (props) => {
//   const { todos } = props;
const TodoList = ({ todos, onDelete, onUpdate }) => {

  const [searchWord, setSearchWord] = useState("");
  const handleChangedSearchWord = (event) => {
    setSearchWord(event.target.value);
  }

  const findTodos = () => {
    // if (searchWord === "") return todos;
    // return todos.filter((todo) => { 
    //   return todo.content.toLowerCase().includes(searchWord.toLowerCase());
    // });

    return searchWord === "" ?
      todos : 
      todos.filter(todo => todo.content.toLowerCase().includes(searchWord.toLowerCase()));
  }

  return (
    <div className="TodoList">
      <h4>Todo List 🌱</h4>
      <input  className="searchbar"
              placeholder="검색어를 입력하세요"
              value={ searchWord }
              onChange={ handleChangedSearchWord }
      />
      <div className="list_wrapper">
      {/* 할 일 목록이 표시되는 영역 : 반복문 처리 */}
      {
        findTodos().map( (todo) => {
          return (
            <TodoItem key={ todo.id } todo={ todo } onDelete={ onDelete } onUpdate={ onUpdate } />
          );
        })
      }  
      </div>
    </div>
  );
};

export default TodoList;

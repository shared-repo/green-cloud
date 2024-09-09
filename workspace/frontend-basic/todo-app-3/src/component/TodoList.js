import { useContext, useMemo, useState } from "react";
import TodoItem from "./TodoItem";
import "./TodoList.css";
import { TodoStateContext } from "../App";

const TodoList = () => {

  const todos  = useContext(TodoStateContext);

  const [searchWord, setSearchWord] = useState("");
  const handleChangedSearchWord = (event) => {
    setSearchWord(event.target.value);
  }

  const findTodos = () => {
    return searchWord === "" ?
      todos : 
      todos.filter(todo => todo.content.toLowerCase().includes(searchWord.toLowerCase()));
  }

  const countTodos = useMemo(() => {
    console.log("----------------------> countTodos is called")
    const totalCnt = todos.length;
    const doneCnt = todos.filter(todo => todo.isDone).length;
    const todoCnt = totalCnt - doneCnt;
    return { totalCnt, doneCnt, todoCnt };
  }, [todos]);
  const { totalCnt, doneCnt, todoCnt } = countTodos;

  return (
    <div className="TodoList">
      <h4>Todo List 🌱</h4>
      <div>
        <div>전체 업무 : {totalCnt}</div>
        <div>완료된 업무 : {doneCnt}</div>
        <div>해야할 업무 : {todoCnt}</div>
      </div>
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
            <TodoItem key={ todo.id } todo={ todo } />
          );
        })
      }  
      </div>
    </div>
  );
};

export default TodoList;

import { useState } from 'react';

import './App.css';
import Header from './component/Header';
import TodoEditor from './component/TodoEditor';
import TodoList from './component/TodoList';


function App() {

  // 프로그램 데이터 저장소 역할
  // useState : 리액트가 관리하는 공유 영역에 변수와 변수의 값을 변경하는 함수를 만들고 반환
  const [todos, setTodos] = useState([]); 

  return (
    <div className="App">
      <Header />
      <TodoEditor />
      <TodoList />
    </div>
  );
}

export default App;

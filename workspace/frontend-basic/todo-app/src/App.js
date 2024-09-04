import { useRef, useState } from 'react';

import './App.css';
import Header from './component/Header';
import TodoEditor from './component/TodoEditor';
import TodoList from './component/TodoList';

const mockTodos = [
  {
    id: 0,
    isDone: false,
    content: "세 번째 프로젝트 수행",
    createdDate: new Date().getTime(),
  },
  {
    id: 1,
    isDone: false,
    content: "이력서 및 자기소개서 작성",
    createdDate: new Date().getTime(),
  },
  {
    id: 2,
    isDone: false,
    content: "포트폴리오 문서 작성",
    createdDate: new Date().getTime(),
  },
];

function App() {

  const nextIdRef = useRef(3);

  // 프로그램 데이터 저장소 역할
  // useState : 리액트가 관리하는 공유 영역에 변수와 변수의 값을 변경하는 함수를 만들고 반환
  // const [todos, setTodos] = useState([]); 
  const [todos, setTodos] = useState(mockTodos); 

  const onCreate = (content) => {
    const todo = { 
      // "id": todos[todos.length-1].id + 1,
      "id": nextIdRef.current,       
      "content" : content, 
      "isDone": false, 
      "createdDate" : new Date().getTime() 
    };
    console.log(todo);
    setTodos([todo, ...todos]);
    nextIdRef.current += 1;
  }

  return (
    <div className="App">
      <Header />
      <TodoEditor onCreate={onCreate} />
      <TodoList todos={ todos } />
    </div>
  );
}

export default App;

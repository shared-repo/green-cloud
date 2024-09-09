import React, { useCallback, useMemo, useReducer, useRef } from 'react';

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
const makeMockTodos = () => {
  const todos = []
  for (let i = 0; i < 2500; i++) {
    todos.push({
      id: i + 1,
      isDone: false,
      content: `할 일 ${i + 1}`,      
      createdDate: new Date().getTime()
    });
  }
  return todos;
};

const reducer = (todos, action) => { // action : 요청된 작업의 종류
  switch (action.type) {
    case "CREATE": 
      return [action.newTodo, ...todos]; // reducer가 return하는 값이 새 state로 설정됨
      //return [...state, action.newTodo]; // reducer가 return하는 값이 새 state로 설정됨
    case "UPDATE":       
      return todos.map((todo) => {
        return todo.id === action.id ? { ...todo, isDone: !todo.isDone } : todo
      });
    case "DELETE": 
      return todos.filter(todo => todo.id !== action.id);
    default:
      return todos;
  }
}

export const TodoStateContext = React.createContext();
export const TodoDispatchContext = React.createContext();

function App() {

  const nextIdRef = useRef(3);

  // const [todos, setTodos] = useState(makeMockTodos()); 
  const [todos, dispatch] = useReducer(reducer, mockTodos);

  const onCreate = (content) => {
    const todo = { 
      "id": nextIdRef.current,       
      "content" : content, 
      "isDone": false, 
      "createdDate" : new Date().getTime() 
    };
    dispatch({
      type: 'CREATE',
      newTodo: todo
    });
    nextIdRef.current += 1;
  }

  // useCallback(func)      // mount(create), update할 때 함수 갱신
  // useCallback(func, [])  // mount(create)할 때 함수 갱신
  // useCallback(func, [a, b]) // a, b가 변경될 때 갱신
  const onDelete = useCallback( (id) => {
    // dispatch({type: "DELETE", id: id});    
    dispatch({type: "DELETE", id});    
  }, []);

  const onUpdate = useCallback( (id) => {
    // dispatch({type: "UPDATE", id: id});
    dispatch({type: "UPDATE", id});
  }, []);

  const memoizedHandler = useMemo(() => {
    return { onCreate, onUpdate, onDelete };
  }, [])

  return (
    <div className="App">
      <TodoStateContext.Provider value={ todos }>
        <TodoDispatchContext.Provider value={ memoizedHandler }>
          <Header />
          <TodoEditor />
          <TodoList />
        </TodoDispatchContext.Provider>
      </TodoStateContext.Provider>
    </div>
  );
}

export default App;

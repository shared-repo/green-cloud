import { Fragment } from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const name = "John Doe";
  const style = {
    backgroundColor: "green",
    color: "red"
  }
  return (
    <>
    <h1 style={ {backgroundColor:"orange"} }>Hello React JSX !!!</h1>
    <h1 style={style}>This is my second react page !!!!!</h1>
    <h1 className='my-style'
      // 여기는 주석
    >Hello, {name}</h1>
    {/* this is comment */}
    </>
  );
}

export default App;

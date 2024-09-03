import logo from './logo.svg';
import './App.css';

import ClassComponent from './ClassComponent';
import FunctionComponent from './FunctionComponent';
import ClassComponent2 from './ClassComponent2';
import FunctionComponent2 from './FunctionComponent2';

function App() {
  return (
    <>
      <br /><br />
      <h1>React Component Demo</h1>
      <hr />
      <ClassComponent email="johndoe@example.com" age="27" />
      <FunctionComponent email="johndoe@example.com" age="27" />
      <hr />
      <ClassComponent2 />
      <FunctionComponent2 />

    </>
  );
}

export default App;
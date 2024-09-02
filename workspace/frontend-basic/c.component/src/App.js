import logo from './logo.svg';
import './App.css';

import ClassComponent from './ClassComponent';
import FunctionComponent from './FunctionComponent';

function App() {
  return (
    <>
      <br /><br />
      <h1>React Component Demo</h1>
      <hr />
      <ClassComponent email="johndoe@example.com" age="27" />
      <FunctionComponent email="johndoe@example.com" age="27" />
      <FunctionComponent />
    </>
  );
}

export default App;

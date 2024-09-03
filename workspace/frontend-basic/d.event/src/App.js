import logo from './logo.svg';
import './App.css';
import ClassEventDemo from './component/ClassEventDemo';
import FunctionEventDemo from './component/FunctionEventDemo';

function App() {
  return (
    <div>
      <h2>Event Demo</h2>
      <hr />

      <ClassEventDemo />
      <br /><br />
      <FunctionEventDemo />
      
    </div>
  );
}

export default App;

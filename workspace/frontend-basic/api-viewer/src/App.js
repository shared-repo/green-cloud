import './App.css';
import { Route, Routes } from "react-router-dom";
import NewsPage from './components/NewsPage';

function App() {

  return (
    <div>
      <Routes>
        <Route path="/api/naver-news-viewer" element={ <NewsPage /> } exact={true} />
        <Route path="/api/naver-news-viewer/:category" element={ <NewsPage /> } />
      </Routes>
    </div>
  );
}

export default App;

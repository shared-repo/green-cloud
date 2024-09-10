import { useParams } from "react-router-dom";
import NewsCategories from "./NewsCategories";
import NewsList from "./NewsList";

const NewsPage = () => {

    const params = useParams(); // 요청 파라미터 읽기 hook
    // const category = params.category ? params.category : "all";
    const category = params.category || "all";

    return (
        <div>
            <NewsCategories />
            <NewsList category={ category } />
        </div>
    );

};

export default NewsPage;
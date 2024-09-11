import styled from 'styled-components';
import NewsItem from './NewsItem';
import axios from "axios";
import { useEffect, useState } from 'react';

const NewsListBlock = styled.div`
    box-sizing: border-box;
    padding-bottom: 3rem;
    width: 768px;
    margin: 0 auto;
    margin-top: 2rem;
    @media screen and (max-width: 768px) {
        width: 100%;
        padding-left: 1rem;
        padding-right: 1rem;
    }
`;

// const NewsList = (props) => {
//     const { category } = props;
const NewsList = ({category}) => {

    const [newsList, setNewsList] = useState(null);

    // 데이터 조회 -> 서버에 요청
    // 사용자가 현재 선택한 카테고리 관련 뉴스 조회
    // 페이지가 화면에 보여질때 실행
    useEffect( () => {
        const loadNews = async (e) => {

            const response = 
                await axios.get(`http://localhost:8080/api/naver-news?category=${category}`);
            // console.log(response);
            setNewsList(response.data.items);
        }
        loadNews();
    // }); // mount 할 때 함수 호출
    // }, []); // [] : mount + update 할 때 함수 호출
    }, [category]); // [category] : mount + category가 변경될 때 함수 호출

    if (!newsList) {
        return;
    }

    return (
        <NewsListBlock>
            {
                newsList.map( (news, idx) => {
                    return (
                        <NewsItem key={idx} news={news}></NewsItem>
                    )
                })
            }
        </NewsListBlock>
    );

};

export default NewsList;
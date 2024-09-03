import { useState } from "react";

const FunctionComponent2 = (props) => {
    const [ cnt, setCnt ] = useState(0); // 상태 관리 변수 및 함수 생성기
    return (
        <>
            <h2>{cnt}</h2>
            <button onClick={ () => { 
                setCnt(cnt + 1);
            } }>+</button>
            <button onClick={ () => { 
                setCnt(cnt - 1);
            } }>-</button>
        </>
    );
};

export default FunctionComponent2;
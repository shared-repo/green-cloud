import { Component } from "react";

class ClassComponent2 extends Component {

    constructor(props) {
        super(props);
        // this.state = 컴포넌트의 상태 데이터를 저장하는 특별한 객체
        this.state = {
            cnt: 0
        };
    }

    render() {
        const { cnt } = this.state;
        return (
            <>
                <h2>{cnt}</h2>
                <button onClick={ () => { 
                    // this.state.cnt++;
                    this.setState({cnt: cnt + 1});
                } }>+</button>
                <button onClick={ () => { 
                    // this.state.cnt--;
                    this.setState({cnt: cnt - 1});
                } }>-</button>
            </>
        );
    }

}

export default ClassComponent2;
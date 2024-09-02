import { Component } from "react";

class ClassComponent extends Component {

    static defaultProps = {
        name: "Anonymous",
        email: "anonymous@example.com",
        age: 0
    }
    // 화면이 갱신되어야 할 때 자동으로 호출되는 메서드
    render() {
        // console.log( this.props );
        // const email = this.props.email;
        // const age = this.props.age;
        const { name, email, age } = this.props;
        return (
            <div>
                <h1>This is class component</h1>
                <h2>[{this.props.name}][{this.props.email}][{this.props.age}]</h2>
                <h2>[{name}][{email}][{age}]</h2>
            </div>
        );
    }
}

export default ClassComponent; // 외부에서 사용할 수 있도록 개방
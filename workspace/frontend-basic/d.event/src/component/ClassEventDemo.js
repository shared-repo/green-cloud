import { Component } from "react";

class ClassEventDemo extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            message: ''
        };
    }

    render() {
        return (
            <div>
                <input value={ this.state.message } 
                       onChange={ (event) => {
                            this.setState({message: event.target.value});
                       }} />
                <button onClick={ (event) => {
                    console.log(this.state.message);
                    this.setState({message: ''});
                } }>전송</button>
            </div>
        );
    }

}

export default ClassEventDemo;
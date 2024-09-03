import { useState } from "react";

const FunctionEventDemo = (props) => {

    const [name, setName] = useState('');
    const [message, setMessage] = useState('');

    const changeHandler = (event) => {
        if (event.target.name === 'name') {
            setName(event.target.value);
        } else {
            setMessage(event.target.value);
        }
    };
    const clickHandler = (event) => {
        console.log(`[${name}]:[${message}]`);
        setName('');
        setMessage('');
    };

    return (
        <div>
            <input  name="name"
                    value={ name } 
                    onChange={ changeHandler } />
            <input  name="message"
                    value={ message } 
                    onChange={changeHandler } />
            <button onClick={ clickHandler }>전송</button>
        </div>
    );

};

export default FunctionEventDemo;
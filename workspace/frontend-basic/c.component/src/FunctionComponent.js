// const FunctionComponent = (props) => {
//     const { name, email, age } = props;
//     return (
//         <div>
//             <h1>This is function component</h1>
//             <h2>[{name}][{email}][{age}]</h2>
//         </div>
//     );

// }

const FunctionComponent = ({ name, email, age }) => {
    return (
        <div>
            <h1>This is function component</h1>
            <h2>[{name}][{email}][{age}]</h2>
        </div>
    );
}

FunctionComponent.defaultProps = {
    name: "Anonymous",
    email: "anonymous@example.com",
    age: 0
}

export default FunctionComponent;
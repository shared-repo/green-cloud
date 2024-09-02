function f() {
    console.log("this is function a");
}
f();

const fc = f;
fc();

const f2 = function() {
    console.log("this is function b");
}
f2();

const f3 = () => { console.log("this is function c"); }
f3();

// const f4 = (name) => { return `hello ${ name }` };
// const f4 = (name) => `hello ${ name }`;
const f4 = name => `hello ${ name }`;
const greetings = f4("John Doe");
console.log(greetings);

const f5 = (a, b) => a + b;
console.log( f5(10, 20) );
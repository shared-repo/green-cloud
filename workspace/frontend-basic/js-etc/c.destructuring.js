const ar = [1, 2, 3, 4, 5];

const a1 = ar[0];
const a2 = ar[1];
const a3 = ar[2];

console.log(a1 + a2 + a3);

const [b1, b2, b3] = ar; // 배열 디스트럭처링
console.log(b1 + b2 + b3);

const [c1, ,c3, ,c5] = ar; // 배열 디스트럭처링
console.log(c1 + c3 + c5);

// const [d1, d2, d3 = 10] = [1, 2]; // 배열 디스트럭처링
const [d1, d2, d3 = 10] = [1, 2, 3]; // 배열 디스트럭처링
console.log(d1 + d2 + d3);

////////////////////////

const person = { "name": "John Doe", "age": 27, "email" : "johndoe@example.com" };
const name = person.name;
const age = person.age;
const email = person.email;
console.log(`[${name}][${age}][${email}]`);

const person2 = { "name2": "John Doe", "age2": 27, "email2" : "johndoe@example.com" };
const { name2, age2, email2 } = person2; // 변수 이름은 객체의 key와 같아야 합니다.
console.log(`[${name2}][${age2}][${email2}]`);

const person3 = { "name3": "John Doe", "age3": 27, "email3" : "johndoe@example.com" };
const { name3, age3 } = person3; // 변수 이름은 객체의 key와 같아야 합니다.

console.log(`[${name3}][${age3}]`);

const person4 = { 
    "name4": "John Doe", 
    "age4": 27, 
    "email4": "johndoe@example.com",
    "phone4": {
        "mobile": "010-6589-1124",
        "office": "02-5421-9852"
    }
 };
const { name4, phone4 : { mobile } } = person4;
console.log(name4);
console.log(mobile);
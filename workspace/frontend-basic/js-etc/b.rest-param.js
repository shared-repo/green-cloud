function f1(n1, n2) { return n1 + n2; }
console.log( f1(10, 20) );

function f2(...numbers) { // rest parameter
    console.log(numbers);
}
f2(1, 2, 3, 4, 5);
f2(10, 20, 600, 34);

function f3(n1, n2, ...numbers) { // rest parameter
    console.log(numbers);
}

f3(1, 2, 3, 4, 5);

//////////////////////////////////////////////////////

function f4(n1, n2, n3, n4, n5) {
    return n1 + n2 + n3 + n4 + n5;
}
const ar = [1, 2, 3, 4, 5];
console.log( f4(ar) );
console.log( f4(ar[0], ar[1], ar[2], ar[3], ar[4]) );
console.log( f4(...ar) ); // ...배열 : 배열을 분해해서 개별 값의 목록으로 변환

console.log([1, 2, 3]);
console.log(...[1, 2, 3]);

function f5(...numbers) {
    console.log(numbers);
}
f5(10, 20, ...[40, 50, 60], 70, ...[30, 20]);

ar1 = [1, 2, 3];
ar2 = [4, 5, 6];
// 두 배열을 병합 -> 한 개의 배열로 만들기
ar3 = [...ar1, ...ar2];
console.log(ar3);

obj = { a: 10, b: 20, c: 30 };

obj2 = { ...obj, d:40 } // ...obj : { a: 10, b: 20, c: 30 } => a:10, b:20, c:30
console.log(obj2);

obj3 = { ...obj, a: 40 } // 기존 항목과 중복되는 항목이 추가되면 기존 항목 변경
console.log(obj3);

obj4 = { a: 40, ...obj } // 기존 항목과 중복되는 항목이 추가되면 기존 항목 변경
console.log(obj4);


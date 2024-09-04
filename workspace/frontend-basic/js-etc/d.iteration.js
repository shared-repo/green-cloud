const persons = [
    { "id": 1, "name": "한석규"},
    { "id": 2, "name": "송강호"},
    { "id": 3, "name": "김윤석"},
    { "id": 4, "name": "최우식"},
    { "id": 5, "name": "최민식"},
];

const names = []
for (let i = 0; i < persons.length; i++) {
    // console.log(persons[i].name);
    names.push(persons[i].name);
}
console.log(names);

const names2 = persons.map( (person) => { 
                                            // console.log(person.name); 
                                            return person.name 
                                        });
console.log(names2);

const top3 = persons.filter( (person) => { return person.id <= 3 });
console.log(top3);
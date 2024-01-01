//Removing Duplicates

let array = [100,23,23,23,23,23,67,45];
let outputArray = [];
let flag = false;
for(j=0; j<array.length; j++){
    for(k=0; k<outputArray.length; k++){
        if(array[j] == outputArray[k]){
            flag = true;
        }
    }
    if(flag == false){
        outputArray.push(array[j]);
    }
    flag = false;
}

console.log("Output Array  == > "+outputArray);

// Pro

let arr = [100,23,23,23,23,67,45,45];
let outputArr = Array.from(new Set(arr));

console.log("PRO Output Array ==> "+outputArr);


// (2)  Dynamic Properties in Objects

let dynamic = "value";
let user = {
    id: 1,
}
user[dynamic] = "other value";

// Pro way
let userDynamic = {
    id: 1,
    [dynamic] : "Other Values",
}

console.log(userDynamic);


// (3) The Ternary Operation

let hungry = true;
let eat = hungry == true ? 'yes' : 'no';
console.log(eat);


// (4) Object to Array

let numbers = {
    one: 1,
    two: 2,
}

let key = Object.keys(numbers);  //Output : [ 'one', 'two' ]
let value = Object.values(numbers);  // Output : [ 1, 2 ]
let entry = Object.entries(numbers);  // Output : [ [ 'one', 1 ], [ 'two', 2 ] ]


// (5) Array to Object

let arr1 = ["value1", "value2", "value3"];
let arrObject = {...arr1};  // Output: { '0': 'value1', '1': 'value2', '2': 'value3' }

function listOfEmployees(input) {
    let employeesList = {};

    for (let name of input) {
        let personalNumber = name.length;
        employeesList[name] = personalNumber;
    }

    for (const key in employeesList) {
        console.log(`Name: ${key} -- Personal Number: ${employeesList[key]}`)
    }
}
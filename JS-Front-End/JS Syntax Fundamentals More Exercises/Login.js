function solve(input) {
    let username = input[0];
    let password = username.split("").reverse().join("");

    for (let i = 1; i < input.length; i++) {
        let login = input[i];
        if (password === login) {
            console.log(`User ${username} logged in.`);
            break;
        } else if (i === 4) {
            console.log(`User ${username} blocked!`)
            break;
        } else {
            console.log('Incorrect password. Try again.');
        }
    }
}
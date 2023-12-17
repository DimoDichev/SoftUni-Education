function checkPassword(password) {
    const isValidLength = (pass) => pass.length >= 6 && pass.length <= 10;
    const isOnlyLetterAndDigits = (pass) => /^\w+$/g.test(pass);
    const isContainAtLeastTwoDigits = (pass) => [...pass.matchAll(/\d/g)].length >= 2;
    let isValidPassword = true;

    if(!isValidLength(password)) {
        console.log('Password must be between 6 and 10 characters');
        isValidPassword = false;
    }

    if (!isOnlyLetterAndDigits(password)) {
        console.log('Password must consist only of letters and digits');
        isValidPassword = false;
    }

    if (!isContainAtLeastTwoDigits(password)) {
        console.log('Password must have at least 2 digits');
        isValidPassword = false;
    }

    if (isValidPassword) {
        console.log('Password is valid');
    }

}
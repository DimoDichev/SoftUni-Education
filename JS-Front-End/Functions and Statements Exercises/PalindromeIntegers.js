function palindrome(numbers) {
    numbers.forEach(num => console.log(isPalindrome(num)));

    function isPalindrome(number) {
        let palindromeNum = Number(number.toString().split('').reverse().join(''));
        return number === palindromeNum;
    }
}
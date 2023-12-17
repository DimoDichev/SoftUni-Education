function deleteByEmail() {
    let result = document.getElementById('result')
    let email = document.getElementsByName("email")[0].value;
    let list = Array.from(document.querySelectorAll('#customers tr td:nth-child(2)'));
    let isFound = list.find((e) => e.textContent === email)
    
    if (isFound) {
        isFound.parentElement.remove();
        result.textContent = 'Deleted.';
    } else {
        result.textContent = 'Not found.'
    }
}
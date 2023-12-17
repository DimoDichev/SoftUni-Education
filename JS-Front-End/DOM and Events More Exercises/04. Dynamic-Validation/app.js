function validate() {
    const email = document.getElementById('email');

    email.addEventListener('change', validateEmail);

    function validateEmail(event) {
        const output = email.value.match(/^[a-z]+@[a-z]+\.[a-z]+$/)
        if (!output) {
            email.classList.add('error')
        } else {
            email.classList.remove('error')
        }
    }
}
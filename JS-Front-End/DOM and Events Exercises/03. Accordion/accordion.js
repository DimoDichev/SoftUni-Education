function toggle() {
    let btn = Array.from(document.getElementsByClassName('button'))[0];
    let btnText = btn.textContent;
    let text = document.getElementById('extra');

    if (btnText === 'More') {
        text.style.display = 'block';
        btn.textContent = 'Less'
    } else {
        text.style.display = 'none';
        btn.textContent = 'More';
    }
}
function lockedProfile() {
    let btns = Array.from(document.getElementsByTagName('button'));

    for (const btn of btns) {
        btn.addEventListener('click', toggleInformation)
    }

    function toggleInformation(e) {
        let btn = e.currentTarget;
        let childrens = Array.from(btn.parentElement.children);
        let unlock = childrens[4];
        let hiddenInformation = childrens[9];

        if (unlock.checked) {
            if (btn.textContent === 'Show more') {
                hiddenInformation.style.display = 'block';
                btn.textContent = 'Hide it';
            } else {
                hiddenInformation.style.display = 'none';
                btn.textContent = 'Show more';
            }
        }
    }
}
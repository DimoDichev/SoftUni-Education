function focused() {
  let inputs = Array.from(document.getElementsByTagName("input"));

  for (const input of inputs) {
    input.addEventListener('focus', (e) => {
      let div = e.target.parentNode;
      div.classList.add('focused');
    })

    input.addEventListener('blur', (e) => {
      let div = e.target.parentNode;
      div.classList.remove('focused');
    })
  }
}
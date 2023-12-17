function loadCommits() {
    let username = document.getElementById('username').value;
    let repo = document.getElementById('repo').value;
    let url = `https://api.github.com/repos/${username}/${repo}/commits`
    let commitsContainer = document.getElementById('commits');

    commitsContainer.innerHTML = '';

    fetch(url)
        .then((res) => res.json())
        .then((data) => {
            for (const line of data) {
                let newLi = document.createElement('li');
                let name = line.commit.author.name;
                let message = line.commit.message;
                newLi.textContent = `${name}: ${message}`;
                commitsContainer.appendChild(newLi);
            }
        })
        .catch((err) => {
            console.log(err.status)
        })
}
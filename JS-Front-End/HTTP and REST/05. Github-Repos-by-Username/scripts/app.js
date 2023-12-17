function loadRepos() {
	const baseURL = 'https://api.github.com/users/';
	let usernameID = document.getElementById('username').value;
	let reposList = document.getElementById('repos');

	reposList.innerHTML = '';

	fetch(baseURL + usernameID + '/repos')
		.then((res) => res.json())
		.then((data) => {
			for (const line of data) {
				let li = document.createElement('li');
				let a = document.createElement('a');
				a.href = line.html_url;
				a.textContent = line.full_name;
				li.appendChild(a);
				reposList.appendChild(li);
			}
		})
		.catch((err) => console.error(err));
}
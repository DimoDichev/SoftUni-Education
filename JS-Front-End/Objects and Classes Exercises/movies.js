function movies(input) {
    let moviesArr = [];

    for (const line of input) {
        let movieName = '';

        if (line.includes('addMovie')) {
            movieName = line.split(' ').slice(1).join(' ');
            let movie = {
                name: movieName
            }
            moviesArr.push(movie);
        } else if (line.includes('directedBy')) {
            movieName = line.split(' directedBy ')[0];
            let director = line.split(' directedBy ')[1];
            if (moviesArr.find((m) => m.name === movieName)) {
                let movie = moviesArr.find((m) => m.name === movieName);
                movie.director = director;
            }
        } else if (line.includes('onDate')) {
            movieName = line.split(' onDate ')[0];
            let date = line.split(' onDate ')[1];
            if (moviesArr.find((m) => m.name === movieName)) {
                let movie = moviesArr.find((m) => m.name === movieName);
                movie.date = date;
            }
        }
    }

    for (const movie of moviesArr) {
        if (movie.name && movie.director && movie.date) {
            console.log(JSON.stringify(movie));
        }
    }
}
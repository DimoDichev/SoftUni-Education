function songs(info) {
    class Song {
        constructor(typeList, name, time) {
            this.typeList = typeList;
            this.name = name;
            this.time = time;
        }
    }

    let songs = [];
    let numbersOfSongs = info.shift();
    let typeSong = info.pop();

    for (let i = 0; i < numbersOfSongs; i++) {
        let [ type, name, time ] = info[i].split('_');
        let song = new Song(type, name, time);
        songs.push(song);
    }

    if (typeSong === 'all') {
        songs.forEach((s) => console.log(s.name));
    } else {
        let filteredSongs = songs.filter((s) => s.typeList === typeSong);
        filteredSongs.forEach((s) => console.log(s.name));
    }
}
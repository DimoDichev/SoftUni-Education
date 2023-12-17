function heroesCreator(input) {
    let herosMap = [];

    for (const line of input) {
        let tokens = line.split(' / ');
        let name = tokens[0];
        let level = Number(tokens[1]);
        let items = tokens[2];

        let hero = {
            name,
            level,
            items
        }

        herosMap.push(hero);
    }

    herosMap.sort((heroA, heroB) => heroA.level - heroB.level);

    for (const hero of herosMap) {
        console.log(`Hero: ${hero.name}`);
        console.log(`level => ${hero.level}`);
        console.log(`items => ${hero.items}`);
    }
}
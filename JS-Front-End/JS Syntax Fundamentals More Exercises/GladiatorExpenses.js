function solve(fightCount, helmet, sword, shield, armor) {
    let price = 0;

    for (let i = 1; i <= fightCount ; i++) {
        if (i % 2 === 0) {
            price += helmet;
        }
        if (i % 3 === 0) {
            price += sword;
        }
        if (i % 6 === 0) {
            price += shield;
        }
        if (i % 12 === 0) {
            price += armor;
        }
    }
    
    console.log(`Gladiator expenses: ${price.toFixed(2)} aureus`);
}
function solve(base, increment) {
    let stone = 0;
    let marble = 0;
    let lapisLazuli = 0;
    let gold = 0;
    let floor = 1;

    for (let i = base; i > 0; i-=2) {
        if ((i - 2) <= 0) {
            gold = i * i * increment;
            break;
        }

        stone += ((i - 2) ** 2) * increment;

        if (floor % 5 === 0) {
            lapisLazuli += ((i * 4) - 4) * increment;
        } else {
            marble += ((i * 4) - 4) * increment;
        }

        floor++;
    }

    console.log(`Stone required: ${Math.ceil(stone)}`);
    console.log(`Marble required: ${Math.ceil(marble)}`);
    console.log(`Lapis Lazuli required: ${Math.ceil(lapisLazuli)}`);
    console.log(`Gold required: ${Math.ceil(gold)}`);
    console.log(`Final pyramid height: ${Math.floor(floor * increment)}`);
}
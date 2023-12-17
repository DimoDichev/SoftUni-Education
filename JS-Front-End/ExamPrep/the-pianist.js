function solve(input) {
    let numberOfPiece = input.shift();

    let pieceList = {};

    for (let i = 0; i < numberOfPiece; i++) {
        let [piece, composer, key] = input.shift().split('|');
        pieceList[piece] = { composer, key };
    }

    for (const line of input) {
        let tokens = line.split('|');
        let currentCommand = tokens.shift();
        switch (currentCommand) {
            case 'Add':
                addCommand(tokens);
                break;
            case 'Remove':
                removeCommand(tokens);
                break;
            case 'ChangeKey':
                changeKeyCommand(tokens);
                break
        }
    }

    for (const piece in pieceList) {
        let { composer, key } = pieceList[piece];
        console.log(`${piece} -> Composer: ${composer}, Key: ${key}`);
    }

    function changeKeyCommand(input) {
        let [piece, newKey] = input;
        if (pieceList.hasOwnProperty(piece)) {
            pieceList[piece].key = newKey;
            console.log(`Changed the key of ${piece} to ${newKey}!`)
        } else {
            console.log(`Invalid operation! ${piece} does not exist in the collection.`)
        }
    }

    function removeCommand(piece) {
        if (pieceList.hasOwnProperty(piece)) {
            delete pieceList[piece];
            console.log(`Successfully removed ${piece}!`)
        } else {
            console.log(`Invalid operation! ${piece} does not exist in the collection.`)
        }
    }

    function addCommand(input) {
        let [piece, composer, key] = input;
        if (!pieceList.hasOwnProperty(piece)) {
            pieceList[piece] = { composer, key };
            console.log(`${piece} by ${composer} in ${key} added to the collection!`);
        } else {
            console.log(`${piece} is already in the collection!`);
        }
    }
}
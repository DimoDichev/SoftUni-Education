function solve(lists) {
    lists = lists.map((el) => JSON.parse(el));
    lists.forEach((l) => l.sort((a, b) => b - a));

    let result = [];

    for (let i = 0; i < lists.length; i++) {
        let currentList = lists[i];
        let isUnique = true;

        for (let l = 0; l < result.length; l++) {
            let curentResultList = result[l];

            if(curentResultList.toString() === currentList.toString()) {
                isUnique = false;
            }
        }

        if (isUnique) {
            result.push(currentList);
        }
    }

    result.sort((a, b) => a.length - b.length)

    result.forEach((el) => console.log('[' + el.join(', ') + ']'))
}
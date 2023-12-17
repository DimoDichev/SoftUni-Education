function search() {
   let inputs = Array.from(document.querySelectorAll('#towns li'));
   let searchText = document.getElementById('searchText').value;
   let result = document.getElementById('result')

   inputs.forEach((line) => {
      line.style.fontWeight = "normal";
      line.style.textDecoration = 'none';
   })

   let matches = 0;

   inputs.forEach((line) => {
      let town = line.textContent;
      if (town.includes(searchText)) {
         line.style.fontWeight = "bold";
         line.style.textDecoration = 'underline';
         matches++;
      }
   })

   result.textContent = `${matches} matches found`
}
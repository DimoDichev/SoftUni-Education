function solve() {
   document.querySelector('#searchBtn').addEventListener('click', onClick);

   function onClick() {
      let searchWord = document.getElementById('searchField').value;
      let table = Array.from(document.querySelectorAll('tbody tr'));

      for (const row of table) {
         let text = row.textContent;
         
         if(row.classList.contains('select')) {
            row.classList.remove('select');
         }

         if (text.includes(searchWord)) {
            row.classList.add('select')
         }
      }

      document.getElementById('searchField').value = '';
   }
}
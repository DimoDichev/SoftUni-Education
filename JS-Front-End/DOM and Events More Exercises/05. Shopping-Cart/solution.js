function solve() {
   let addBtns = Array.from(document.querySelectorAll('.add-product'));
   let checkoutBtn = document.querySelector('.checkout');
   let textarea = document.querySelector('textarea');

   let totalPrice = 0;
   let productList = [];

   addBtns.forEach((btn) => btn.addEventListener('click', addHandler));
   checkoutBtn.addEventListener('click', checkoutHandler)

   function checkoutHandler() {
      textarea.textContent += `You bought ${productList.join(', ')} for ${totalPrice.toFixed(2)}.`
      addBtns.forEach((btn) => btn.setAttribute('disabled', true));
      checkoutBtn.setAttribute('disabled', true);
   }

   function addHandler() {
      let productClass = this.parentNode.parentNode;
      let productName = productClass.querySelector('.product-title').textContent;
      let productPrice = productClass.querySelector('.product-line-price').textContent

      totalPrice += Number(productPrice);

      let isUnique = true;
      productList.forEach((p) => {
         if (p === productName) {
            isUnique = false;
         }
      })
      if (isUnique) {
         productList.push(productName);
      }

      textarea.textContent += `Added ${productName} for ${productPrice} to the cart.\n`
   }
}
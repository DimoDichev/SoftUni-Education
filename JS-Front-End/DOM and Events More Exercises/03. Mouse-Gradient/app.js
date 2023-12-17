function attachGradientEvents() {
    const gradient = document.getElementById('gradient');
    const result = document.getElementById('result');

    gradient.addEventListener('mouseout', hidePercentage);
    gradient.addEventListener('mousemove', showPercentage);
    

    function showPercentage(event) {
        const x = event.offsetX
        const width = gradient.clientWidth
        const percentage = parseInt((x / width) * 100)
        result.textContent = `${percentage}%`
    }
    
    function hidePercentage() {
        result.textContent = '';
    }
}
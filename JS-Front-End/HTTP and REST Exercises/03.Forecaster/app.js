function attachEvents() {
    let BASE_URL_LOCATION = 'http://localhost:3030/jsonstore/forecaster/locations';
    let CURENT_CONDITION_URL = 'http://localhost:3030/jsonstore/forecaster/today/';
    let THREE_DAY_CONDITION_URL = 'http://localhost:3030/jsonstore/forecaster/upcoming/';
    const textLocation = document.getElementById('location');
    const submitBtn = document.getElementById('submit');
    const forecastContainer = document.getElementById('forecast');
    const currentForecast = document.getElementById('current');
    const upcomingForecast = document.getElementById('upcoming');

    const weatherSymbol = {
        "Sunny": '&#x2600',
        "Partly sunny": '&#x26C5',
        "Overcast": '&#x2601',
        "Rain": '&#x2614',
        "Degrees": '&#176'
    }

    submitBtn.addEventListener('click', loadWeatherHandler);

    function loadWeatherHandler() {
        let currentCity = textLocation.value;
        let currentCode = '';

        fetch(BASE_URL_LOCATION)
            .then((res) => res.json())
            .then((data) => {
                forecastContainer.style.display = 'block';
                for (const city in data) {
                    if (data[city].name === currentCity) {
                        currentCode = data[city].code
                    }
                }

                fetch(`${CURENT_CONDITION_URL}${currentCode}`)
                    .then((res) => res.json())
                    .then((data) => {
                        let cityName = data.name;
                        let { condition, high, low } = data.forecast;
                        let currentForecastsContainer = createElement('div', currentForecast, '', ['forecasts']);
                        createElement('span', currentForecastsContainer, weatherSymbol[condition], ['condition', 'symbol'], null, null, true);
                        let spanConditionContainer = createElement('span', currentForecastsContainer, '', ['condition']);
                        createElement('span', spanConditionContainer, cityName, ['forecast-data']);
                        createElement('span', spanConditionContainer, `${low}${weatherSymbol.Degrees}/${high}${weatherSymbol.Degrees}`, ['forecast-data'], null, null, true);
                        createElement('span', spanConditionContainer, condition, ['forecast-data']);
                    })
                    .catch((err) => console.error(err));

                fetch(`${THREE_DAY_CONDITION_URL}${currentCode}`)
                .then((res) => res.json())
                .then((data) => {
                    let forecastThreeDayContainer = createElement('div', upcomingForecast, '', ['forecast-info']);
                    for (const day of data.forecast) {
                        let { condition, high, low } = day;
                        let dayUpcomingContainer = createElement('span', forecastThreeDayContainer, '', ['upcoming']);
                        createElement('span', dayUpcomingContainer, weatherSymbol[condition], ['symbol'], null, null, true);
                        createElement('span', dayUpcomingContainer, `${low}${weatherSymbol.Degrees}/${high}${weatherSymbol.Degrees}`, ['forecast-data'], null, null, true);
                        createElement('span', dayUpcomingContainer, condition, ['forecast-data']);
                    }
                })
                .catch((err) => console.error(err));

            })
            .catch((err) => console.error(err));
    }

    function createElement(type, parentNode, content, classes, id, attributes, useInnerHtml) {
        const htmlElement = document.createElement(type);

        if (content && useInnerHtml) {
            htmlElement.innerHTML = content;
        } else if (content && type !== 'input') {
            htmlElement.textContent = content;
        } else if (content && type === 'input') {
            htmlElement.value = content;
        }

        // [ ]
        if (classes && classes.length > 0) {
            htmlElement.classList.add(...classes);
        }

        if (id) {
            htmlElement.id = id;
        }

        // {src: 'link', href: 'http'}
        if (attributes) {
            for (let key in attributes) {
                htmlElement[key] = attributes[key];
            }
        }

        if (parentNode) {
            parentNode.appendChild(htmlElement);
        }

        return htmlElement;
    }
}

attachEvents();
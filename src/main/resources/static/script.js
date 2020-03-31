const API_URL = `${window.origin}/url-info`;

const sendRequest = async targetUrl => {
    const url = `${API_URL}?targetUrl=${targetUrl}`;
    const response = await fetch(url, {
        method: 'GET',
    });
    return await response.json();
};

window.onload = () => {
    const urlList = [];

    document.querySelectorAll('[data-url]').forEach(element => {
        urlList.push({
            url: element.dataset.url,
            titleElem: element.querySelector('[data-title]'),
        });
    });

    Promise.all(urlList.map(async requestInfo => {
        const urlInfo = await sendRequest(requestInfo.url);
        requestInfo.titleElem.textContent = urlInfo.title;
    }));
};


const API_URL = `${window.origin}/droplets`;
let readList;

const getClickListener = id => async event => {
    try {

        await fetch(`${API_URL}/${id}`, { method: 'POST' });
        const listElement = event.target.closest('li');
        readList.appendChild(listElement);

    } catch(e) {
        console.log(e)
    }
};

const setupNavLinks = () => {
    const activePage = document.querySelector('[data-active-page').dataset.activePage;
    const activePageLink = document.querySelector(`[data-link="${activePage}"]`);

    if (activePageLink) {
        activePageLink.classList.add('active');
    }
};

window.onload = () => {
    setupNavLinks();

    readList = document.querySelector('[data-list="read"]');

    document.querySelectorAll('[data-action="read"]').forEach(element => {
        element.addEventListener('click', getClickListener(element.dataset.id));
    });
};

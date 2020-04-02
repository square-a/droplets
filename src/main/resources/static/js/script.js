const API_URL = `${window.origin}/droplets`;

const getClickListener = id => async event => {
    try {

        await fetch(`${API_URL}/${id}`, { method: 'POST' });
        const listElement = event.target.closest('li');
        listElement.parentNode.removeChild(listElement);

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

    document.querySelectorAll('[data-action="read"]').forEach(element => {
        element.addEventListener('click', getClickListener(element.dataset.id));
    });
};

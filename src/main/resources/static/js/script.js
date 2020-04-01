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

window.onload = () => {
    readList = document.querySelector('[data-list="read"');

    document.querySelectorAll('[data-action="read"]').forEach(element => {
        element.addEventListener('click', getClickListener(element.dataset.id));
    });
};

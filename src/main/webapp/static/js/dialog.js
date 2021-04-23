let deleteButtons = document.getElementsByClassName('delete-button')

for (let i = 0; i < deleteButtons.length; i++) {
    deleteButtons[i].addEventListener('click', function (event) {
        const proceed = confirm("Are you sure you want to delete this film?");
        if (!proceed) {
            event.preventDefault()
        }
    })
}

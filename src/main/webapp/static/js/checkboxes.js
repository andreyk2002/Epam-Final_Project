let checkboxes = document.getElementsByClassName('film-checkbox')
for (let i = 0; i < checkboxes.length; i++) {
    checkboxes[i].addEventListener('change', function (event) {
        if (hasChecked()) {
            for (let j = 0; j < checkboxes.length; j++) {
                checkboxes[j].removeAttribute('required');
            }
        } else {
            for (let j = 0; j < checkboxes.length; j++) {
                checkboxes[j].setAttribute('required', 'required');
            }
        }
    });
}

function hasChecked() {
    for (let i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            return true;
        }
    }
    return false;
}
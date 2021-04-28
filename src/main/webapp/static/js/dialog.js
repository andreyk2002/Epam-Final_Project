let messages = new Map()
messages["en_US"] = "Are you sure to delete selected film?"
messages["ru_RU"] = "Вы действительно хотите удалить выбранный фильм?"
messages["be_BY"] = "Вы упэўнены, што трэба удаліць выбраны фільм?"

const defaultLocale = "en_US"
let deleteButtons = document.getElementsByClassName('delete-button')
let currentLocale = document.getElementsByName("locale")[0].value

for (let i = 0; i < deleteButtons.length; i++) {
    deleteButtons[i].addEventListener('click', function (event) {
        let message = currentLocale in messages ? messages[currentLocale] : messages[defaultLocale]
        const proceed = confirm(message);
        if (!proceed) {
            event.preventDefault()
        }
    })
}

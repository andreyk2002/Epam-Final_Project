const ACTIVE_TAG = "header-active"
let header = document.querySelector('header')
window.onscroll = () => {
    if(window.pageYOffset > 50){
        header.classList.add(ACTIVE_TAG)
    }else {
        header.classList.remove(ACTIVE_TAG)
    }
}

let cross = document.getElementById("cross")
let menu = document.querySelector("nav")
let menuBtn = document.getElementById("open-menu")
cross.addEventListener( 'click', e =>  menu.classList.remove("menu-active"))
menuBtn.addEventListener( 'click', e =>  menu.classList.add("menu-active"));


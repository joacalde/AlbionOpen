const toggleButton = document.getElementsByClassName('checkbtn')[0]
const navbarLinks = document.getElementsByClassName('leftnav')[0]

toggleButton.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const hnav = document.getElementsByClassName('hnav')[0]
hnav.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const representacionesnav = document.getElementsByClassName('representacionesnav')[0]
representacionesnav.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const serviciosnav = document.getElementsByClassName('serviciosnav')[0]
serviciosnav.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const contactarnav = document.getElementsByClassName('contactarnav')[0]
contactarnav.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const hnosotros = document.getElementsByClassName('hnosotros')[0]
hnosotros.addEventListener('click',()=> {
    navbarLinks.classList.toggle('active')
})

const lineasnav = document.getElementsByClassName('lineasnav')[0]
const leftnav2 = document.getElementsByClassName('leftnav2')[0]
const cancelleftnav2 = document.getElementsByClassName('cancelleftnav2')[0]
lineasnav.addEventListener('click',()=> {
    leftnav2.classList.toggle('active')
})
cancelleftnav2.addEventListener('click',()=> {
    leftnav2.classList.toggle('active')
})

const cardsmas = document.getElementsByClassName('cardsmas')[0]

const mas = document.getElementsByClassName('mas')[0]
mas.addEventListener('click',()=> {
    cardsmas.classList.toggle('active')
    mas.classList.toggle('active')
    menos.classList.toggle('active')
})

const menos = document.getElementsByClassName('menos')[0]
menos.addEventListener('click',()=> {
    cardsmas.classList.toggle('active')
    mas.classList.toggle('active')
    menos.classList.toggle('active')
})

const testimonio1 = document.getElementsByClassName('testimonio1')[0]
testimonio1.classList.toggle('active')
const testimonio2 = document.getElementsByClassName('testimonio2')[0]
const testimonio3 = document.getElementsByClassName('testimonio3')[0]
const testimonio4 = document.getElementsByClassName('testimonio4')[0]
const testimoniosizq = document.getElementsByClassName('testimoniosizq')[0]
const testimoniosder = document.getElementsByClassName('testimoniosder')[0]

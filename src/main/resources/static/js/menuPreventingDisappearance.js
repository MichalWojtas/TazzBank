const dropContainer = document.getElementById("dropdown-menu-1");
const logoutLink = document.getElementById("logoutBottomOfMenu");

dropContainer.addEventListener("click", function(event) {
    /*if(event.target === logoutLink){
        return;
    }*/
    //event.preventDefault();
    event.stopPropagation();
});
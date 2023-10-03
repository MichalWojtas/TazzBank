const addFundsDropdown = document.getElementById("addFundsDropdown");

//Avoid same variable names for all js files, its makes problems
addFundsDropdown.addEventListener("click", function(event) {
    event.stopPropagation();
});
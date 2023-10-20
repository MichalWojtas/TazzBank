function toggleDarkMode() {

    const body = document.body;
    const welcomeText = document.getElementById('welcomeText');

    const isDarkMode = body.classList.contains('dark-mode');
    const isDarkModeWelcome = welcomeText.classList.contains('dark-mode');

    if (isDarkMode) {
        body.classList.remove('dark-mode');
        document.documentElement.style.setProperty('--circleButtonsBackgroundColor', '#090909');
        document.documentElement.style.setProperty('--circleButtonsHoverFilter', 'drop-shadow(0px 0px 10px black)');
        document.documentElement.style.setProperty('--dropdownMenu1BorderColor', '4px solid #111');
        document.documentElement.style.setProperty('--dropdownMenu1FilterShadow', 'drop-shadow(12px 12px 12px black)');
        document.documentElement.style.setProperty('--dropdownMenuBorderColor', '4px solid #111');
        document.documentElement.style.setProperty('--dropdownMenuFilterShadow', 'drop-shadow(12px 12px 12px black)');
        document.documentElement.style.setProperty('--mainSeparateFormBorderColor', '4px solid #111');
        document.documentElement.style.setProperty('--mainSeparateFormFilterShadow', 'drop-shadow(12px 12px 12px black)');

        localStorage.setItem('darkMode', 'false'); // Save in localStorage
    } else {
        body.classList.add('dark-mode');
        document.documentElement.style.setProperty('--circleButtonsBackgroundColor', 'white');
        document.documentElement.style.setProperty('--circleButtonsHoverFilter', 'drop-shadow(0px 0px 10px white)');
        document.documentElement.style.setProperty('--dropdownMenu1BorderColor', '4px solid white');
        document.documentElement.style.setProperty('--dropdownMenu1FilterShadow', 'drop-shadow(12px 12px 12px white)');
        document.documentElement.style.setProperty('--dropdownMenuBorderColor', '4px solid white');
        document.documentElement.style.setProperty('--dropdownMenuFilterShadow', 'drop-shadow(12px 12px 12px white)');
        document.documentElement.style.setProperty('--mainSeparateFormBorderColor', '4px solid white');
        document.documentElement.style.setProperty('--mainSeparateFormFilterShadow', 'drop-shadow(12px 12px 12px white)');
        localStorage.setItem('darkMode', 'true');

    }

    if(isDarkModeWelcome){
        welcomeText.classList.remove('dark-mode');
        localStorage.setItem('darkMode','false');
    }else{
        welcomeText.classList.add('dark-mode');
        localStorage.setItem('darkMode','true');
    }

    var elementsTransactionAndAmountOnAccounts = document.getElementsByClassName("transactionAndAmountOnAccounts");
    for (var i = 0; i < elementsTransactionAndAmountOnAccounts.length; i++) {
        var elementTransactionAndAmountOnAccounts = elementsTransactionAndAmountOnAccounts[i];
        if (elementTransactionAndAmountOnAccounts.classList.contains('dark-mode')) {
            elementTransactionAndAmountOnAccounts.classList.remove('dark-mode');
            elementTransactionAndAmountOnAccounts.style.color = "black";
        } else {
            elementTransactionAndAmountOnAccounts.classList.add('dark-mode');
            elementTransactionAndAmountOnAccounts.style.color = "white";
        }
    }

    var isDarkModeTransactionAndAmountOnAccounts = elementsTransactionAndAmountOnAccounts[0].classList.contains('dark-mode');
    localStorage.setItem('darkMode', isDarkModeTransactionAndAmountOnAccounts.toString());
}

const toggleModeButton = document.getElementById('toggleMode');

toggleModeButton.addEventListener('click', toggleDarkMode);



const savedDarkMode = localStorage.getItem('darkMode');

if (savedDarkMode === 'true') {
    toggleDarkMode();
}


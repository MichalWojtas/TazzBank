// Funkcja do obsługi przełączania trybu
function toggleDarkMode() {
    // Pobierz element <body>
    const body = document.body;
    const welcomeText = document.getElementById('welcomeText');

    // Sprawdź, czy tryb jest aktualnie jasny lub ciemny
    const isDarkMode = body.classList.contains('dark-mode');
    const isDarkModeWelcome = welcomeText.classList.contains('dark-mode');

    // Przełącz tryb na przeciwny
    if (isDarkMode) {
        body.classList.remove('dark-mode');
        localStorage.setItem('darkMode', 'false'); // Zapisz tryb w localStorage
    } else {
        body.classList.add('dark-mode');
        localStorage.setItem('darkMode', 'true'); // Zapisz tryb w localStorage
    }

    if(isDarkModeWelcome){
        welcomeText.classList.remove('dark-mode');
        localStorage.setItem('darkMode','false');
    }else{
        welcomeText.classList.add('dark-mode');
        localStorage.setItem('darkMode','true');
    }
}

// Pobierz przycisk przełączania trybu
const toggleModeButton = document.getElementById('toggleMode');

// Obsługa kliknięcia przycisku
toggleModeButton.addEventListener('click', toggleDarkMode);

// Sprawdź, czy tryb został wcześniej ustawiony w localStorage
const savedDarkMode = localStorage.getItem('darkMode');

if (savedDarkMode === 'true') {
    // Jeśli tryb jest ustawiony na 'true' w localStorage, włącz tryb ciemny
    toggleDarkMode();
}
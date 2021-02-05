function validateTeamForm() {

    const teamNameInput = document.getElementById('teamName');
    const descriptionInput = document.getElementById('description');
    const minAgeInput = document.getElementById('minAge');
    const dateToInput = document.getElementById('dateTo');

    const errorTeamName = document.getElementById('errorTeamName');
    const errorDescription = document.getElementById('errorDescription');
    const errorMinAge = document.getElementById('errorMinAge');
    const errorDateTo = document.getElementById('errorDateTo')
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([teamNameInput, descriptionInput, minAgeInput, dateToInput], [errorTeamName, errorDescription, errorMinAge, errorDateTo], errorsSummary);

    let valid = true;

    if (!checkRequired(teamNameInput.value)) {
        valid = false;
        teamNameInput.classList.add("error-input");
        //errorTeamName.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorTeamName.innerText = lang;
    } else if (!checkTextLengthRange(teamNameInput.value, 2, 20)) {
        valid = false;
        teamNameInput.classList.add("error-input");
        //errorTeamName.innerText = "Pole powinno zawierać od 2 do 20 znaków";
        var lang = document.getElementById('errorSizeTeamname').innerText;
        errorTeamName.innerText = lang;
    }

     if (!checkTextLengthRangeDescription(descriptionInput.value,300)) {
         valid = false;
         descriptionInput.classList.add("error-input");
         //errorDescription.innerText = "Pole powinno zawierać do 300 znaków";
         var lang = document.getElementById('errorSizeDescription').innerText;
         errorDescription.innerText = lang;
     }

    if (!checkRequired(minAgeInput.value)) {
        valid = false;
        minAgeInput.classList.add("error-input");
        //errorMinAge.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorMinAge.innerText = lang;
    }else if (!checkNumberRange(minAgeInput.value, 16, 100)) {
        valid = false;
        minAgeInput.classList.add("error-input");
        //errorMinAge.innerText = "Pole powinno być liczbą w zakresie od 16 do 100";
        var lang = document.getElementById('errorSizeAge').innerText;
        errorMinAge.innerText = lang;
    }


    if (!checkRequired(dateToInput.value)) {
        valid = false;
        dateToInput.classList.add("error-input");
        //errorDateTo.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorDateTo.innerText = lang;
    }


    if (!valid) {
        //errorsSummary.innerText = "Formularz zawiera błędy";
        var lang = document.getElementById('errorForms').innerText;
        errorsSummary.innerText = lang;
    }

    return valid;

}
function validateForm() {
    const chosePlayerInput = document.getElementById('chosePlayer');
    const choseTeamInput = document.getElementById('choseTeam');
    const numOnShirtInput = document.getElementById('numOnShirt');
    const dateFromInput =document.getElementById('dateFrom');
    const dateToInput = document.getElementById('dateTo');

    const errorChosePlayer = document.getElementById('errorChosePlayer');
    const errorChoseTeam = document.getElementById('errorChoseTeam');
    const errorNumOnShirt = document.getElementById('errorNumOnShirt');
    const errorDateFrom = document.getElementById('errorDateFrom');
    const errorDateTo = document.getElementById('errorDateTo')
    const errorsSummary = document.getElementById('errorsSummary');

    resetErrors([chosePlayerInput, choseTeamInput, numOnShirtInput,dateFromInput,dateToInput], [errorChosePlayer, errorChoseTeam, errorNumOnShirt,errorDateFrom,errorDateTo], errorsSummary);

    let valid = true;

    if (!checkRequired(chosePlayerInput.value) || chosePlayerInput.value<0) {
        valid = false;
        chosePlayerInput.classList.add("error-input");
        //errorChosePlayer.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorChosePlayer.innerText = lang;
    }

    if (!checkRequired(choseTeamInput.value) || choseTeamInput.value<0) {
        valid = false;
        choseTeamInput.classList.add("error-input");
        //errorChoseTeam.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorChoseTeam.innerText = lang;
    }

    if (!checkRequired(numOnShirtInput.value)) {
        valid = false;
        numOnShirtInput.classList.add("error-input");
        //errorNumOnShirt.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorNumOnShirt.innerText = lang;
    }else if (!checkNumberRange(numOnShirtInput.value, 1, 100)) {
        valid = false;
        numOnShirtInput.classList.add("error-input");
        //errorNumOnShirt.innerText = "Pole powinno być liczbą w zakresie od 1 do 100";
        var lang = document.getElementById('errorSizeShirt').innerText;
        errorNumOnShirt.innerText = lang;
    }

    let nowDate = new Date(),
        month = '' + (nowDate.getMonth() + 1),
        day = '' + nowDate.getDate(),
        year = nowDate.getFullYear();

    if (month.length < 2)
        month = '0' + month;
    if (day.length < 2)
        day = '0' + day;
    const nowString = [day,month,year].join('-');


    if (!checkRequired(dateFromInput.value)) {
        valid = false;
        dateFromInput.classList.add("error-input");
        //errorDateFrom.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorDateFrom.innerText = lang;
    }else if (checkDateIfAfter(dateFromInput.value, nowString)) {
        valid = false;
        dateFromInput.classList.add("error-input");
        //errorDateFrom.innerText = "Data nie może być z przyszłości";
        var lang = document.getElementById('errorNoFuture').innerText;
        errorDateFrom.innerText = lang;
    } else if (checkRequired(dateToInput.value) && !checkDateIfAfter(dateToInput.value, dateFromInput.value)) {
        //jeśli data od oraz data do jest poprawna, sprawdzamy kolejność dat
        valid = false;
        dateToInput.classList.add("error-input");
        //errorDateTo.innerText = "Data do powinna być późniejsza niż data od";
        var lang = document.getElementById('errorAfterDate').innerText;
        errorDateFrom.innerText = lang;
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
function validatePlayerForm() {

    const firstNameInput = document.getElementById('firstName');
    const lastNameInput = document.getElementById('lastName');
    const emailInput = document.getElementById('email');
    const phoneNumberInput = document.getElementById('phoneNumber');
    const dateOfBirthInput = document.getElementById('dateOfBirth');

    const errorFirstName = document.getElementById('errorFirstName');
    const errorLastName = document.getElementById('errorLastName');
    const errorEmail = document.getElementById('errorEmail');
    const errorPhoneNumber = document.getElementById('errorPhoneNumber');
    const errorDateOfBirth = document.getElementById('errorDateOfBirth')
    const errorsSummary = document.getElementById('errorsSummary');


    resetErrors([firstNameInput, lastNameInput, emailInput, phoneNumberInput, dateOfBirthInput], [errorFirstName, errorLastName, errorEmail, errorPhoneNumber, dateOfBirthInput], errorsSummary);

    let valid = true;
    if (!checkRequired(firstNameInput.value)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        var lang = document.getElementById('filedrequired').innerText;
        errorFirstName.innerText = lang;

    } else if (!checkTextLengthRange(firstNameInput.value, 2, 30)) {
        valid = false;
        firstNameInput.classList.add("error-input");
        //errorFirstName.innerText = "Pole powinno zawierać od 2 do 30 znaków";
        var lang = document.getElementById('errorSizeName').innerText;
        errorFirstName.innerText = lang;
    }

    if (!checkRequired(lastNameInput.value)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        //errorLastName.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorLastName.innerText = lang;
    } else if (!checkTextLengthRange(lastNameInput.value, 2, 30)) {
        valid = false;
        lastNameInput.classList.add("error-input");
        //errorLastName.innerText = "Pole powinno zawierać od 2 do 30 znaków";
        var lang = document.getElementById('errorSizeName').innerText;
        errorLastName.innerText = lang;
    }

    if (!checkRequired(emailInput.value)) {
        valid = false;
        emailInput.classList.add("error-input");
        //errorEmail.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorEmail.innerText = lang;
    } else if (!checkTextLengthRange(emailInput.value, 5, 40)) {
        valid = false;
        emailInput.classList.add("error-input");
        //errorEmail.innerText = "Pole powinno zawierać od 5 do 40 znaków";
        var lang = document.getElementById('errorSizeEmail').innerText;
        errorEmail.innerText = lang;
    } else if (!checkEmail(emailInput.value)) {
        valid = false;
        emailInput.classList.add("error-input");
        //errorEmail.innerText = "Pole powinno zawierać prawidłowy adres email";
        var lang = document.getElementById('errorSizeEmailScru').innerText;
        errorEmail.innerText = lang;
    }

    if (!checkRequired(phoneNumberInput.value)) {
        valid = false;
        phoneNumberInput.classList.add("error-input");
        //errorPhoneNumber.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorPhoneNumber.innerText = lang;
    } else if (!checkTextLengthRange(phoneNumberInput.value, 9, 9)) {
        valid = false;
        phoneNumberInput.classList.add("error-input");
        //errorPhoneNumber.innerText = "Pole powinno zawierać 9 znaków";
        var lang = document.getElementById('errorSizePhone').innerText;
        errorPhoneNumber.innerText = lang;
    } else if (!checkPhoneNumber(phoneNumberInput.value)) {
        valid = false;
        phoneNumberInput.classList.add("error-input");
        //errorPhoneNumber.innerText = "Pole powinno zawierać prawidłowy numer telefonu(np. 123456789)";
        var lang = document.getElementById('errorEmailScructure').innerText;
        errorPhoneNumber.innerText = lang;
    }

    if (!checkRequired(dateOfBirthInput.value)) {
        valid = false;
        dateOfBirthInput.classList.add("error-input");
        //errorDateOfBirth.innerText = "Pole jest wymagane";
        var lang = document.getElementById('filedrequired').innerText;
        errorDateOfBirth.innerText = lang;
    }/*else if (!checkDate(dateOfBirthInput.value)) {
        valid = false;
        dateOfBirthInput.classList.add("error-input");
        errorDateOfBirth.innerText = "Pole powinno zawierać datę w formacie dd-MM-yyyy (np. 01.01.2000)";
    }*/

    if (!valid) {
        //errorsSummary.innerText = "Formularz zawiera błędy";
        var lang = document.getElementById('errorForms').innerText;
        errorsSummary.innerText = lang;
    }
    return valid;

}

function AddAlert() {
    alert("Dodano nowego gracza");
}
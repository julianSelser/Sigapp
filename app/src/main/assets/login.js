SIGA.debugMsg('In login.js ...');

/* in case they decide to change them in the future */
/* maybe they should be injected through android? */
var selectors = {
    loginForm:      '#login-filds',
    cip:            '[name=cip]',
    passwd:         '[name=passwd]',
    submit:         '[name=enviar]',
    errorSection:   '.tableErrors',
};

function fillLoginAndSubmit(){
    document.querySelector(selectors.cip).value = SIGA.getCip();
    document.querySelector(selectors.passwd).value = SIGA.getPasswd();
    document.querySelector(selectors.submit).click();
}

function loginDataIsOk(){
    return !(document.querySelector(selectors.errorSection));
}

onDocumentReady(function(){
    if(loginDataIsOk()){
        SIGA.debugMsg('Login data correct, attempting submit');

        fillLoginAndSubmit();
    }
    else{
        SIGA.loginDataWasWrong();
    }
});

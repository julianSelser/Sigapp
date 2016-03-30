SigAPI.debugMsg('In login.js ...');

/* in case they decide to change them in the future */
var selectors = {
    loginForm:      '#login-filds',
    cip:            '[name=cip]',
    passwd:         '[name=passwd]',
    submit:         '[name=enviar]',
    errorSection:   '.tableErrors',
};

function fillLoginAndSubmit(login){
    document.querySelector(selectors.cip).value = login.getCip();
    document.querySelector(selectors.passwd).value = login.getPasswd();
    document.querySelector(selectors.submit).click();
}

function shouldAttemptLogin(){
    return !(document.querySelector(selectors.errorSection));
}

function parsedErrors(){
    /* should probably parse error div */
    return document.querySelector(selectors.errorSection).innerHTML;
}

onDocumentReady(function(){
    var loginService = SigAPI.getLoginService();
    
    if(shouldAttemptLogin()){
        SigAPI.debugMsg('attempting real login');

        fillLoginAndSubmit(loginService);
    }
    else{
        SigAPI.debugMsg('loggin data was wrong, errors were: ' + parsedErrors());
        
        loginService.failWithErrors(parsedErrors());
    }
});

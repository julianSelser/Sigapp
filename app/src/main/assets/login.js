/*
SIGA = SIGA || {
    debugMsg: function(m){ console.log(m); },
    loginDataWasWrong: function(){},
    getCip: function(){},
    getPasswd: function(){}
};
*/

SIGA.debugMsg('In login.js ...');

/* loads jQuery */
(function(){ 
    var request = new XMLHttpRequest();

    request.open('get', 'https://code.jquery.com/jquery-2.2.0.min.js', false);

    request.send();

    if (request.status === 200) eval(request.responseText);

    jQuery.noConflict();

    if(jQuery) SIGA.debugMsg('jQuery loaded correctly');
}());

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
    var $loginForm = jQuery(selectors.loginForm);
    $loginForm.find(selectors.cip).val(SIGA.getCip());
    $loginForm.find(selectors.passwd).val(SIGA.getPasswd());
    $loginForm.find(selectors.submit).click();
}

function loginDataIsOk(){
    return !(jQuery(selectors.errorSection).length > 0);
}

jQuery(function(){
    if(loginDataIsOk()){
        SIGA.debugMsg('Login data correct, attempting submit');

        fillLoginAndSubmit();
    }
    else{
        SIGA.loginDataWasWrong();
    }
});

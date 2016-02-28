/**
 * testing.js holds code for testing all other javascripts
 */

function loginPassed(){
    if(document.querySelector(selectors.cip).value === SIGA.getCip() &&
        document.querySelector(selectors.passwd).value === SIGA.getPasswd()){

        document.querySelector('#login-test-result').innerHTML = 'login.js passed!';
    }
}

onDocumentReady(function(){
    var title = document.querySelector('#title');
    title.innerHTML = 'Testing Page Loaded! :D';
    title.className = 'passed-test';
});

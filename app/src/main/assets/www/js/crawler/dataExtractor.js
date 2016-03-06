onDocumentReady(function(){
    /**
     * must always check if we are loggedIn
     * and attempt a log in if we arent...
     * worst case scenario, we have to redirect to webapp login
     */
    checkIfLoggedIn();
});

function checkIfLoggedIn(){
    if(isActuallyLoggedIn()){
        SIGA.logIn();
        SIGA.debugMsg('just logged in');
    }
}

function isActuallyLoggedIn(){
    /**
     * SIGA has some weird glitches but we can scan
     * the page to see weather its logged in
     */
    return true;
}
onDocumentReady(function(){
    /**
     * must always check if we are loggedIn
     * and attempt a log in if we arent...
     * worst case scenario, we have to redirect to webapp login
     */
    SIGA.debugMsg('in data extractor...');
    checkIfLoggedIn();
});

function checkIfLoggedIn(){
    SIGA.logIn();
    SIGA.debugMsg('just logged in');
}
onDocumentReady(function(){
    
    console.log(window.SIGA? '%cSIGA was injected' : '%cSIGA not injected!',
        'color:' + (window.SIGA? 'blue' : 'red'));
});

function attemptLogin(){
    var cipInput = document.querySelector('#legajo');
    var passInput = document.querySelector('#pass');
    
    cipInput.disabled = true;
    passInput.disabled = true;
    
    SIGA.setCip(cipInput.value);
    SIGA.setPasswd(passInput.value);
    
    SIGA.attemptLogin();
    
    new Promise(function(resolve, reject){
        
        var i = setInterval(function(){
            if(SIGA.isLoggedIn()){
                clearInterval(i);
                resolve();
            }
            
            //TODO: check for login status
            //if theres errors, or timeout, reject()
        }, 500);
    }).then(function(){
        cipInput.disabled = false;
        passInput.disabled = false;
        window.location.href = 'main.html';
        
    }).catch(function(){
        //TODO: handle errors, show them to user
    });
}
onDocumentReady(function(){
    console.log(window.SIGA? '%cSIGA was injected' : '%cSIGA not injected!',
        'color:' + (window.SIGA? 'blue' : 'red'));
});

function attemptLogin(){
    var cipInput = document.querySelector('#legajo');
    var passInput = document.querySelector('#pass');
    var spinner = document.querySelector('#submitSpinner');
    
    cipInput.disabled = true;
    passInput.disabled = true;
    cipInput.classList.add("disabledLook");
    passInput.classList.add("disabledLook");
    spinner.classList.remove("invisible");
    
    SIGA.setCip(cipInput.value);
    SIGA.setPasswd(passInput.value);
    
    SIGA.attemptLoginAnd(function(){
        //success
        window.location.href = 'main.html';
    }, function(data){
        document.querySelector("#errors").innerHTML = data.get();
        
        //failure        
        cipInput.disabled = false;
        passInput.disabled = false;
        cipInput.classList.remove("disabledLook");
        passInput.classList.remove("disabledLook");
        spinner.classList.add("invisible");
    });
}
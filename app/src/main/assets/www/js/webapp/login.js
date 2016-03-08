onDocumentReady(function(){
    console.log(window.SIGA? '%cSIGA was injected' : '%cSIGA not injected!',
        'color:' + (window.SIGA? 'blue' : 'red'));
});

function attemptLogin(){
    var login = SIGA.getService('login');
    var cipInput = document.querySelector('#legajo');
    var passInput = document.querySelector('#pass');
    var spinner = document.querySelector('#submitSpinner');
    
    cipInput.disabled = true;
    passInput.disabled = true;
    cipInput.classList.add("disabledLook");
    passInput.classList.add("disabledLook");
    spinner.classList.remove("invisible");
    
    login.setCip(cipInput.value);
    login.setPasswd(passInput.value);
    
    SIGA.serviceQuery({
        service: "login",
        
        onSuccess: function(){
            window.location.href = 'main.html';
        }, 
        
        onFailure: function(data){
            document.querySelector("#errors").innerHTML = data.get();

            cipInput.disabled = false;
            passInput.disabled = false;
            cipInput.classList.remove("disabledLook");
            passInput.classList.remove("disabledLook");
            spinner.classList.add("invisible");
        }
    });
}
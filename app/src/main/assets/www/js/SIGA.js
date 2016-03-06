/**
 * SIGA stub of the JavascriptInterface
 * if SIGA was injected by an activity, it uses that one
 * @type SIGA 
 */
var SIGA = window.SIGA || {
    
    cip:"",
    pass:"",
    
    getCip: function(){ return this.cip; },
    getPasswd: function(){ return this.pass; },
    setCip: function(cip){ return this.cip = cip; },
    setPasswd: function(pass){ return this.pass = pass; },
    
    debugMsg: function(m){ console.log(m); },
    
    loginDataWasWrong: function(){},
    
    attemptLogin: function(){},
    
    logIn: function(){ throw new Error("logIn shouldn't be called; It's here for completeness");},
    
    isLoggedIn: function(){ return true; },
    loginFailed: function(){ return false; },
    getLoginData: function(){},
    
};

SIGA.attemptLoginAnd = function(onSuccess, onFailure){
    var queryForLogin = SIGA.serviceQuerier(SIGA.isLoggedIn, SIGA.loginFailed, SIGA.getLoginData);
    
    SIGA.attemptLogin();
    
    queryForLogin(onSuccess, onFailure);
};

/**
 * Encapsulates async service querying, with callbacks onSuccess and onFailure
 * Reasons: 1) I don't know if I want to just wait 500ms, I can change hwo it's handled in a single place in the future
 *          2) Without this crap I'd end up with similar looking ad-hoc interval polling everywhere
 *          3) Without this, I'd use too many JavascriptInterface method calls everywhere, and JavascriptInterface is a hack in on itself
 */
SIGA.serviceQuerier = function(successCondition, failureCondition, dataGetter){
    
    return function(successHandler, failureHandler){
        if(!successHandler) throw new Error("A success handler must be specified");
        
        var i;
        var data = {
            stopQuerying: function(){
                clearInterval(i);
            },
            
            get: function(){
                if(dataGetter) return dataGetter.call(SIGA);
            }
        };
        
        i = setInterval(function(){
            if(successCondition.call(SIGA)){
                successHandler(data);
            }
            else if(failureCondition && failureCondition.call(SIGA)){
                failureHandler(data);
            }
        }, 500);
    };
};
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
    
    loginDataWasWrong: function(){ ; },
    
    attemptLogin: function(){},
    
    isLoggedIn: function(){ return true; }
    
};
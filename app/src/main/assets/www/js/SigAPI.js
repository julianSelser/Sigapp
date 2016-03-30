/**
 * SigAPI stub of the JavascriptInterface
 * if SigAPI was injected by an activity, it uses that one
 * @type SigAPI 
 */
var SigAPI = window.SigAPI || {
    
    getLoginService: function(){
        return {
            cip:"",
            pass:"",

            getCip: function(){ return this.cip; },
            getPasswd: function(){ return this.pass; },
            setCip: function(cip){ return this.cip = cip; },
            setPasswd: function(pass){ return this.pass = pass; },


            success: function(){ return true; },
            failure: function(){ return false; },
            getData: function(){},
            startQuerying: function(){},
            succeedWithData: function(data){},
            failWithErrors: function(errors){},
        };
    },
    
    
    debugMsg: function(m){ console.log(m); },
    
};

SigAPI.getService = function(serviceString){
    if(typeof serviceString !== "string")
        throw new Error("service must recieve a string");
    
    var capitalizedServiceString = 
            serviceString.charAt(0).toUpperCase() + serviceString.slice(1).toLowerCase();
    
    var service = "get" + capitalizedServiceString + "Service";

    return SigAPI[service]();
};

/* Encapsulates async service querying, with callbacks onSuccess and onFailure */
SigAPI.serviceQuery = function(queryOptions){
    /* TODO: add validation checks to queryOptions, ie.: to have an onSuccess, etc*/

    var i;
    var service = SigAPI.getService(queryOptions.service);
    var data = {
        stopQuerying: function(){
            clearInterval(i);
        },

        get: function(){
            return service.getData();
        }
    };
    
    service.startQuerying();

    i = setInterval(function(){
        if(service.success()){
            queryOptions.onSuccess(data);
        }
        else if(service.failure()){
            queryOptions && queryOptions.onFailure(data);
        }
    }, 500);
};
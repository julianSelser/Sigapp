/**
 * testing.js holds code for testing all other javascripts
 */
function loginPassed(){
    if(document.querySelector(selectors.cip).value == SIGA.getCip() &&
        document.querySelector(selectors.passwd).value == SIGA.getPasswd()){


        document.querySelector('#login-test-result').innerHTML = 'login.js passed!';
    }
}

onDocumentReady(function(){
    testOnDocumentReady();
    testServiceQuerier();
});

function testOnDocumentReady(){
    var title = document.querySelector('#title');
    title.innerHTML = 'Testing Page Loaded! :D';
    title.className = 'passed-test';
}

function testServiceQuerier(){
    var succeeded = true;
    var errored = false;

    var successCond = function (){ return succeeded;};
    var errorCond = function (){ return errored; };
    var dataGetter = function (){
        if(succeeded) return "serviceQuerier worked for success";
        if(errored) return "serviceQuerier worked for error";
    };

    var onSomeServiceData = SIGA.serviceQuerier(successCond, errorCond, dataGetter);

    onSomeServiceData(function(data){
        succeeded = false;
        errored = true;
        document.querySelector("#onsuccess").innerHTML += data.get();
    }, function(data){
        document.querySelector("#onerror").innerHTML += data.get();
        data.stopQuerying();
        document.querySelector("#stopQueryCalled").innerHTML += "stopped cycle, this should only appear once";
    });
}
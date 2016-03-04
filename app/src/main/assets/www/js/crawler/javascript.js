/* load jquery immediately, because we cant leave without it */
(function loadJQuery(){
    var request = new XMLHttpRequest();

    request.open("get", 'https://code.jquery.com/jquery-2.2.0.min.js', false);

    request.send();

    if (request.status === 200) eval(request.responseText);

    jQuery.noConflict();

    SIGA.debugMsg('jQuery loaded...');
}());

function setLoginInputs(){

}


$(function(){
    setLoginInputs();
});

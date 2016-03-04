var request = new XMLHttpRequest();

request.open("get", 'https://code.jquery.com/jquery-2.2.0.min.js', false);

request.send();

SIGA.debugMsg('before jquery evaluation...');
if (request.status === 200) eval(request.responseText);
SIGA.debugMsg('after jquery evaluation...');

jQuery.noConflict();

SIGA.debugMsg('just ran jquery...');

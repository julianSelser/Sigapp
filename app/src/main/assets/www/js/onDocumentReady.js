/**
 * @param {callback} doSomething is the callback to execute onDocumentReady
 */
function onDocumentReady(doSomething){
    setTimeout(function(){
        if(document.readyState === "complete")
            doSomething();
        else{
            onDocumentReady(doSomething);
        }
    }, 50);
}
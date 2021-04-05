// This helps avoid conflicts in case we inject 
// this script on the same page multiple times
// without reloading.
var injected = injected || (function() {

    var methods = {};

    methods.getLinks = function() {
        var links = [];
        $('.episode-title').each(function() {
            links.push($(this).attr('href'));
        });
        return links;
    };

    // This tells the script to listen for messages from the extension.
    chrome.runtime.onMessage.addListener(function(request, sender, sendResponse) {
        var data = {};
        // If the method the extension has requested
        // exists, call it and assign its response
        // to data.
        if (methods.hasOwnProperty(request.method))
            data = methods[request.method]();
        // Send the response back to the extension.
        sendResponse({ data: data });
        return true;
    });

    return true;
})();
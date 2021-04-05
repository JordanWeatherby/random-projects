// Execute the inject.js in a tab and call a method,
// passing the result to a callback function.
function injectedMethod(tab, method, callback) {
    chrome.tabs.executeScript(tab.id, { file: 'inject.js' }, function() {
        chrome.tabs.sendMessage(tab.id, { method: method }, callback);
    });
}

function getLinks(tab) {
    // When a response is returned from the method, do stuff
    injectedMethod(tab, 'getLinks', function(response) {
        // Add hrefs to array
        links = [].concat(response.data);
        //links.join('<br>');

        // Write links array to html in new tab
        var tab = window.open('about:blank', '_blank');
        tab.document.write("<p>");
        for (var i = 0; i < links.length; i++) {
            links[i] = "https://roosterteeth.com" + links[i];
            tab.document.write(links[i] + "<br>")
        }
        tab.document.write("</p>")
        tab.document.close()


        return true;
    });
}

// When the browser action is clicked, call the function
chrome.browserAction.onClicked.addListener(getLinks);
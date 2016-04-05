/**
 * Created by pengguangyu on 2016/3/21.
 */

var pgy = {
    extend : function(target, options) {
        for (name in options) {
            target[name] = options[name];
        }
        return target;
    }
};
var pgy_toggle = (function(pgy_toggle,o){
    var defaults = {
        switchName:"switch",
        id:"toggle",
        hide:"none",
        show:"block"
    };
    var opts = defaults;
    var state = opts.show;
    o.toggle = function(opts){
        opts = pgy_toggle.extend(defaults,opts);
        var switchAction = document.getElementById(opts.switchName);
        var element = document.getElementById(opts.id);
        switchAction.onclick = function(){
            if(element.style.display!=(null||""))
                state = element.style.display;
            if(state == opts.hide)
                element.style.display = opts.show;
            else
                element.style.display = "none";
        }
    };

    return o;
})(pgy,(pgy_toggle || {}));

pgy_toggle.toggle({switchName:"switch"});


/*µÈ´ý¼¯³É*/
function sendXMLContext(url,context){
    var xmlhttp = createHttpRequest();
    if(xmlhttp){
        xmlhttp.open("POST",url,false);
        xmlhttp.setRequestHeader("Content-Type", "text/xml");
        xmlhttp.send(context);
        return xmlhttp.responseText;
    }
    return "";
}

function createHttpRequest(){
    var xmlhttp=false;
    try{
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
        try {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        } catch (E) {
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
        try {
            xmlhttp = new XMLHttpRequest();
        } catch (e) {
            xmlhttp=false;
        }
    }
    if (!xmlhttp && window.createRequest) {
        try {
            xmlhttp = window.createRequest();
        } catch (e) {
            xmlhttp=false;
        }
    }
    return xmlhttp;
}

function createDOMDocument(){
    var xmldoc=new ActiveXObject("Msxml2.DOMDocument");
    return xmldoc;
}
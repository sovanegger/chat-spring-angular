var APP_NAME = "/chat";
var LOGIN_PAGE_URL = "/login";

var hasStorage = (function() {
	if(typeof(Storage)!=='undefined')
		return true;
	else
		return false;
})();
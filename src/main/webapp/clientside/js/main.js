var APP_NAME = "/chat-spring-angular";
var LOGIN_PAGE_URL = "/login";
var CHAT_MESSAGE_LIMIT = 10;
var chatAppControllers = angular.module('chatAppControllers', []);

var hasStorage = (function() {
	if(typeof(Storage)!=='undefined')
		return true;
	else
		return false;
})();

var redirectToLoginPageWhenNoUser = function(location) {
	if (!localStorage.username)
		location.path(LOGIN_PAGE_URL).replace();
};

var setCSSDisplayOnElementsByClass = function (className, state) {
	var elements = document.getElementsByClassName(className);
	
	for (var i = 0; i < elements.length; i++) {
		elements[i].style.display = state;
	}
}; 
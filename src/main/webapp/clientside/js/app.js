var chatApp = angular.module('chatApp', [
	'ngRoute',
	'chatAppControllers'
]);

chatApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/', {
				templateUrl: 'clientside/views/login.html',
				controller: 'LoginFormController' 
			}).
			when('/login', {
				templateUrl: 'clientside/views/login.html',
				controller: 'LoginFormController'
			}).
			when('/rooms', {
				templateUrl: 'clientside/views/rooms.html',
				controller: 'RoomListController' 
			}).
			when('/rooms/:roomId', {
				templateUrl: 'clientside/views/chat.html',
				controller: 'ChatController'
			}).
			when('/rooms?roomId=:roomId', {
				templateUrl: 'clientside/views/chat.html',
				controller: 'ChatController'
			}).
			otherwise({
				redirectTo: '/login'
			});
	}
]);
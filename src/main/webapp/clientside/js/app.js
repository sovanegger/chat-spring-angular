var chatApp = angular.module('chatApp', [
	'ngRoute'
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
chatApp.run(['$rootScope', '$location', function($rootScope, $location) {
	$rootScope.$on('$routeChangeStart', function(event, next, current) { 
		if (next) {
			var nextRoute = next.originalPath;
			if (!localStorage.username) {
				if (nextRoute !== '/login')
					$location.path(LOGIN_PAGE_URL).replace();
			}
			else if (nextRoute === '/login')
				$location.path('/rooms').replace();
		};
	 });
}]);
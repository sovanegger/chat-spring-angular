function user($rootScope) {
	var username = localStorage.username;
	
	var broadcast = function (newUsername) {
		$rootScope.$broadcast('user.update', newUsername);
	};
	
	var update = function(newUsername) {
		if (!newUsername)
			localStorage.removeItem('username');
		else
			localStorage.username = newUsername;
		username = newUsername;
		broadcast(newUsername);
	};
	return {
		username: username,
		update: update
    };
}

function routeAuth($rootScope, $location) {
	$rootScope.$on('$routeChangeStart', function(event, current, next) { 
		if (!localStorage.username)
			$location.path(LOGIN_PAGE_URL).replace();
	 });
}

chatApp.service('user', ['$rootScope', user]);
chatApp.service('routeAuth', ['$rootScope', '$location', routeAuth]);
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

function roomsLoading($interval) {
	var intervalPromise = null;
	
	var start = function(roomId, callback) {
		intervalPromise = $interval(function(){
			callback(roomId);
		}, 1000);
	};
	
	var stop = function() {
		if (intervalPromise)
			$interval.cancel(intervalPromise);
	};
	
	return {
		intervalPromise: intervalPromise,
		start: start,
		stop: stop
	};
}

function routeAuth($rootScope, $location) {
	$rootScope.$on('$routeChangeStart', function(event, current, next) { 
		if (!localStorage.username)
			$location.path(LOGIN_PAGE_URL).replace();
	 });
}

chatApp.service('user', ['$rootScope', user]);
chatApp.service('roomsLoading', ['$interval', roomsLoading]);
chatApp.service('routeAuth', ['$rootScope', '$location', routeAuth]);
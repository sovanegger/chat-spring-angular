function NavController($scope, $location, user) {
	$scope.username = user.username;
	
	$scope.$on('user.update', function (event, newUsername) {
		$scope.username = newUsername;
    });
	
	$scope.logout = function() {
		user.update(null);
		$location.path(LOGIN_PAGE_URL).replace();
	};
}

function RoomListController($scope, $http, $location) {
	$http.get(APP_NAME+'/rooms').success(function(data) {
		$scope.rooms = data;
	});
}

function ChatController($scope, $http, $routeParams, $location, user) {
	var resetAllMessages = function(roomId, limit) {
		var url = APP_NAME+'/chatmessages?roomId='+roomId;
		if (limit)
			url+='&limit='+limit; 
		$http.get(url).success(function(data) {
			$scope.chatMessages = data;
		});
	};
	
	$scope.send = function(chatMessage) {
		chatMessage.username = localStorage.username;
		chatMessage.room = $scope.room;
		$http.post(APP_NAME+'/chatmessages', chatMessage).success(function(data) {
			resetAllMessages(chatMessage.room.id);
			document.getElementsByName('chatMessageForm')[0].reset();
			$scope.chatMessageForm.$setPristine();
		});
	};
	
	var roomId = $routeParams.roomId;
	resetAllMessages(roomId);
	$http.get(APP_NAME+'/rooms/'+roomId).success(function(data) {
		$scope.room = data;
	}); 
}

function LoginFormController($scope, $http, $location, user) {
	$scope.login = function(username) {
		if (hasStorage) {
			if (!username)
				alert('Nezadali jste uživatelské jméno');
			else {
				user.update(username);
				$location.path('/rooms').replace();
			}
		}
		else
			alert('Prohlížeč nepodporuje tuto aplikaci');
	};
	if (user.username)
		$location.path('/rooms').replace();
}

chatApp.controller('NavController', ['$scope', '$location', 'user', NavController]);
chatApp.controller('RoomListController', ['$scope', '$http', '$location', RoomListController]);
chatApp.controller('ChatController', ['$scope', '$http', '$routeParams', '$location', 'user', ChatController]);
chatApp.controller('LoginFormController', ['$scope', '$http', '$location', 'user', LoginFormController]);
function NavController($scope, $location) {
	if (localStorage.username)
		setCSSDisplayOnElementsByClass('need-authenticated-user', 'inline');
	else
		setCSSDisplayOnElementsByClass('need-authenticated-user', 'none');

	$scope.logout = function() {
		if (localStorage.username)
			localStorage.removeItem('username');
		$location.path(LOGIN_PAGE_URL).replace();
		setCSSDisplayOnElementsByClass('need-authenticated-user', 'none');
	};
}

function RoomListController($scope, $http, $location) {
	redirectToLoginPageWhenNoUser($location);
	$http.get(APP_NAME+'/rooms').success(function(data) {
		$scope.rooms = data;
	});
}

function ChatController($scope, $http, $routeParams, $location) {
	redirectToLoginPageWhenNoUser($location);
	var resetAllMessages = function(roomId, limit) {
		var url = APP_NAME+'/chatmessages?roomId='+roomId;
		if (limit)
			url+='&limit='+limit; 
		$http.get(url).success(function(data) {
			$scope.chatMessages = data;
		});
	};
	
	$scope.send = function(chatMessage) {
		redirectToLoginPageWhenNoUser($location);
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

function LoginFormController($scope, $http, $location) {
	$scope.login = function(username) {
		if (hasStorage) {
			if (!username)
				alert('Nezadali jste uživatelské jméno');
			else {
				localStorage.username = username;
				$location.path('/rooms').replace();
				setCSSDisplayOnElementsByClass('need-authenticated-user', 'inline');
			}
		}
		else
			alert('Prohlížeč nepodporuje tuto aplikaci');
	};
	if (localStorage.username)
		$location.path('/rooms').replace();
}

chatApp.controller('NavController', ['$scope', '$location', NavController]);
chatApp.controller('RoomListController', ['$scope', '$http', '$location', RoomListController]);
chatApp.controller('ChatController', ['$scope', '$http', '$routeParams', '$location', ChatController]);
chatApp.controller('LoginFormController', ['$scope', '$http', '$location', LoginFormController]);
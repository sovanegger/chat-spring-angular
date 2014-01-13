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

function FooterController($scope, user) {
	$scope.username = user.username;
	
	$scope.$on('user.update', function (event, newUsername) {
		$scope.username = newUsername;
    });
}

function RoomListController($scope, $http, $location) {
	$http.get(APP_NAME+'/rooms').success(function(data) {
		$scope.rooms = data;
	});
}

function ChatController($scope, $http, $routeParams, $location, roomsLoading) {
	var currentLimit = 10;
	var resetAllMessages = function(roomId, limit) {
		var url = APP_NAME+'/chatmessages?roomId='+roomId;
		if (limit)
			url+='&limit='+limit; 
		$http.get(url).success(function(data) {
			$scope.chatMessages = data;
			if ($scope.chatMessages.length < currentLimit)
				$scope.nextLink = false;
			else
				$scope.nextLink = true;
		});
	};
	
	var startLoading = function(roomId) {
		resetAllMessages($scope.room.id, currentLimit);
		roomsLoading.stop();
		roomsLoading.start($scope.room.id, currentLimit, resetAllMessages);
	};
	
	$scope.nextLink = true;
	$scope.send = function(chatMessage) {
		chatMessage.username = localStorage.username;
		chatMessage.room = $scope.room;
		$http.post(APP_NAME+'/chatmessages', chatMessage).success(function(data) {
			resetAllMessages(chatMessage.room.id, currentLimit);
			document.getElementsByName('chatMessageForm')[0].reset();
			$scope.chatMessageForm.$setPristine();
			$scope.chatMessage.messageText='';
		});
	};
	
	$scope.next = function(number) {
		currentLimit+=number;
		startLoading($scope.room.id);
	};
	
	if ($routeParams.roomId) {
		$http.get(APP_NAME+'/rooms/'+$routeParams.roomId).success(function(data) {
			$scope.room = data;
			startLoading($scope.room.id);
		});
	}
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
}

chatApp.controller('NavController', ['$scope', '$location', 'user', NavController]);
chatApp.controller('FooterController', ['$scope', 'user', FooterController]);
chatApp.controller('RoomListController', ['$scope', '$http', '$location', RoomListController]);
chatApp.controller('ChatController', ['$scope', '$http', '$routeParams', '$location', 'roomsLoading', ChatController]);
chatApp.controller('LoginFormController', ['$scope', '$http', '$location', 'user', LoginFormController]);
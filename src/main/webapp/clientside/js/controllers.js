var chatAppControllers = angular.module('chatAppControllers', []);

function RoomListController($scope, $http) {
	$http.get('/chat-spring-angular/rooms').success(function(data) {
		$scope.rooms = data;
	});
}

function ChatController($scope, $http, $routeParams) {
	$scope.roomId = $routeParams.roomId;
}

chatAppControllers.controller('RoomListController', ['$scope', '$http', RoomListController]);
chatAppControllers.controller('ChatController', ['$scope', '$http', '$routeParams', ChatController]);

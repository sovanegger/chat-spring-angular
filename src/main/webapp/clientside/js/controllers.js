var APP_NAME = "/chat-spring-angular";
var CHAT_MESSAGE_LIMIT = 10;
var chatAppControllers = angular.module('chatAppControllers', []);

function RoomListController($scope, $http) {
	$http.get(APP_NAME+'/rooms').success(function(data) {
		$scope.rooms = data;
	});
}

function ChatController($scope, $http, $routeParams) {
	var resetAllMessages = function(roomId, limit) {
		var url = APP_NAME+'/chatmessages?roomId='+roomId;
		if (limit !== undefined)
			url+='&limit='+limit; 
		$http.get(url).success(function(data) {
			$scope.chatMessages = data;
		});
	};
	
	$scope.send = function(chatMessage) {
		chatMessage.username = 'kotalik';
		chatMessage.room = $scope.room;
		$http.post(APP_NAME+'/chatmessages', chatMessage).success(function(data) {
			resetAllMessages(chatMessage.room.id);
			document.getElementsByName("chatMessageForm")[0].reset();
			$scope.chatMessageForm.$setPristine();
		});
	};
	
	var roomId = $routeParams.roomId;
	resetAllMessages(roomId);
	$http.get(APP_NAME+'/rooms/'+roomId).success(function(data) {
		$scope.room = data;
	}); 
}

chatAppControllers.controller('RoomListController', ['$scope', '$http', RoomListController]);
chatAppControllers.controller('ChatController', ['$scope', '$http', '$routeParams', ChatController]);

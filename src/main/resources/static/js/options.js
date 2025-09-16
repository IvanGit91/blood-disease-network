app.controller('optionsController', function($rootScope, $scope, $http, $location, Idle) {

    if ($rootScope.authenticated === undefined || !$rootScope.authenticated) checkLogin($rootScope, $http, $location); else $rootScope.load = true;

    $(function () {
        showModal($rootScope);
    });

});
app.controller('userController', function($rootScope, $scope, $http, $location, $route, $routeParams, $filter, NgTableParams, $compile) {

    if ($rootScope.authenticated === undefined || !$rootScope.authenticated) checkLogin($rootScope, $http, $location); else $rootScope.load = true;

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
        $('[data-toggle="tooltip"], .tooltip').tooltip("hide");
        $('[data-toggle="popover"]').popover({html: true})
            .click(function (ev) {
                $('[data-toggle="tooltip"], .tooltip').tooltip("hide");
                //this is workaround needed in order to make ng-click work inside of popover
                $compile($('.popover.fade').contents())($scope);
            });

        showModal($rootScope);
    });

    $scope.operation = $routeParams.operation;

    // Check if it's in update mode
    if ($scope.operation === 'add') {
        $scope.add = true;
        $rootScope.user = {};
        $scope.user = {};
        $scope.selectedHospital = undefined;
        listAllHospitals(false);
    }
    else if ($scope.operation === 'update') {
        if ($rootScope.user !== undefined) {
            $scope.add = false;
            $scope.user = $rootScope.user;
            listAllHospitals(true);
        } else {
            $location.path("/user/add");
        }
    }
    else if ($scope.operation === 'list'){
        listAllUsers();
    }
    else {
        console.log("NO OP SELECTED");
        $scope.load = false;
    }

    function listAllUsers(){
        $scope.isRendered = false;
        $http({
            method: 'GET',
            url: '/bdn/user/'
        }).then(function successCallback(result) {
            $scope.users = result.data;
            console.log(result.data);

            $scope.isRendered = true;
            $scope.tableParams = new NgTableParams({
                page: 1,
                count: 10
            }, {
                dataset: $scope.users
            });

        }, function(err) {
            console.log("Error get list all users");
            console.log(err);
        });
    }


    function listAllHospitals(flag){
        $http({
            method: 'GET',
            url: '/bdn/hospital/'
        }).then(function successCallback(result) {
            $scope.hospitals = result.data;
            console.log(result.data);
            if (flag){  //Trova il codice hospital
                $scope.hospitals.forEach(function(element) {
                    if (element.code === $scope.user.hospitalCode){
                        $scope.selectedHospital = element;
                    }
                });
            }
        }, function(err) {
            console.log("Error get list all hospitals");
            console.log(err);
        });
    }

    $scope.getSelectedHospital = function () {
        if ($scope.selectedHospital !== undefined) {
            console.log("SELECTED ");
            console.log($scope.selectedHospital);
        } else {
            console.log("SELECTED NO HOSPITAL");
        }
    };


    $scope.addOrUpdateUser = function(){
        $scope.user.hospitalCode = $scope.selectedHospital.code;
        $scope.user.hospitalName = $scope.selectedHospital.name;
        console.log("User: ");
        console.log($scope.user);
        if ($scope.add) {
            $http({
                method: 'POST',
                url: '/bdn/user/add',
                data: $scope.user
            }).then(function (result) {
                console.log(result.data);
                fillModal($rootScope, "Result", "User registration completed successfully", "alert-success", "btn-success", false);
                $location.path("/user/list");
            }, function (err) {
                console.log("Error durante il salvataggio dell'user");
                console.log(err);
                fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            });
        } else {
            $http({
                method: 'POST',
                url: '/bdn/user/update',
                data: $scope.user
            }).then(function (result) {
                console.log(result.data);
                fillModal($rootScope, "Result", "User update completed successfully", "alert-success", "btn-success", false);
                $scope.isModUser = false;
                //$location.path("/main");
                $route.reload();
            }, function(err) {
                console.log("Error durante il salvataggio dell'user");
                console.log(err);
                fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            });
        }
    };

    $scope.modUser = function(user){
        $rootScope.user = user;
        $location.path("/user/update");
    };

    $scope.deleteUser = function($event){
        $rootScope.semaphore++;
        const id = $($event.target).prev().val();
        $http({
            method: 'DELETE',
            url: '/bdn/user/delete/' + id
        }).then(function (result) {
            console.log(result.data);
            fillModal($rootScope, "Result", "User delete completed successfully", "alert-success", "btn-success", false);
            //$location.path("/hospital/delete");
            $rootScope.semaphore--;
            $route.reload();
        }, function(err) {
            console.log("Error during deletion of the user");
            console.log(err);
            fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            $rootScope.semaphore--;
        });
    };
});
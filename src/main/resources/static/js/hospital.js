app.controller('hospitalController',
    function($rootScope, $scope, $http, $location, $route, $routeParams, $filter, NgTableParams, $compile) {

    // Check accessibility if URL is inserted from browser
    if ($rootScope.authenticated === undefined || !$rootScope.authenticated) checkLogin($rootScope, $http, $location); else $rootScope.load = true;

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
        $('[data-toggle="tooltip"], .tooltip').tooltip("hide");
        $('[data-toggle="popover"]').popover({ html : true })
            .click(function(ev) {
                $('[data-toggle="tooltip"], .tooltip').tooltip("hide");
                //this is workaround needed in order to make ng-click work inside of popover
                $compile($('.popover.fade').contents())($scope);
            });

        showModal($rootScope);
    });

    $scope.operation = $routeParams.operation;
    // Check if in edit mode

    if ($scope.operation === 'add') {
        $scope.add = true;
        $rootScope.hospital = {};
        $scope.hospital = {};
    }
    else if ($scope.operation === 'update'){
        if ($rootScope.hospital !== undefined) {
            $scope.add = false;
            $scope.hospital = $rootScope.hospital;
        } else {
            $location.path("/hospital/add");
        }
    }
    else if ($scope.operation === 'list'){
        listAllHospitals();
    }
    else {
        console.log("NO OPERATION SELECTED");
        $scope.load = false;
    }


    function listAllHospitals(){
        $scope.isRendered = false;
        $http({
            method: 'GET',
            url: '/bdn/hospital/'
        }).then(function successCallback(result) {
            $scope.hospitals = result.data;
            console.log(result.data);

            $scope.isRendered = true;
            $scope.tableParams = new NgTableParams({
                page: 1,
                count: 10
            }, {
                dataset: $scope.hospitals
            });

        }, function(err) {
            console.log("Error get list all hospitals");
            console.log(err);
        });
    }


    $scope.addOrUpdateHospital = function(){
        console.log("Hospital: ");
        console.log($scope.hospital);
        if ($scope.add) {
            $http({
                method: 'POST',
                url: '/bdn/hospital/add',
                data: $scope.hospital
            }).then(function (result) {
                console.log(result.data);
                fillModal($rootScope, "Result", "Hospital registration completed successfully", "alert-success", "btn-success", false);
                $location.path("/hospital/list");
            }, function (err) {
                console.log("Error during hospital saving");
                console.log(err);
                fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            });
        }
        else {
            $http({
                method: 'POST',
                url: '/bdn/hospital/update',
                data: $scope.hospital
            }).then(function (result) {
                console.log(result.data);
                $rootScope.modalShowed = false;
                fillModal($rootScope, "Result", "Hospital update completed successfully", "alert-success", "btn-success", false);
                $location.path("/hospital/list");
            }, function(err) {
                console.log("Error during hospital saving");
                console.log(err);
                $rootScope.modalShowed = false;
                fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            });
        }
    };

    $scope.modHospital = function(hospital){
        $rootScope.hospital = hospital;
        $location.path("/hospital/update");
    };


    $scope.deleteHospital = function($event){
        $rootScope.semaphore++;
        const id = $($event.target).prev().val();
        $http({
            method: 'DELETE',
            url: '/bdn/hospital/delete/' + id
        }).then(function (result) {
            console.log(result.data);
            fillModal($rootScope, "Result", "Hospital deletion completed successfully", "alert-success", "btn-success", false);
            $rootScope.semaphore--;
            $route.reload();
        }, function(err) {
            console.log("Error during hospital saving");
            console.log(err);
            fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            $rootScope.semaphore--;
        });
    };
});
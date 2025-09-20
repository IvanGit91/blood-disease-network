app.controller('mainController', function ($rootScope, $scope, $http, $location, Idle) {

    // Accessibility check if inserting the URL from browser
    checkLogin($rootScope, $http, $location);

    $(function () {
        showModal($rootScope);
        Idle.watch();
    });

    function listWho() {
        $http({
            method: 'GET',
            url: $rootScope.prefixDomain + 'medical-record/who2016/'
        }).then(function (response) {
            $scope.labels = [];
            response.data.forEach(function (element) {
                console.log(element);
                if (element.description.split('(').length > 1) {
                    $scope.labels.push('(' + element.description.split('(')[1]);
                } else {
                    $scope.labels.push(element.description);
                }
            });
            console.log($scope.labels);
            getJson();
        }, function (err) {
            console.log(err);
        });
    }

    function getJson() {
        $http({
            method: 'GET',
            url: './jsons/pieData2.json'
        }).then(function (response) {
            console.log(response.data);
            $scope.data = response.data;
        }, function (err) {
            console.log("Error get json");
            console.log(err);
        });
    }

    $scope.titleName = ["MDS", "Myeloma", "Lymphoma", "CLL", "Myeloproliferative"];
    listWho();

});
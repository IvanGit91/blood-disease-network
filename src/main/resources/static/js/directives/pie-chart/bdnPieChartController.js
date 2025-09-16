
angular.module('bdaApplication').controller("BdnPieChartController", BdnPieChartController);

function BdnPieChartController($scope, $window, $location, $http) {

    // console.log($scope.data);
    // console.log($scope.labels);
    $(function () {
    });

    $scope.options = {
        responsive: true,
        maintainAspectRatio: false,
        legend: {
            display: false
        }
    }


}
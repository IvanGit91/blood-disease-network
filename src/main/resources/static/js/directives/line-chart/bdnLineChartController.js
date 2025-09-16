
angular.module('bdaApplication').controller("BdnLineChartController", BdnLineChartController);

function BdnLineChartController($scope, $window, $location, $http) {

    // console.log($scope.data);
    // console.log($scope.labels);
    $(function () {
    });
    $scope.data1 = [[30, 50, 110, 80, 60, 50],[0, 60, 90, 180, 120, 150]];
    $scope.labels1 = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'];
    $scope.colorz = ['#00ff00','#ff0000'];
    $scope.dataset = [{
        label: 'Left dataset',
        fill: false,
        yAxisID: 'left-y-axis',
        borderWidth: 2
        // lineTension: 0
    }, {
        label: 'Right dataset',
        fill: false,
        yAxisID: 'left-y-axis',
        borderWidth: 2
        // lineTension: 0
    }];
    $scope.options = {
        legend: {
            display: true,
            labels: {
                fontColor: 'rgb(255, 255, 255)',
            }
        },
        scales: {
            xAxes: [{
                ticks:{
                    fontColor: "#ffffff"
                }
            }],
                yAxes: [{
                id: 'left-y-axis',
                type: 'linear',
                position: 'left',
                ticks: {
                    fontColor: "#ffffff"
                }
            }]
        }
    }


}
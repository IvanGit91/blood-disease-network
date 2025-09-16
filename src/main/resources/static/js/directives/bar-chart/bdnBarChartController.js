
angular.module('bdaApplication').controller("BdnBarChartController", BdnBarChartController);

function BdnBarChartController($scope, $window, $location, $http) {

    // console.log($scope.data);
    // console.log($scope.labels);
    $(function () {
    });
    // $scope.dataset = {
    //     // label: '# of Votes',
    //     // data: [3, 5],
    //     backgroundColor: [
    //         'rgba(230, 230, 230, 0.6)',
    //         'rgba(230, 230, 230, 0.6)'
    //     ],
    //     hoverBackgroundColor: [
    //         'rgba(230, 230, 230, 0.3)',
    //         'rgba(230, 230, 230, 0.3)'
    //     ],
    //     borderColor: [
    //         '#e6e6e6',
    //         '#e6e6e6'
    //     ],
    //     borderWidth: 2
    // };

    $scope.dataset = [{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    },{
        label: 'Left dataset'
    }]

    $scope.options = {
        responsive: true,
        maintainAspectRatio: true,
        legend: {
            display: true,
            labels: {
                fontColor: 'rgb(255, 255, 255)',
            }
        },
        scales: {
            yAxes: [{id: 'y-axis-1', type: 'linear', position: 'left', ticks: {
                    beginAtZero:true,
                    fontColor: "white",
                    display: true
                }}],
            xAxes: [{ticks: {
                    // fontColor: "#ffffff",
                    // maxRotation: 0,
                    // fontFamily: "sans-serif",
                    // fontSize: 14,
                    display: false,
                }}]
        }
    }
}
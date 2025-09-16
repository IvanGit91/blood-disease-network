angular.module('bdaApplication').directive("bdnPieChart", bdnPieChart);

function bdnPieChart() {
    return {
        restrict: 'E',
        controller: 'BdnPieChartController',
        scope: {
            titlename: "=",
            labels: "=",
            data: "="
        },
        templateUrl: './js/directives/pie-chart/bdnPieChart.html'
    };
}
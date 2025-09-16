angular.module('bdaApplication').directive("bdnBarChart", bdnBarChart);

function bdnBarChart() {
    return {
        restrict: 'E',
        controller: 'BdnBarChartController',
        scope: {
            labels: "=",
            data: "="
        },
        templateUrl: './js/directives/bar-chart/bdnBarChart.html'
    };
}
angular.module('bdaApplication').directive("bdnLineChart", bdnLineChart);

function bdnLineChart() {
    return {
        restrict: 'E',
        controller: 'BdnLineChartController',
        scope: {
            labels: "=",
            data: "="
        },
        templateUrl: './js/directives/line-chart/bdnLineChart.html'
    };
}
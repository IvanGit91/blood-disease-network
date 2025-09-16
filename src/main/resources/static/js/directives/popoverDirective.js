// This is a workaround needed in order to make the popover work within the ng-table that is rendered dynamically
app.directive('popover', function($compile, $timeout) {
    return {
        restrict: 'A',
        link: function(scope, element) {
            $timeout(function() {
                $(element).popover({html: true})
                    .click(function (ev) {
                        $('[data-toggle="tooltip"], .tooltip').tooltip("hide");
                        //this is workaround needed in order to make ng-click work inside of popover
                        $compile($('.popover.fade').contents())(scope);
                    });
            });
        }
    };
});

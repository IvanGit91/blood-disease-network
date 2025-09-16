var app = angular.module(
    'bdaApplication',
    ['ngRoute', 'ngResource', 'ngTable', 'alexjoffroy.angular-loaders', 'chart.js', 'ngAnimate', 'ngIdle', 'ui.bootstrap']
);

app.config(['$httpProvider',
    function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }
]);

app.config(function($routeProvider) {
	$routeProvider
        .when('/', {
            templateUrl : './template/login.html',
            controller : 'loginController'
        })
        .when('/logout', {
            templateUrl: './template/login.html',
            controller: 'logoutController'
        })
        .when('/register', {
            templateUrl : './template/register.html',
            controller : 'registerController'
        })
        .when('/main',{
            templateUrl: './template/main.html',
            controller: 'mainController'
        })
        .when('/hospital/:operation',{
            templateUrl: './template/hospital.html',
            controller: 'hospitalController'
        })
        .when('/user/:operation',{
            templateUrl: './template/user.html',
            controller: 'userController'
        })
        .when('/medical-record-myelo/:idcard/:operation',{
            templateUrl: './template/myelo.html',
            controller: 'myeloController'
        })
        .when('/medical-record-cll/:idcard/:operation',{
            templateUrl: './template/cll.html',
            controller: 'cllController'
        })
        .when('/option',{
            templateUrl: './template/options.html',
            controller: 'optionsController'
        })
        .otherwise({
            redirectTo : '/login',
            templateUrl : './template/login.html',
            controller: 'loginController'
        });
});

app.config(function(IdleProvider, KeepaliveProvider) {
    var timeoutInSeconds = 29 * 60;  //29 minutes
    IdleProvider.idle(60); // In seconds. It' s the time that it needs to stay 'idle' till the IdleStart function start
    // 29 minuti + 1 minute to enter in the idle state
    IdleProvider.timeout(timeoutInSeconds);  // In seconds. The time that needs to pass after the IdleStart function to go in timeout
    //KeepaliveProvider.interval(10); // In seconds. Execute the functions keepalive every 2 seconds
    //KeepaliveProvider.http('/keepalive/'); // URL that makes sure session is alive
});

app.config(['TitleProvider', function(TitleProvider) {
    TitleProvider.enabled(false); // it is enabled by default
}]);


// Global function
app.run(function($rootScope, $http, $location, $templateCache, Idle) {
    $rootScope.semaphore = 0;
    $rootScope.appErrors = null;
    $rootScope.prefixDomain = '/bdn/';
    $rootScope.modalShowed = true;
    $rootScope.load = false;

    // IDLE
    const initialSeconds = (new Date()).getTime() / 1000;  //In seconds
    let totalSecondsElapsed = 0;
    const showModalTime = 30; //30 seconds before the end of the timeout

    //Idle.watch();
    $rootScope.$on('IdleStart', function() { //Execute after the idle time passed
        console.log("START");
        totalSecondsElapsed = ((new Date()).getTime() / 1000) - initialSeconds;
        console.log("TIME ELAPSED: " +  totalSecondsElapsed + " FROM THE RUN OF APPLICATION: " + initialSeconds);
    });

    $rootScope.$on('IdleEnd', function() {
        console.log("END");
        hideModalOnly("Info");
    });

    // $rootScope.$on('Keepalive', function() {
    //     console.log("Keep alive");
    // });

    // countdown in seconds
    $rootScope.$on('IdleWarn', function(e, countdown) {
        if (countdown === showModalTime){
            showModalOnly("Info");
        }
    });

    $rootScope.$on('IdleTimeout', function() {   //Execute after "idle time" + "timeout time"
        hideModalOnly("Info");
        showModalOnly("Logout");
        console.log("TIMEOUT");
        // $timeout(function () {
        $http.post('logout', {})
        .finally(function () {
            $rootScope.authenticated = false;
            deleteCookie("username");
            deleteCookie("password");
        });
        // }, 4000);
    });

    $('#modalLogout').on('hidden.bs.modal', function (e) {
        //$location.path("/");
        window.location.href = window.location.protocol + "//" + window.location.host + $rootScope.prefixDomain;
    });

    // $rootScope.fillModal = function(title, body, alertClass, btnClass, show) {
    //     $rootScope.modalTitle = title;
    //     $rootScope.modalBody = body;
    //     $rootScope.modalClass = alertClass;
    //     $rootScope.modalButtonClass = btnClass;
    //     if (show) {
    //         $rootScope.showModal();
    //     }
    // };
    //
    // $rootScope.showModal = function() {
    //     if ($rootScope.modalTitle !== undefined && $rootScope.modalTitle != null) {
    //         $('#modalUserTitle').text($rootScope.modalTitle);
    //         $('#modalUserBody').text($rootScope.modalBody);
    //         $('#modalUser .modal-header').addClass($rootScope.modalClass);
    //         $('#modalCloseButton').addClass($rootScope.modalButtonClass);
    //         $('#modalUser').modal('show');
    //         $rootScope.nullRootVariables();
    //     }
    // };
    //
    // $rootScope.nullRootVariables = function() {
    //     $rootScope.modalTitle = null;
    //     $rootScope.modalBody = null;
    //     $rootScope.modalClass = null;
    //     $rootScope.modalButtonClass = null;
    // };

    // $templateCache.put('./template/patient.html', '<span> app.html content </span>');
    // console.log($templateCache.get('./template/patient.html'));

    $http.get('./template/cached/patient.html').then(function (response) {
        $templateCache.put('patient.html', response.data);
        //console.log($templateCache.get('patient.html'));
    }, function (errorResponse) {
        console.log('Cannot load the file template patient');
    });

    $http.get('./template/cached/followup.html').then(function (response) {
        $templateCache.put('followup.html', response.data);
    }, function (errorResponse) {
        console.log('Cannot load the file template followup');
    });
});


app.filter('roundup', roundUpFilter);
function roundUpFilter(){
    return function (value) {
        return Math.ceil(value);
    };
}

app.factory('$remember', function() {
    function fetchValue(name) {
        var gCookieVal = document.cookie.split("; ");
        for (var i=0; i < gCookieVal.length; i++)
        {
            // a name/value pair (a crumb) is separated by an equal sign
            var gCrumb = gCookieVal[i].split("=");
            if (name === gCrumb[0])
            {
                var value = '';
                try {
                    value = angular.fromJson(gCrumb[1]);
                } catch(e) {
                    value = unescape(gCrumb[1]);
                }
                return value;
            }
        }
        // a cookie with the requested name does not exist
        return null;
    }
    return function(name, values) {
        if(arguments.length === 1) return fetchValue(name);
        var cookie = name + '=';
        if(typeof values === 'object') {
            var expires = '';
            cookie += (typeof values.value === 'object') ? angular.toJson(values.value) + ';' : values.value + ';';
            if(values.expires) {
                var date = new Date();
                date.setTime( date.getTime() + (values.expires * 24 *60 * 60 * 1000));
                expires = date.toGMTString();
            }
            cookie += (!values.session) ? 'expires=' + expires + ';' : '';
            cookie += (values.path) ? 'path=' + values.path + ';' : '';
            cookie += (values.secure) ? 'secure;' : '';
        } else {
            cookie += values + ';';
        }
        document.cookie = cookie;
    }
});
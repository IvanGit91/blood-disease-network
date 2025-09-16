app.controller('loginController', function($rootScope, $scope, $http, $location, $route, $remember, Idle) {

    // $rootScope.modalTitle = "Prova";
    // $rootScope.modalBody = "Prova";

    Idle.unwatch();
    $(function () {
        showModal($rootScope);

        /* REMEMBER ME */
        $scope.remember = false;
        $scope.credentials = {};
        const user = $remember('username');
        const pass = $remember('password');
        let remember = $remember('rememberme');
        if (remember === null || remember === 'undefined' || remember === "")
            remember = false;
        console.log("USER: " + user + " PASS: " + pass + " REMEMBERME: " + remember);
        // if (user !== "undefined" && user !== null && user !== "" && pass !== "undefined" && pass !== null && pass !== "") {
        //     $scope.remember = true;
        //     $scope.credentials.username = $remember('username');
        //     $scope.credentials.password = $remember('password');
        //     console.log("REMEMBER1: " + $scope.remember);
        //     $("#username").val($scope.credentials.username);
        //     $("#password").val($scope.credentials.password);
        //     $("#loginSubmitBtn").click();
        // } else {
        //     httpLogin();
        // }

        if (remember) {
            httpLogin();
        } else {
            $http.post('logout', {})
            .finally(function () {
                $rootScope.authenticated = false;
                deleteCookie("username");
                deleteCookie("password");
                $location.path("/");
                httpLogin();
            });
        }

/*        // Affinchè il footer stia sempre in basso. ControlMenu è la barra di admin in alto
        var hTotal = $('.login-container').outerHeight() + (window.innerHeight - $('html').outerHeight());
        $('.login-container').css('min-height', hTotal);

        // Callback quando viene fatto il resize del browser
        $(window).resize(function() {
            var hTotal = $('.login-container').outerHeight() + (window.innerHeight - $('html').outerHeight());
            $('.login-container').css('min-height', hTotal);
        });*/

    });

    // $scope.fromlogout = $routeParams.fromlogout;
    // console.log("FROM: " + $scope.fromlogout);

    // $scope.rememberMe = function() {
    //
    //     console.log("REMEMBER2: " + $scope.remember);
    // };

    $scope.load = false;
    $scope.loginUser = function () {
        authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $scope.loginerror = false;
                $rootScope.userName = $scope.credentials.username;
                $rootScope.userRole = $scope.$parent.userRole;
                $location.path("/main");
            } else {
                $scope.loginerror = true;
                $rootScope.authenticated = false;
                $location.path("/");
            }
        });
    };

    $scope.resetForm = function () {
        $scope.credentials = null;
    };

    const authenticate = function (credentials, callback) {
        const headers = $scope.credentials ? {
            authorization: "Basic " + btoa($scope.credentials.username + ":" + $scope.credentials.password)
        } : {};
        $http.get('user', {
            headers: headers
        }).then(function (response) {
            console.log(response.data);
            //console.log(response.data.authorities[0].authority);
            if (response.data.authenticated) {
                $rootScope.authenticated = true;
                $rootScope.userName = response.data.name;
                $rootScope.userRole = response.data.authorities[0].authority;
                // Remember me
                if ($scope.remember) {
                    $remember('username', $scope.credentials.username);
                    $remember('password', $scope.credentials.password);
                } else {
                    $remember('username', '');
                    $remember('password', '');
                }
                $remember('rememberme', $scope.remember);
            } else {
                $rootScope.authenticated = false;
            }
            callback && callback();
        }, function () {
            $rootScope.authenticated = false;
            callback && callback();
        });
    };


    function httpLogin() {
        $http({
            method: 'GET',
            url: '/bdn/check-login/'
        }).then(function (response) {
            console.log(response);
            checkLogin(response);
        }, function (err) {
            console.log("Error get logged user");
            console.log(err);
            $rootScope.authenticated = false;
            $rootScope.load = true;
        });
    }

    function checkLogin(response){
        $scope.loginerror = false;
        $rootScope.authenticated = (response.data.principal) ? response.data.principal.authenticated : false;
        if ($rootScope.authenticated) {
            $rootScope.userName = response.data.principal.name;
            $rootScope.userRole = response.data.principal.authorities[0].authority;
            $scope.loginerror = false;
            $location.path("/main");
        } else {
            $scope.credentials = {};
            $scope.load = true;
            //authenticate();
        }
        $rootScope.load = true;
    }
});


app.controller('logoutController', function ($rootScope, $scope, $http, $location, $route) {
    $http.post('logout', {})
    .finally(function () {
        $rootScope.authenticated = false;
        deleteCookie("username");
        deleteCookie("password");
        $location.path("/");
    });

    // $scope.logout = function() {
    //     $http.post('logout', {}).success(function() {
    //         $rootScope.authenticated = false;
    //         $location.path("/");
    //     }).error(function(err) {
    //         console.log("Error logout");
    //         console.log(err);
    //         $rootScope.authenticated = false;
    //     });
    // }

});
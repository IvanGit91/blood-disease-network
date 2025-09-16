app.controller('registerController', function($rootScope, $scope, $http, $location, $route) {

   $(function () {
       listAllHospitals();
       $rootScope.load = true;
   });

   $scope.selectedHospital = undefined;
   
   function listAllHospitals(){
  	 $http({
		  method: 'GET',
		  url: '/bdn/hospital/'
	  }).then(function successCallback(result) {
	      $scope.hospitals = result.data;
	      console.log(result.data);
	  }, function(err) {
         console.log("Error get list all hospitals");
         console.log(err);
     });
   }

    $scope.getSelectedHospital = function () {
        if ($scope.selectedHospital !== undefined) {
            console.log("SELECTED ");
            console.log($scope.selectedHospital);
        } else {
            console.log("SELECTED NO HOSPITAL");
        }
    };
   
   $scope.sendRegisterData = function()
   {
	  $scope.user.role="USER";
	  $scope.user.hospitalName = $scope.selectedHospital.name;

      $http({
		  method: 'POST',
		  url: '/bdn/user/add',
	      data: $scope.user
	  }).then(function (result) {
	      console.log(result.data);
          fillModal($rootScope, "Result", "User registration completed successfully", "alert-success", "btn-success", false);
          $location.path("/");
          //$route.reload();
	  }, function(err) {
          console.log("Error register user");
          console.log(err);
          fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
      });
   }

    // $scope.navigate = function()
    // {
    //     $rootScope.modalTitle = "Ciao";
    //     $rootScope.modalBody = "Bello";
    //     $location.path("/").search({param1: 'value'});
    // }
}); 
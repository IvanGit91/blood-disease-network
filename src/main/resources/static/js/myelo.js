app.controller('myeloController',
    function($rootScope, $scope, $http, $location, $route, $routeParams, $compile, $templateCache, $timeout) {

    if ($rootScope.authenticated === undefined || !$rootScope.authenticated) checkLogin($rootScope, $http, $location); else $rootScope.load = true;

    $scope.rendered = false;
    $scope.opRunning = false;
    $scope.activeRequired = false;

    $(function () {
        showModal($rootScope);
        // $templateCache.get('patient.html');
        $compile($('#patientBody'))($scope);
        $compile($('#followupBody'))($scope);
    });

    $scope.medicalRecord = {};
    $scope.medicalRecord.category = parseInt($routeParams.idcard, 10);
    $scope.operation = $routeParams.operation;

    // SE NON E' MIELOPROLOIFERATIVA
    if ($scope.medicalRecord.category !== 5){
        fillModal($rootScope, "Error", "Medical Record not found", "alert-danger", "btn-danger", false);
        $location.path("/main");
    }
    else {
        getCategoryById($rootScope, $scope, $http);
    }

    function setRunning(){
        $scope.opRunning = false;
    }

    // TIMELINE SECTION
    $scope.currentSection = 1;
    let timelines =$('#timelineMielo li').toArray();

    function nextPhase(index, operation){
        let section = timelines[index];
        if (operation) $(section).addClass('complete'); else $(section).removeClass('complete');
    }

    $scope.togglePreviousSection = function() {
        let prevSection = $('#timeline .complete').length - 1;
        if (prevSection > 0) {
            $scope.currentSection = prevSection;
            nextPhase($scope.currentSection, false);
        }
    };

    $scope.toggleNextSection = function(){
        let section = $('#timeline .complete').length;
        if (section < 4) {
            $scope.currentSection = section + 1;
            nextPhase(section, true);
        }
    };

    $scope.sectionClicked = function($event){
        // $($event.currentTarget).index()   mettendo il li come ng-click
        // $($event.target.parentNode.parentNode).index()  mettendo h4 come ng-click
        let sectionClicked = $($event.target.parentNode.parentNode).index() + 1;
        if (sectionClicked > $scope.currentSection){
            for(let i=$scope.currentSection; i<=sectionClicked - 1; i++){
                nextPhase(i, true);
            }
        }
        else if (sectionClicked < $scope.currentSection){
            for(let i=sectionClicked; i<=$scope.currentSection; i++){
                nextPhase(i, false);
            }
        }
        else {
            console.log("No change");
        }
        $scope.currentSection = sectionClicked;
    };

    // PATIENT
    $scope.patient = null;
    $scope.addPatient = function(){
        addPatient($rootScope, $scope, $http, timelines);  //Add patient and medicalRecord
    };

    //FOLLOW-UP
    $scope.numFollowup = 0;
    $scope.localFollowup = [];
    $scope.localFollowup[$scope.numFollowup] = $scope.numFollowup;
    $scope.followup = [];
    $scope.addFollowup = function(){
        addFollowup($rootScope, $scope, $http, $location);
    };
    $scope.addFollowupAll = function(){
        addFollowupAll($rootScope, $scope, $http, $location);
    };


    $scope.addNewFollowup = function(element){
        if (!$scope.opRunning) {
            $scope.opRunning = true;
            $scope.numFollowup++;
            $scope.localFollowup.push($scope.numFollowup);
            addNewElement($scope, $compile, element, $scope.numFollowup, 'numFollowup', 'localFollowup', 'followup', 'deleteFollowup');
            $timeout(setRunning, 400);
        }
    };

    $scope.deleteFollowup = function(element){
        if (!$scope.opRunning) {
            $scope.opRunning = true;
            $(element).next().remove();
            $(element).remove();
            $scope.numFollowup--;
            $scope.localFollowup.pop();
            $scope.opRunning = false;
        }
    };

    // DIAGNOSI MIELOPROLIFERATIVA
    $scope.MyeloDiagnosis = null;
    $scope.addMyeloproliferativeDiagnosis = function(){
        $scope.MyeloDiagnosis = {};
        $scope.MyeloDiagnosis.medicalRecord = $scope.newMedicalRecord;
        $scope.MyeloDiagnosis.hospitalCode = $rootScope.userHospitalCode;
        $http({
            method: 'POST',
            url: $rootScope.prefixDomain + 'myeloproliferative/diagnosis/add',
            data: $scope.MyeloDiagnosis
        }).then(function (result) {
            console.log(result.data);
            fillModal($rootScope, "Result", "Myeloproliferative diagnosis registered successfully", "alert-success", "btn-success", true);
            listTherapiesLines($rootScope, $scope, $http);
            listTreatmentResponse($rootScope, $scope, $http);
            nextPhase($scope.currentSection++, true);
        }, function(err) {
            console.log("Error registering myeloproliferative diagnosis");
            console.log(err);
            fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
        });
    };

    // TERAPIA MIELOPROLIFERATIVA
    $scope.numTherapies = 0;
    $scope.localTherapy = [];
    $scope.localTherapy[$scope.numTherapies] = $scope.numTherapies;
    $scope.myeloTherapy = [];
    $scope.addTherapyMieloproliferative = function(){
        let nextPhasePass = 0;
        for (let ind=0; ind<$scope.myeloTherapy.length; ind++) {
            $scope.myeloTherapy[ind].medicalRecord = {};
            $scope.myeloTherapy[ind].medicalRecord = $scope.newMedicalRecord;
            $http({
                method: 'POST',
                url: $rootScope.prefixDomain + 'myeloproliferative/therapy/add',
                data: $scope.myeloTherapy[ind]
            }).then(function (result) {
                console.log(result.data);
                nextPhasePass++;
                if (nextPhasePass === $scope.myeloTherapy.length) {
                    fillModal($rootScope, "Result", "Myeloproliferative therapy registered successfully", "alert-success", "btn-success", true);
                    nextPhase($scope.currentSection++, true);
                }
            }, function (err) {
                console.log("Error register myeloproliferative diagnosis");
                console.log(err);
                fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
            });
        }
    };

    $scope.addNewTherapy = function(element){
        console.log("ADD " + $scope.opRunning);
        if (!$scope.opRunning) {
            $scope.opRunning = true;
            $scope.numTherapies++;
            $scope.localTherapy.push($scope.numTherapies);
            addNewElement($scope, $compile, element, $scope.numTherapies, 'numTherapies', 'localTherapy', 'myeloTherapy', 'deleteTherapy');
            // $scope.opRunning = false;
            $timeout(setRunning, 400);
        }
    };

    $scope.deleteTherapy = function(element){
        console.log("DELETE " + $scope.opRunning);
        if (!$scope.opRunning) {
            $scope.opRunning = true;
            $(element).next().remove();
            $(element).remove();
            $scope.numTherapies--;
            $scope.localTherapy.pop();
            $scope.opRunning = false;
        }
    };
});
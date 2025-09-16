// ###############################
// ###### UTILITY FUNCTIONS ######
// ###############################

function fillModal($rootScope, title, body, alertClass, btnClass, show) {
    $rootScope.modalShowed = false;
    $('#modalUserTitle').text(title);
    $('#modalUserBody').text(body);
    $('#modalUser .modal-header').addClass(alertClass);
    $('#modalCloseButton').addClass(btnClass);
    if (show && !$rootScope.modalShowed) {
        showModal($rootScope);
    }
}

function showModal($rootScope) {
    if (!$rootScope.modalShowed) {
        $('#modalUser').modal('show');
        $rootScope.modalShowed = true;
    }
}

function hideModal() {
    $('#modalUser').modal('show');
}

function showModalOnly(modalName) {
    $('#modal'+modalName).modal('show');
}

function hideModalOnly(modalName) {
    $('#modal'+modalName).modal('hide');
}

function checkLogin($rootScope, $http, $location) {
    $rootScope.load = false;
    $http({
        method: 'GET',
        url: '/bdn/check-login/'
    }).then(function (response) {
        if (response.data.length === 0){
            $rootScope.authenticated = false;
            $location.path("/");
        }
        else {
            $rootScope.authenticated = response.data.principal.authenticated;
            if ($rootScope.authenticated === false) {
                $location.path("/");
            }
            $rootScope.userRole = response.data.principal.authorities[0].authority;
            $rootScope.userName = response.data.principal.name;
            $rootScope.userHospitalCode = response.data.hospitalCode;
            $rootScope.load = true;
        }
    }, function(err) {
        console.log("Error get logged user");
        console.log(err);
        $rootScope.authenticated = false;
        $rootScope.load = true;
        $location.path("/");
    });
}


function getCategoryById($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/find-category/' +  $scope.medicalRecord.category
    }).then(function (result) {
        console.log(result);
        $scope.card = result.data.nome;
        $scope.medicalRecord.category = result.data;
        $scope.rendered = true;
    }, function(err) {
        console.log(err);
    });
}


//FUNCTION TO POPULATE THE COMBO-BOX
function listWho($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/who2016/'
    }).then(function (result) {
        console.log(result);
        $scope.whos = result.data;
    }, function(err) {
        console.log(err);
    });
}

function listSymptom($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/symptom/'
    }).then(function (result) {
        console.log(result);
        $scope.symptom = result.data;
    }, function(err) {
        console.log(err);
    });
}

function listBoneMarrowFibrosis($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/bone-marrow-fibrosis/'
    }).then(function (result) {
        console.log(result);
        $scope.boneMarrowFibrosis = result.data;
    }, function(err) {
        console.log(err);
    });
}

function listTherapiesLines($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/therapy-line/'
    }).then(function (result) {
        console.log(result);
        $scope.lineeTherapies = result.data;
    }, function(err) {
        console.log(err);
    });
}

function listTreatmentResponse($rootScope, $scope, $http){
    $http({
        method: 'GET',
        url: $rootScope.prefixDomain + 'medical-record/treatment-response/'
    }).then(function (result) {
        console.log(result);
        $scope.treatmentResponses = result.data;
    }, function(err) {
        console.log(err);
    });
}

function addNewElement($scope, $compile, element, currentIndex, medicalRecordName, arrayName, modelName, functionDeleteName){
    const terHeader = $(element).clone();
    const terBody = $(element).next().clone();
    terHeader.attr('id', terHeader.attr('id') + currentIndex);
    terHeader.find('button.btn-link').attr('data-target',  terHeader.find('button.btn-link').attr('data-target') + currentIndex);
    terHeader.find('button.btn-link').attr('aria-controls',  terHeader.find('button.btn-link').attr('aria-controls') + currentIndex);
    terHeader.find('button.btn-link > span:last-child').text(' - ' + (currentIndex + 1));
    terHeader.find('button.oi-plus').attr('ng-show', arrayName+'['+currentIndex+'] === '+medicalRecordName+'');
    terBody.attr('id', terBody.attr('id') + currentIndex);
    terBody.attr('aria-labelledby', terBody.attr('aria-labelledby') + currentIndex);
    terBody.removeClass('show');
    $(element).next().after(terHeader);
    terHeader.after(terBody);
    if (terHeader.find('button.oi-minus').length === 0){
        terHeader.find('button.oi-plus').before("<button type='button' class='oi oi-minus btn btn-danger app-btn-red' ng-click='"+functionDeleteName+"($event.target.parentNode.parentNode.parentNode)'>Delete</button>");
        terHeader.find('button.oi-minus').attr('ng-show', arrayName+'['+currentIndex+'] === '+medicalRecordName+'');
    }
    else {
        terHeader.find('button.oi-minus').attr('ng-show', arrayName+'['+currentIndex+'] === '+medicalRecordName+'');
    }
    $(terBody.find('.form-group')).each(function( index ) {
        // console.log( index + ": " + $(this).text() );
        const indexComma = $(this).find('.app-form-control').attr('ng-model').indexOf('.');
        $(this).find('.app-form-control').attr('ng-model', modelName+'['+currentIndex+']' + $(this).find('.app-form-control').attr('ng-model').substr(indexComma));
    });
    $compile(terHeader)($scope);  //Compile ng-click to make it work
    $compile(terBody)($scope);
}

function nextPhaseUtil(index, operation, timelines){
    let section = timelines[index];
    if (operation) $(section).addClass('complete'); else $(section).removeClass('complete');
}

function addPatient($rootScope, $scope, $http, timelines){
    $http({
        method: 'POST',
        url: $rootScope.prefixDomain + 'patient/add',
        data: $scope.patient
    }).then(function (result) {
        console.log(result.data);
        addMedicalRecord($rootScope, $scope, $http, result.data, timelines);
    }, function(err) {
        console.log("Error register patient");
        console.log(err);
        fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
    });
}

function addMedicalRecord($rootScope, $scope, $http, newPatient, timelines){
    $scope.medicalRecord.patient = newPatient;
    $http({
        method: 'POST',
        url: $rootScope.prefixDomain + 'medical-record/add',
        data: $scope.medicalRecord
    }).then(function (result) {
        console.log(result.data);
        $scope.newMedicalRecord = result.data;
        fillModal($rootScope, "Result", "Medical Record and Patient registered successfully", "alert-success", "btn-success", true);
        listSymptom($rootScope, $scope, $http);
        listWho($rootScope, $scope, $http);
        listBoneMarrowFibrosis($rootScope, $scope, $http);
        nextPhaseUtil($scope.currentSection++, true, timelines);
    }, function(err) {
        console.log("Error register medicalRecord");
        console.log(err);
        fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
    });
}

function addFollowup($rootScope, $scope, $http, $location){
    let nextPhasePass = 0;
    for (let ind=0; ind<=$scope.followup.length; ind++) {
        $scope.followup[ind].medicalRecord = {};
        $scope.followup[ind].medicalRecord = $scope.newMedicalRecord;
        $http({
            method: 'POST',
            url: $rootScope.prefixDomain + 'myeloproliferative/followup/add',
            data: $scope.followup[ind]
        }).then(function (result) {
            console.log(result.data);
            nextPhasePass++;
            if (nextPhasePass === $scope.followup.length) {
                fillModal($rootScope, "Result", "FollowUp and Medical Record registered successfully", "alert-success", "btn-success", false);
                $location.path('/main');
            }
        }, function (err) {
            console.log("Error register followUp");
            console.log(err);
            fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
        });
    }
}

function addFollowupAll($rootScope, $scope, $http, $location){
    for (let ind=0; ind<$scope.followup.length; ind++) {
        $scope.followup[ind].medicalRecord = {};
        $scope.followup[ind].medicalRecord = $scope.newMedicalRecord;
    }
    $http({
        method: 'POST',
        url: $rootScope.prefixDomain + 'myeloproliferative/followup/addAll',
        data: $scope.followup
    }).then(function (result) {
        console.log(result.data);
        fillModal($rootScope, "Result", "FollowUp and Medical Record registered successfully", "alert-success", "btn-success", false);
        $location.path('/main');
    }, function (err) {
        console.log("Error register followUp");
        console.log(err);
        fillModal($rootScope, "Error", err.data.message, "alert-danger", "btn-danger", true);
    });
}

function deleteCookie(name) {
    document.cookie = name+'=; Max-Age=-99999999;';
}
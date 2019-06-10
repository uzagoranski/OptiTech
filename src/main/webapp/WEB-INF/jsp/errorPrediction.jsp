<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="OptiTech connected vehicle platform">
    <meta name="author" content="OptiTech">
    <meta name="keywords" content="OptiTech">

    <title>Artificial intelligence stats</title>

    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <link href="css/theme.css" rel="stylesheet" media="all">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

</head>

<body class="animsition">
<div class="page-wrapper">
    <div class="page-container">
        <%@include file="template/navigation.jsp" %>
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row" style="background-color: whitesmoke; border-radius: 10px; padding: 25px">
                        <div class="col-lg-12">
                            <c:choose>
                                <c:when test="${error != ''}">
                                    <div class="alert alert-danger" role="alert">
                                        Calculated most possible error: ${error}
                                    </div>
                                </c:when>
                            </c:choose>
                            <div class="card">
                                <div class="card-header">Enter your data</div>
                                <div class="card-body">
                                    <div class="card-title">
                                        <h3 class="text-center title-2">Vehicle error prediction</h3>
                                    </div>
                                    <hr>
                                    <form action="/errorPrediction" method="post">
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="vehicleSelect" class="form-control-label">Vehicle</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <select name="vehicleSelect" id="vehicleSelect" class="form-control" href="http://localhost:8080/statsAI?id=${idCar}">
                                                    <option value="0">Please select your vehicle</option>
                                                    <c:forEach items="${vehicles}" var="v">
                                                        <option value="${v.getVehicleId()}">${v.getCarMaker()} ${v.getVehicleTitle()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <input type="hidden" name="idInput" id="idInput"/>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="yearInput" class=" form-control-label">Year</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <input type="text" id="yearInput" name="yearInput"
                                                           disabled="" class="form-control">
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="fuelInput" class=" form-control-label">Fuel</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <input type="text" id="fuelInput" name="fuelInput"
                                                            disabled="" class="form-control">
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="engineInput" class=" form-control-label">Engine</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <input type="text" id="engineInput" name="engineInput"
                                                           disabled="" class="form-control">
                                                </div>
                                            </div>
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="driveInput" class=" form-control-label">Drive</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <input type="text" id="driveInput" name="driveInput"
                                                            disabled="" class="form-control">
                                                </div>
                                            </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="timeInput" class=" form-control-label">Trip time (sec)</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="timeInput" name="timeInput"
                                                       placeholder="Enter your trip time..."
                                                       class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="distanceInput" class=" form-control-label">Trip distance (m)</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="distanceInput" name="distanceInput"
                                                       placeholder="Enter your trip distance..."
                                                       class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="speedInput" class="form-control-label">Average speed (km/h)</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="speedInput" name="speedInput"
                                                       placeholder="Enter your average speed..." class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="RPMInput" class=" form-control-label">Average RPM (rpm)</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="RPMInput" name="RPMInput"
                                                       placeholder="Enter your average RPM..." class="form-control">
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col col-md-3">
                                                <label for="ODODistanceInput" class=" form-control-label">Total mileage (m)</label>
                                            </div>
                                            <div class="col-12 col-md-9">
                                                <input type="text" id="ODODistanceInput" name="ODODistanceInput"
                                                       placeholder="Enter your total distance..." class="form-control">
                                            </div>
                                        </div>
                                        <div>
                                            <button id="payment-button" type="submit"
                                                    class="btn btn-lg btn-info btn-block">
                                                <i class="fa fa-save fa-lg"></i>&nbsp;
                                                <span id="payment-button-amount">Submit</span>
                                                <span id="payment-button-sending" style="display:none;">Sending…</span>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="vendor/jquery-3.2.1.min.js"></script>

<script>
    var podatki = {};
    jQuery(document).ready(function () {
       jQuery('#vehicleSelect').change(function () {
           var id = $("#vehicleSelect").val();
           var link ="/statsAI?id=" + id;
           jQuery.ajax({
              type: 'POST',
              url: link,
               datatype: 'json',
               data: JSON.stringify(podatki),
               success: function (result) {
                  var json = JSON.parse(result);
                  var year = json['year'];
                  var fuel = json['fuel'];
                  var enginePower = json['enginePower'];
                  var engineSize = json['engineSize'];
                  var drive = json['drive'];
                  var vehicleId = json['id'];
                  jQuery('input[name="yearInput"]').val(year);
                   jQuery('input[name="engineInput"]').val(enginePower+" HP, " + engineSize + " ccm");
                   jQuery('input[name="driveInput"]').val(drive);
                   jQuery('input[name="fuelInput"]').val(fuel);
                   jQuery('input[name="idInput"]').val(vehicleId);
               }
           });
       });
    });
</script>

<script src="vendor/bootstrap-4.1/popper.min.js"></script>
<script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
<script src="vendor/slick/slick.min.js"></script>
<script src="vendor/wow/wow.min.js"></script>
<script src="vendor/animsition/animsition.min.js"></script>
<script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<script src="vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="vendor/counter-up/jquery.counterup.min.js"></script>
<script src="vendor/circle-progress/circle-progress.min.js"></script>
<script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="vendor/chartjs/Chart.bundle.min.js"></script>
<script src="vendor/select2/select2.min.js"></script>
<script src="js/main.js"></script>

</body>

</html>
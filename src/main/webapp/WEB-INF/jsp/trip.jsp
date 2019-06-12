<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="OptiTech connected vehicle platform">
    <meta name="author" content="OptiTech">
    <meta name="keywords" content="OptiTech">

    <title>Trip details</title>

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

    <style type="text/css">
        .outer {
            width: 600px;
            height: 200px;
            margin: 0 auto;
        }

        .outer .chart-container {
            width: 300px;
            float: left;
            height: 200px;
        }

        .highcharts-yaxis-grid .highcharts-grid-line {
            display: none;
        }

        @media (max-width: 600px) {
            .outer {
                width: 100%;
                height: 400px;
            }

            .outer .chart-container {
                width: 300px;
                float: none;
                margin: 0 auto;
            }

        }

        .chart {
            min-width: 320px;
            max-width: 800px;
            height: 220px;
            margin: 0 auto;
        }

        /* Tooltip container */
        .tooltip2 {
        }

        /* Tooltip text */
        .tooltiptext2 {
            visibility: hidden;
            background-color: black;
            color: #fff;
            text-align: center;
            padding: 10px 10px 10px 10px;
            border-radius: 6px;

            /* Position the tooltip text - see examples below! */
            position: absolute;
            z-index: 1;
        }

        /* Show the tooltip text when you mouse over the tooltip container */
        .tooltip2:hover .tooltiptext2 {
            visibility: visible;
        }
    </style>
</head>

<body class="animsition">
<div class="page-wrapper">
    <div class="page-container">
        <%@include file="template/navigation.jsp" %>
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid" style="background-color: whitesmoke; border-radius: 10px; padding: 25px">
                    <div class="row">
                        <div class="col-lg-6">
                            <h1>Trip details</h1><br>
                            <p style="padding-left: 10px">Start:<b> ${tripData.startTime}</b></p>
                            <p style="padding-left: 10px">End:<b> ${tripData.finishTime}</b></p>
                            <p style="padding-left: 10px">Distance:<b> ${tripData.distance} km</b></p>
                            <p style="padding-left: 10px">Driver score:<b> ${tripData.totalScore}/100</b></p>

                        </div>
                        <div class="col-lg-6">
                            <h3 style="text-align: center">${vehicle.getCarMaker()} ${vehicle.getVehicleTitle()}</h3>
                            <img class="avto" src="${carImage}">
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="row">
                        <div class="col-lg-6">
                            <canvas id="speedChart" width="800" height="450"></canvas>
                        </div>
                        <div class="col-lg-6">
                            <canvas id="rpmChart" width="800" height="450"></canvas>
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
<script>
    <c:set var="json" value="${jsonVss}"></c:set>
    var date = ${json.get("date")};
    var vssAvg = ${json.get("vssAvg")};

    var speedChart = new Chart(document.getElementById("speedChart"), {
        type: 'line',
        data: {
            labels: date
            ,
            datasets: [{
                data: vssAvg,
                label: "Speed",
                borderColor: "#3e95cd",
                fill: false
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Average speed'
            }
        }
    });

    <c:set var="jsonRpm2" value="${jsonRpm}"></c:set>
    var rpmDate =${jsonRpm2.get("date2")};
    var rpmAvg =${jsonRpm2.get("rpmAvg")};
    var rpmChart = new Chart(document.getElementById('rpmChart'), {
        type: 'bar',
        data: {
            labels: rpmDate,
            datasets: [{
                data: rpmAvg,
                label: "RPM",
                borderColor: "#3e95cd",
                fill: false
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Average Rpm'
            }
        }
    });

    //    BAR CHART
    var value =${tripData.totalScore};
    var max = 100;

    var bar_ctx = document.getElementById('bar-chart');
    var bar_chart = new Chart(bar_ctx, {
        type: 'horizontalBar',
        data: {
            labels: [],
            datasets: [{

                data: [value],
                backgroundColor: "${color}"
            }, {
                data: [max - value],
                backgroundColor: "lightgrey",
            },]
        },
        options: {
            legend: {
                display: false
            },
            tooltips: {
                enabled: false
            },
            scales: {
                xAxes: [{
                    display: false,
                    stacked: true
                }],
                yAxes: [{
                    display: false,
                    stacked: true
                }],
            } // scales
        } // options
    });
</script>

</body>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="OptiTech connected vehicle platform">
    <meta name="author" content="OptiTech">
    <meta name="keywords" content="OptiTech">

    <title>Vehicle details</title>

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

    <link rel="stylesheet" href="css/rSlider.min.css">
    <script src="js/rSlider.min.js"></script>

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
    </style>
</head>

<body class="animsition">
<div class="page-wrapper">
    <div class="page-container">
        <%@include file="template/navigation.jsp" %>
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row" style="background-color: whitesmoke; border-radius: 10px; padding: 25px">
                        <div class="col-lg-6 mx-auto" id="carView">
                            <h1 align="left"
                                class="title-1 m-b-25">${vehicle.getCarMaker()} ${vehicle.getVehicleTitle()}</h1>
                            <div align="left">
                                <span><i>Year: </i> <b> ${vehicle.getCarModelYear()}</b></span><br/>
                                <span><i>Fuel: </i> <b> ${fuel}</b></span><br/>
                                <span><i>Engine: </i> <b> ${vehicle.getEngineSize()} ccm, ${vehicle.getEnginePower()} HP</b></span><br/>
                                <span><i>Drive: </i> <b> ${drive}</b></span><br/>
                                <span><i>Registry number: </i> <b> ${vehicle.getRegNumber()}</b></span><br/>
                                <span><i>Vin: </i> <b> ${vehicle.getVin()}</b></span><br/>
                            </div>
                            <br/>
                        </div>
                        <div class="col-lg-6 mx-auto" id="image">
                            <img class="avto" src="${linkImage}">
                            <canvas id="bar-chart" height="20"></canvas>
                        </div>
                    </div>
                    <hr/>
                    <div class="row">
                        <div class="col-lg-12">
                            <input type="text"
                                   href="http://localhost:8080/carDetails?id=${idCar}&sliderValue=${sliderValue}"
                                   id="slider" class="slider" style="margin: 10px">
                        </div>
                    </div>
                    <div class="row" style="background-color: whitesmoke; border-radius: 10px">
                        <div class="col-lg-6">
                            <canvas id="speedChart" width="800" height="450"></canvas>
                        </div>
                        <div class="col-lg-6">
                            <canvas id="rpmChart" width="800" height="450"></canvas>
                        </div>
                    </div>
                </div>
            </div>
            <br />
            <br />

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

<script type="text/javascript">
    var value =
    ${score}
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


<script>
    <c:set var="json" value="${jsonSpeed}"></c:set>
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
                text: 'Vehicle speed per Log'
            }
        }
    });
    <c:set var="jsonRpm2" value="${jsonRpm}"></c:set>
    var rpmDate =
    ${jsonRpm2.get("date2")}
    var rpmAvg =
    ${jsonRpm2.get("rpmAvg")}
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
                text: 'Vehicle speed per Log'
            }
        }
    });

    var podatki = {};
    <c:set var="jsonSlider" value="${sliderRange}"></c:set>
    var mySlider = new rSlider({
        target: '#slider',
        values: ${jsonSlider.get("rangeDates")}
        ,
        range: true,
        onChange: function (href) {
            var link = "/carDetails?id=${idCar}&sliderValue=" + mySlider.getValue();
            $.ajax({
                url: link,
                type: 'POST',
                dataType: 'json',
                data: JSON.stringify(podatki),
                cache: false,
                success: function (result) {

                    //SPEED CHART
                    vssAvg = result[0]['vssAvg'];
                    date = result[0]['date'];
                    speedChart.destroy();
                    speedChart = new Chart(document.getElementById("speedChart"), {


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
                                text: 'Vehicle speed per Log'
                            }
                        }
                    });


                    //RPM CHART
                    rpmDate = result[1]['date2'];
                    rpmAvg =result[1]['rpmAvg'];
                    rpmChart.destroy();
                        rpmChart = new Chart(document.getElementById('rpmChart'), {
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
                                    text: 'Vehicle speed per Log'
                                }
                            }
                        });

                    // $('#target').html(JSON.stringify(result));
                    // $.validator.unobtrusive.parse($("form#ValidateForm"));
                }
            });
            window.history.pushState({href: href}, '', "carDetails?id=${idCar}&sliderValue=" + href);

            $(document).ready(function () {
                $(document).on('change', 'slider', function () {
                    openURL($(this).attr("href"));
                    return false; //intercept the link
                });

                window.addEventListener('popstate', function (e) {
                    if (e.state)
                        openURL(e.state.href);
                });

            });
        }
    });

</script>
</body>

</html>
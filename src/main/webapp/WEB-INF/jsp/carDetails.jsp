<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title Page-->
    <title>Vehicle details</title>

    <!-- Fontfaces CSS-->
    <link href="css/font-face.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

    <!-- Bootstrap CSS-->
    <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
    <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="css/theme.css" rel="stylesheet" media="all">

    <!-- Charts-->
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
    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <%@include file="template/navigation.jsp"%>

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row" style="background-color: whitesmoke; border-radius: 25px; padding: 25px">
                            <div class="col-lg-6 mx-auto" id="carView">
                                <h1 align="left" class="title-1 m-b-25">${vehicle.getCarMaker()} ${vehicle.getVehicleTitle()}</h1>
                                <div align="left">
                                    <span><i>Year: </i> <b> ${vehicle.getCarModelYear()}</b></span><br/>
                                    <span><i>Fuel: </i> <b> ${fuel}</b></span><br/>
                                    <span><i>Engine: </i> <b> ${vehicle.getEngineSize()} ccm, ${vehicle.getEnginePower()} HP</b></span><br/>
                                    <span><i>Drive: </i> <b> ${drive}</b></span><br/>
                                    <span><i>Registry number: </i> <b> ${vehicle.getRegNumber()}</b></span><br/>
                                    <span><i>Vin: </i> <b> ${vehicle.getVin()}</b></span><br/>
                                </div><br/>
                            </div>
                            <div class ="col-lg-6 mx-auto" id="image">
                                <img class="avto" src="${linkImage}">
                            </div>
                    </div>
                    <hr/>

                    <script src="highcharts/code/highcharts.js"></script>
                    <script src="highcharts/code/highcharts-more.js"></script>

                    <script src="highcharts/code/modules/solid-gauge.js"></script>
                    <div class="outer">
                        <div id="container-speed" class="chart-container"></div>
                        <div id="container-rpm" class="chart-container"></div>
                    </div>

                    <br/>
                    <script src="highcharts/code/highcharts.js"></script>
                    <script src="highcharts/code/modules/data.js"></script>
                    <div id="container"></div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<!-- Jquery JS-->
<script src="vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="vendor/bootstrap-4.1/popper.min.js"></script>
<script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="vendor/slick/slick.min.js">
</script>
<script src="vendor/wow/wow.min.js"></script>
<script src="vendor/animsition/animsition.min.js"></script>
<script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
</script>
<script src="vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="vendor/counter-up/jquery.counterup.min.js">
</script>
<script src="vendor/circle-progress/circle-progress.min.js"></script>
<script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="vendor/chartjs/Chart.bundle.min.js"></script>
<script src="vendor/select2/select2.min.js"></script>

<!-- Main JS-->
<script src="js/main.js"></script>

<!-- Charts-->

<script type="text/javascript">
    var gaugeOptions = {

        chart: {
            type: 'solidgauge'
        },

        title: null,

        pane: {
            center: ['50%', '85%'],
            size: '140%',
            startAngle: -90,
            endAngle: 90,
            background: {
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                innerRadius: '60%',
                outerRadius: '100%',
                shape: 'arc'
            }
        },

        tooltip: {
            enabled: false
        },

        // the value axis
        yAxis: {
            stops: [
                [0.1, '#55BF3B'], // green
                [0.5, '#DDDF0D'], // yellow
                [0.9, '#DF5353'] // red
            ],
            lineWidth: 0,
            minorTickInterval: null,
            tickAmount: 2,
            title: {
                y: -70
            },
            labels: {
                y: 16
            }
        },

        plotOptions: {
            solidgauge: {
                dataLabels: {
                    y: 5,
                    borderWidth: 0,
                    useHTML: true
                }
            }
        }
    };

    // The speed gauge
    var chartSpeed = Highcharts.chart('container-speed', Highcharts.merge(gaugeOptions, {
        yAxis: {
            min: 0,
            max: 200,
            title: {
                text: 'Speed'
            }
        },

        credits: {
            enabled: false
        },

        series: [{
            name: 'Speed',
            data: [80],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y}</span><br/>' +
                    '<span style="font-size:12px;color:silver">km/h</span></div>'
            },
            tooltip: {
                valueSuffix: ' km/h'
            }
        }]

    }));

    // The RPM gauge
    var chartRpm = Highcharts.chart('container-rpm', Highcharts.merge(gaugeOptions, {
        yAxis: {
            min: 0,
            max: 5,
            title: {
                text: 'RPM'
            }
        },

        series: [{
            name: 'RPM',
            data: [1],
            dataLabels: {
                format: '<div style="text-align:center"><span style="font-size:25px;color:' +
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y:.1f}</span><br/>' +
                    '<span style="font-size:12px;color:silver">* 1000 / min</span></div>'
            },
            tooltip: {
                valueSuffix: ' revolutions/min'
            }
        }]

    }));

    // Bring life to the dials
    setInterval(function () {
        // Speed
        var point,
            newVal,
            inc;

        if (chartSpeed) {
            point = chartSpeed.series[0].points[0];
            inc = Math.round((Math.random() - 0.5) * 100);
            newVal = point.y + inc;

            if (newVal < 0 || newVal > 200) {
                newVal = point.y - inc;
            }

            point.update(newVal);
        }

        // RPM
        if (chartRpm) {
            point = chartRpm.series[0].points[0];
            inc = Math.random() - 0.5;
            newVal = point.y + inc;

            if (newVal < 0 || newVal > 5) {
                newVal = point.y - inc;
            }

            point.update(newVal);
        }
    }, 2000);

</script>

<script type="text/javascript">
    /*
    The purpose of this demo is to demonstrate how multiple charts on the same page
    can be linked through DOM and Highcharts events and API methods. It takes a
    standard Highcharts config with a small variation for each data set, and a
    mouse/touch event handler to bind the charts together.
    */


    /**
     * In order to synchronize tooltips and crosshairs, override the
     * built-in events with handlers defined on the parent element.
     */
    ['mousemove', 'touchmove', 'touchstart'].forEach(function (eventType) {
        document.getElementById('container').addEventListener(
            eventType,
            function (e) {
                var chart,
                    point,
                    i,
                    event;

                for (i = 0; i < Highcharts.charts.length; i = i + 1) {
                    chart = Highcharts.charts[i];
                    // Find coordinates within the chart
                    event = chart.pointer.normalize(e);
                    // Get the hovered point
                    point = chart.series[0].searchPoint(event, true);

                    if (point) {
                        point.highlight(e);
                    }
                }
            }
        );
    });

    /**
     * Override the reset function, we don't need to hide the tooltips and
     * crosshairs.
     */
    Highcharts.Pointer.prototype.reset = function () {
        return undefined;
    };

    /**
     * Highlight a point by showing tooltip, setting hover state and draw crosshair
     */
    Highcharts.Point.prototype.highlight = function (event) {
        event = this.series.chart.pointer.normalize(event);
        this.onMouseOver(); // Show the hover marker
        this.series.chart.tooltip.refresh(this); // Show the tooltip
        this.series.chart.xAxis[0].drawCrosshair(event, this); // Show the crosshair
    };

    /**
     * Synchronize zooming through the setExtremes event handler.
     */
    function syncExtremes(e) {
        var thisChart = this.chart;

        if (e.trigger !== 'syncExtremes') { // Prevent feedback loop
            Highcharts.each(Highcharts.charts, function (chart) {
                if (chart !== thisChart) {
                    if (chart.xAxis[0].setExtremes) { // It is null while updating
                        chart.xAxis[0].setExtremes(
                            e.min,
                            e.max,
                            undefined,
                            false,
                            { trigger: 'syncExtremes' }
                        );
                    }
                }
            });
        }
    }

    // Get the data. The contents of the data file can be viewed at
    Highcharts.ajax({
        url: 'https://cdn.jsdelivr.net/gh/highcharts/highcharts@v7.0.0/samples/data/activity.json',
        dataType: 'text',
        success: function (activity) {

            activity = JSON.parse(activity);
            activity.datasets.forEach(function (dataset, i) {

                // Add X values
                dataset.data = Highcharts.map(dataset.data, function (val, j) {
                    return [activity.xData[j], val];
                });

                var chartDiv = document.createElement('div');
                chartDiv.className = 'chart';
                document.getElementById('container').appendChild(chartDiv);

                Highcharts.chart(chartDiv, {
                    chart: {
                        marginLeft: 40, // Keep all charts left aligned
                        spacingTop: 20,
                        spacingBottom: 20
                    },
                    title: {
                        text: dataset.name,
                        align: 'left',
                        margin: 0,
                        x: 30
                    },
                    credits: {
                        enabled: false
                    },
                    legend: {
                        enabled: false
                    },
                    xAxis: {
                        crosshair: true,
                        events: {
                            setExtremes: syncExtremes
                        },
                        labels: {
                            format: '{value} km'
                        }
                    },
                    yAxis: {
                        title: {
                            text: null
                        }
                    },
                    tooltip: {
                        positioner: function () {
                            return {
                                // right aligned
                                x: this.chart.chartWidth - this.label.width,
                                y: 10 // align to title
                            };
                        },
                        borderWidth: 0,
                        backgroundColor: 'none',
                        pointFormat: '{point.y}',
                        headerFormat: '',
                        shadow: false,
                        style: {
                            fontSize: '18px'
                        },
                        valueDecimals: dataset.valueDecimals
                    },
                    series: [{
                        data: dataset.data,
                        name: dataset.name,
                        type: dataset.type,
                        color: Highcharts.getOptions().colors[i],
                        fillOpacity: 0.3,
                        tooltip: {
                            valueSuffix: ' ' + dataset.unit
                        }
                    }]
                });
            });
        }
    });

</script>
</body>

</html>
<!-- end document-->

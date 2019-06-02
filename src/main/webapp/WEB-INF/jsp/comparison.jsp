<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.json.JSONObject" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Title Page-->
    <title>Comparison</title>

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
    <link href="css/reset.css" rel="stylesheet" media="all">
    <link href="css/style.css" rel="stylesheet" media="all">

    <!-- Charts-->
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
                    <section class="cd-products-comparison-table">
                        <header>
                            <h2>Compare Vehicles</h2>

                            <div class="actions">
                                <a href="#0" class="reset">Reset</a>
                                <a href="#0" class="filter">Filter</a>
                            </div>
                        </header>

                        <div class="cd-products-table">
                            <div class="features">
                                <div class="top-info">Modeli</div>
                                <ul class="cd-features-list">
                                    <li>Engine size</li>
                                    <li>Engine power</li>
                                    <!-- other features here -->
                                </ul>
                            </div> <!-- .features -->

                            <div class="cd-products-wrapper">
                                <ul class="cd-products-columns">
                                    <c:forEach items="${vehicles}" var="v">
                                        <li class="product">
                                            <div class="top-info">
                                                <div class="check"></div>
                                                <img src="${v.getImgLink()}" alt="product image">
                                                <h3>${v.getVehicleTitle()}</h3>
                                            </div> <!-- .top-info -->

                                            <ul class="cd-features-list">
                                                <li>${v.getEngineSize()}</li>
                                                <li>${v.getEnginePower()}</li>
                                                <!-- other values here -->
                                            </ul>
                                        </li> <!-- .product -->
                                    </c:forEach>
                                </ul> <!-- .cd-products-columns -->
                            </div> <!-- .cd-products-wrapper -->

                            <ul class="cd-table-navigation">
                                <li><a href="#0" class="prev inactive">Prev</a></li>
                                <li><a href="#0" class="next">Next</a></li>
                            </ul>
                        </div> <!-- .cd-products-table -->
                    </section> <!-- .cd-products-comparison-table -->
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
<script src="js/modernizr.js"></script>


</body>

</html>
<!-- end document-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="OptiTech connected vehicle platform">
    <meta name="author" content="OptiTech">
    <meta name="keywords" content="OptiTech">

    <title>Comparison</title>

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
    <link href="css/reset.css" rel="stylesheet" media="all">
    <link href="css/comparison.css" rel="stylesheet" media="all">

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

    <style type="text/css">
        .top-info {
            border-bottom: 0;
        }

        li {
            border-left: 0;
            border-top: 1px;
            border-bottom: 0;
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
                    <section class="cd-products-comparison-table">
                        <header>
                            <h2>VEHICLE COMPARATOR</h2>

                            <div class="actions">
                                <a href="#0" class="reset">Reset</a>
                                <a href="#0" class="filter">Filter</a>
                            </div>
                        </header>
                        <div class="cd-products-table">
                            <div class="features">
                                <div class="top-info">Vehicle</div>
                                <ul class="cd-features-list">
                                    <li>Condition</li>
                                    <li>Model year</li>
                                    <li>Engine power</li>
                                    <li>Engine size</li>
                                    <li>Fuel type</li>
                                    <li>Drive</li>
                                    <li>Max speed</li>
                                    <li>Max RPM</li>
                                </ul>
                            </div>

                            <div class="cd-products-wrapper">
                                <ul class="cd-products-columns" style="width: 5800px">
                                    <c:forEach items="${vehicles}" var="v">
                                        <li class="product">
                                            <div class="top-info">
                                                <div class="check"></div>
                                                <div class="vehicle">
                                                    <img style="max-height: 120px" src="${v.getImgLink()}" alt="vehicle">
                                                    <h3>${v.getCarMaker()} ${v.getVehicleTitle()}</h3>
                                                </div>
                                            </div>
                                            <ul class="cd-features-list">
                                                <li style="line-height: 16px;">${v.getConditionScore()}%</li>
                                                <li style="line-height: 16px;">${v.getCarModelYear()}</li>
                                                <li style="line-height: 16px;">${v.getEnginePower()} HP</li>
                                                <li style="line-height: 16px;">${v.getEngineSize()} ccm</li>
                                                <li style="line-height: 16px;">${v.getFuelType()}</li>
                                                <li style="line-height: 16px;">${v.getDrivenWheels()}</li>
                                                <li style="line-height: 16px;">${v.getMaxSpeed()} km/h</li>
                                                <li style="line-height: 16px;">${v.getMaxRpm()} rpm</li>
                                            </ul>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>

                            <ul class="cd-table-navigation">
                                <li><a href="#0" class="prev inactive">Prev</a></li>
                                <li><a href="#0" class="next">Next</a></li>
                            </ul>
                        </div>
                    </section>
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
<script src="js/modernizr.js"></script>
<script src="js/comparison.js"></script>

</body>

</html>
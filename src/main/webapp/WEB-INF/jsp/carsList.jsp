<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="OptiTech connected vehicle platform">
    <meta name="author" content="OptiTech">
    <meta name="keywords" content="OptiTech">

    <title>Vehicles list</title>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

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
    <link href="css/style.css" rel="stylesheet" media="all">
</head>

<body class="animsition">
<div class="page-wrapper">
    <div class="page-container">
        <%@include file="template/navigation.jsp" %>
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="table-responsive table--no-card m-b-40">
                                <table class="table table-borderless table-striped table-earning">
                                    <thead>
                                    <tr>
                                        <th>Vehicle</th>
                                        <th>Year</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${vehicles}" var="v">
                                        <tr class='clickable-row'
                                            data-href='http://localhost:8080/carsList?id=${v.getVehicleId()}'>
                                            <td class="basicData">${v.getCarMaker()} ${v.getVehicleTitle()}</td>
                                            <td class="basicData">${v.getCarModelYear()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="col-lg-6 mx-auto" id="carView"
                             style="background-color: whitesmoke; border-radius: 10px; padding: 25px; max-height: 570px">
                            <h1 align="center"
                                class="title-1 m-b-25">${vehicle.getCarMaker()} ${vehicle.getVehicleTitle()}</h1>
                            <div align="center">
                                <img class="avto" src="${slika}">
                            </div>
                            <br/>
                            <div align="center">
                                <span><i>Year: </i> <b> ${vehicle.getCarModelYear()}</b></span><br/>
                                <span><i>Fuel: </i> <b> ${fuel}</b></span><br/>
                                <span><i>Engine size: </i> <b> ${vehicle.getEngineSize()} ccm</b></span><br/>
                                <span><i>Horsepower: </i> <b> ${vehicle.getEnginePower()} HP</b></span><br/>
                            </div>
                            <br/>
                            <div class="sredina">
                                <a href="http://localhost:8080/carDetails?id=${vehicle.getVehicleId()}">
                                    <button class="gumb" type="submit">Stats</button>
                                </a>
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
<script src="js/myJS.js"></script>

</body>

</html>
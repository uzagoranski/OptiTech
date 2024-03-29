<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="user" value="${user}"/>
<c:set var="email" value="${email}"/>
<c:set var="name" value="${name}"/>
<c:set var="image" value="${image}"/>

<c:choose>
    <c:when test="${user == 'anonymousUser'}">
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="index">
                            <img src="images/icon/logo.png" alt="OptiTech" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub active">
                            <a href="index">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                        </li>
                        <li>
                            <a href="carsList">
                                <i class="fas fa-chart-bar"></i>Vehicles</a>
                        </li>
                        <li>
                            <a href="comparison">
                                <i class="fas fa-table"></i>Comparison</a>
                        </li>
                        <li>
                            <a href="errorPrediction">
                                <i class="far fa-check-square"></i>Error prediction</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="index">
                    <img src="images/icon/logo.png" alt="OptiTech" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="has-sub">
                            <a href="index"><i class="fas fa-tachometer-alt"></i>Dashboard</a>
                        </li>

                        <li class="has-sub">
                            <a href="carsList">
                                <i class="fa fa-car"></i>Vehicles</a>
                        </li>
                        <li>
                            <a href="comparison">
                                <i class="fas fa-table"></i>Comparison</a>
                        </li>
                        <li>
                            <a href="errorPrediction">
                                <i class="far fa-check-square"></i>Error prediction</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>

        <div class="page-container">
            <header class="header-desktop">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap">
                            <div></div>
                            <div class="header-button">
                                <form method="get" action="login">
                                    <input type="submit" class="login-btn" value="LOGIN"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
    </c:when>
    <c:otherwise>
        <header class="header-mobile d-block d-lg-none">
            <div class="header-mobile__bar">
                <div class="container-fluid">
                    <div class="header-mobile-inner">
                        <a class="logo" href="index">
                            <img src="images/icon/logo.png" alt="OptiTech" />
                        </a>
                        <button class="hamburger hamburger--slider" type="button">
                                    <span class="hamburger-box">
                                        <span class="hamburger-inner"></span>
                                    </span>
                        </button>
                    </div>
                </div>
            </div>
            <nav class="navbar-mobile">
                <div class="container-fluid">
                    <ul class="navbar-mobile__list list-unstyled">
                        <li class="has-sub active">
                            <a class="js-arrow" href="index">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                        </li>
                        <li>
                            <a href="carsList">
                                <i class="fas fa-chart-bar"></i>Vehicles</a>
                        </li>
                        <li>
                            <a href="comparison">
                                <i class="fas fa-table"></i>Comparison</a>
                        </li>
                        <li>
                            <a href="errorPrediction">
                                <i class="far fa-check-square"></i>Error prediction</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="index">
                    <img src="images/icon/logo.png" alt="OptiTech" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="has-sub">
                            <a href="index"><i class="fas fa-tachometer-alt"></i>Dashboard</a>
                        </li>

                        <li class="has-sub">
                            <a href="carsList">
                                <i class="fa fa-car"></i>Vehicles</a>
                        </li>
                        <li>
                            <a href="comparison">
                                <i class="fas fa-table"></i>Comparison</a>
                        </li>
                        <li>
                            <a href="errorPrediction">
                                <i class="far fa-check-square"></i>Error prediction</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </aside>

        <div class="page-container">
            <header class="header-desktop">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">
                        <div class="header-wrap">
                            <div></div>
                            <div class="header-button">
                                <div class="noti-wrap">

                                </div>
                                <div class="account-wrap">
                                    <div class="account-item clearfix js-item-menu">
                                        <div class="image">
                                            <img src="${image}" alt="${name}" />
                                        </div>
                                        <div class="content">
                                            <a class="js-acc-btn" href="#">${name}</a>
                                        </div>
                                        <div class="account-dropdown js-dropdown">
                                            <div class="info clearfix">
                                                <div class="image">
                                                    <a href="#">
                                                        <img src="${image}" alt="${name}" />
                                                    </a>
                                                </div>
                                                <div class="content">
                                                    <h5 class="name">
                                                        <a href="#">${name}</a>
                                                    </h5>
                                                    <span class="email">${email}</span>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__body">
                                                <div class="account-dropdown__item">
                                                    <a href="account">
                                                        <i class="zmdi zmdi-account"></i>Account</a>
                                                </div>
                                            </div>
                                            <div class="account-dropdown__footer">
                                                <a href="logout">
                                                    <i class="zmdi zmdi-power"></i>Logout</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
        </div>
    </c:otherwise>
</c:choose>
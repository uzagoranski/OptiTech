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
                        <a class="logo" href="/">
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
                            <a href="carsList">
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
                            <a href="statsAI">
                                <i class="far fa-check-square"></i>AI</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="/">
                    <img src="images/icon/logo.png" alt="OptiTech" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="has-sub">
                            <a href="/"><i class="fas fa-tachometer-alt"></i>Dashboard</a>
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
                            <a href="statsAI">
                                <i class="far fa-check-square"></i>AI</a>
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
                                <a href="login">LOGIN</a>
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
                        <a class="logo" href="/">
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
                            <a class="js-arrow" href="#">
                                <i class="fas fa-tachometer-alt"></i>Dashboard</a>
                            <ul class="navbar-mobile-sub__list list-unstyled js-sub-list">
                                <li>
                                    <a href="/">Home</a>
                                </li>
                            </ul>
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
                            <a href="statsAI">
                                <i class="far fa-check-square"></i>AI</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>

        <aside class="menu-sidebar d-none d-lg-block">
            <div class="logo">
                <a href="/">
                    <img src="images/icon/logo.png" alt="OptiTech" />
                </a>
            </div>
            <div class="menu-sidebar__content js-scrollbar1">
                <nav class="navbar-sidebar">
                    <ul class="list-unstyled navbar__list">
                        <li class="has-sub">
                            <a href="/"><i class="fas fa-tachometer-alt"></i>Dashboard</a>
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
                            <a href="statsAI">
                                <i class="far fa-check-square"></i>AI</a>
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
                                    <div class="noti__item js-item-menu">
                                        <i class="zmdi zmdi-notifications"></i>
                                        <span class="quantity">3</span>
                                        <div class="notifi-dropdown js-dropdown">
                                            <div class="notifi__title">
                                                <p>You have 3 Notifications</p>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c1 img-cir img-40">
                                                    <i class="zmdi zmdi-email-open"></i>
                                                </div>
                                                <div class="content">
                                                    <p>You got a email notification</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c2 img-cir img-40">
                                                    <i class="zmdi zmdi-account-box"></i>
                                                </div>
                                                <div class="content">
                                                    <p>Your account has been blocked</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__item">
                                                <div class="bg-c3 img-cir img-40">
                                                    <i class="zmdi zmdi-file-text"></i>
                                                </div>
                                                <div class="content">
                                                    <p>You got a new file</p>
                                                    <span class="date">April 12, 2018 06:50</span>
                                                </div>
                                            </div>
                                            <div class="notifi__footer">
                                                <a href="#">All notifications</a>
                                            </div>
                                        </div>
                                    </div>
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
                                                <a href="/logout">
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
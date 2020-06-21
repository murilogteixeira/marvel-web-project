<!doctype html>
<html th:fragment="layout (template, menu)">
<head>
    <title>Marvel Quiz</title>
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/stylesheets/main.css" />
    <link rel="stylesheet" type="text/css" href="/stylesheets/home-style.css">
    <link rel="stylesheet" type="text/css" href="/stylesheets/Style.css">
    <link rel="stylesheet" type="text/css" href="/stylesheets/layout.css">
    <script type="text/javascript" src="/scripts/QuizScript.js"></script>
</head>

<body style="background-color:black;">
    <nav class="navbar navbar-default navbar-static-top navbar-inverse red">
        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    Marvel Super Fans
                </a>
            </div>
        </ul>
        <div class="container">
            <ul class="nav navbar-nav">
                <li class="nav-item" th:class="${activeTab == 'home'} ? active : null">
                    <a class="nav-link" href="/">
                        <span class="glyphicon glyphicon-home"></span>
                        Home
                    </a>
                </li>
                <li th:class="${activeTab == 'characters'} ? active : null">
                    <a class="nav-link" href="/characters">
                        <span class="glyphicon glyphicon-user"></span>
                        Characters
                    </a>
                </li>
                <li th:class="${activeTab == 'events'} ? active : null">
                    <a class="nav-link" href="/events">
                        <span class="glyphicon glyphicon-list-alt"></span>
                        Events
                    </a>
                </li>
                <li th:class="${activeTab == 'comics'} ? active : null">
                    <a class="nav-link" href="/comics">
                        <span class="glyphicon glyphicon-book"></span>
                        Comics
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-right" th:class="${activeTab == 'login'} ? active : null">
                    <a class="nav-link" href="/login">
                        <span class="glyphicon glyphicon-book"></span> 
                        Login
                    </a>
                </li>
            </ul>
        </div>
    </nav>

    <div th:replace="${template}"/>

    <script th:src="@{/webjars/jquery/jquery.min.js}"></script>
    <script th:src="@{/webjars/jquery-ui/jquery-ui.min.js}"></script>
    <script th:src="@{/webjars/bootstrap/js/bootstrap.min.js}"></script>
</body>
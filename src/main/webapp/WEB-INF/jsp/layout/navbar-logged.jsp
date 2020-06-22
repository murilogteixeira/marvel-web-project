<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html>
<head>
    <title>Marvel Quiz</title>
    <link rel="stylesheet" type="text/css" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/css/main.css" />
    <link rel="stylesheet" type="text/css" href="/css/home-style.css">
    <link rel="stylesheet" type="text/css" href="/css/Style.css">
    <link rel="stylesheet" type="text/css" href="/css/layout.css">
</head>

<body style="background-color:black;">
    
    <nav class="navbar navbar-default navbar-static-top navbar-inverse red">
        <ul class="nav navbar-nav">
            <div class="navbar-header">
                <a class="navbar-brand" href="/logged">
                    Marvel Super Fans
                </a>
            </div>
        </ul>
        <div class="container">
            <ul class="nav navbar-nav">
                <li class="nav-item ${activeTab == 'home' ? 'active' : null}">
                    <a class="nav-link" href="/logged">
                        <span class="glyphicon glyphicon-home"></span>
                        Home
                    </a>
                </li>
                <li class="${activeTab == 'characters' ? 'active' : null}">
                    <a class="nav-link" href="/characters?isLogged=true">
                        <span class="glyphicon glyphicon-user"></span>
                        Characters
                    </a>
                </li>
                <li class="${activeTab == 'events' ? 'active' : null}">
                    <a class="nav-link" href="/events?isLogged=true">
                        <span class="glyphicon glyphicon-list-alt"></span>
                        Events
                    </a>
                </li>
                <li class="${activeTab == 'comics' ? 'active' : null}">
                    <a class="nav-link" href="/comics?isLogged=true">
                        <span class="glyphicon glyphicon-book"></span>
                        Comics
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="navbar-right" class="${activeTab == 'login' ? 'active' : null}">
                    <a class="nav-link" href="/">
                        <span class="glyphicon glyphicon-log-out"></span> 
                        Logout
                    </a>
                </li>
            </ul>
        </div>
    </nav>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
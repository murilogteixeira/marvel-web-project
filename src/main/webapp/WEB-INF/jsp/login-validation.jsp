<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<body>
  <c:import url="layout/navbar.jsp"/>
  <br><br><br>
  <div class="container roundedCorners" style="align-items: center; background-color: brown; width: 50%;">
    <h2 style="color:  #E6E6E6; text-align: center;">Error to Login</h2>
    <h4 style="color:  #E6E6E6; text-align: center;">Please Login again...</h4>
    
    <br><br>
    <a href="/login">
        <button style="text-align: center;" class="btn btn-dark btnLoginCadastrar btn-dark">Login</button>
    </a>
    <br><br>
  </div>
  
  <div class="cardHomeBottom">
    <div class="row">
      <div class="col-sm-4">
        <h2 style="color: white;">Heroes</h2>
        <p style="color: white;">Get to know marvel heroes better.</p>
      </div>
      <div class="col-sm-4">
        <h2 style="color: white;">History</h2>
        <p style="color: white;">Learn a little more about the story of Marvel's most acclaimed heroes.</p>
      </div>
      <div class="col-sm-4">
        <h2 style="color: white;">Comics</h2>
        <p style="color: white;">Discover new things about this great universe.</p>
      </div>
    </div>
  </div>

  <hr>

  <footer>
    <br>
    <p style="color: white; text-align: center;">© Grupo Pagumu</p>
    <br>
  </footer>
</body>
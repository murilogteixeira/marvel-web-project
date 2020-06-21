<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>

<body>
  <c:import url="layout/navbar.jsp"/>
  <br><br><br>
  <div class="container roundedCorners" style="align-items: center; background-color: brown; width: 50%;">
    <form method="POST" action="/">
      <div class="divCenter">
        <h3 style="color: white;">Login</h1>
        </h3></br>
        <input type="text" name="txtLogin" class="roundedCornersText">
      </div></br>
      <div class="divCenter">
        <h3 style="color: white;">Senha</h1>
        </h3></br>
        <input type="password" name="txtSenha" class="roundedCornersText">
      </div><br><br>
      <!-- <div class="centerButtons"> -->
      <button type="submit" style="text-align: center; " class="btn btn-dark btnLoginCadastrar btn-dark">
        Logar
      </button></br></br>

      <a href="/register">
        <button type="button" style="text-align: center; color: white;" class="btn btn-link btnLoginCadastrar">
          Register
        </button>
      </a>
      <!-- </div> -->
      <br><br>
    </form>
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
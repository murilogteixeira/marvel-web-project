<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>
  <c:import url="layout/navbar.jsp" />

  <div class="container">
    <h1 style="color: white;">MARVEL Comics Super Fans</h1></br>
    <!--OPCOES E INFORMACOES SOBRE QUADRINHOS-->
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-sm-4">
          <a href="characters">
            <div class="cardHomeTitle">
              <h1 style="color: black;">Characters</h1>
            </div>
          </a>
        </div>
        <div class="col-sm-4">
          <a href="events">
            <div class="cardHomeTitle">
              <h1 style="color: black;">Events</h1>
            </div>
          </a>
        </div>
        <div class="col-sm-4">
          <a href="comics">
            <div class="cardHomeTitle">
              <h1 style="color: black;">Comics</h1>
            </div>
          </a>
        </div></br>
      </div>

      <c:if test="${username != null}">
        <div class="row justify-content-center" id="divQuiz">
          <div class="col-6 text-center">
            <a href="quiz">
              <div class="cardHomeTitle">
                <h1 style="color: black;">Quiz</h1>
              </div>
            </a>
          </div>
        </div>
      </c:if>

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
      <p style="color: white;">© Grupo Pagumu</p>
    </footer>

  </div>
  <script src="/js/index.js"></script>
</body>

</html>
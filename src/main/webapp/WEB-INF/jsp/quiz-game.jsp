<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<body>
  <c:import url="layout/navbar.jsp"/>
  <div class="container">
    <h1 style="color: white;">Quiz</h1>
    <br>
    
    <div class="container"> 
        <h3 style="color: #E6E6E6;">
            <span>${quiz.pergunta}</span>
        </h3>

        <br>

        <c:if test="${quiz.conteudoIsImage}">
            <div class="cardHomeBottom">
                <img src="${quiz.conteudo}"/>
            </div>
        </c:if>
        <c:if test="${!quiz.conteudoIsImage}">
          <div>
              <h3 style="color: #f2ea46;">
                  <span>${quiz.conteudo}</span>
              </h4>
          </div>
        </c:if>

        <br>

      <div class="row">
        <c:forEach var="resposta" items="${quiz.respostas}">
          <div class="col-sm-6 text-center" name="resposta" style="cursor:pointer;" onclick="verificarResposta('${resposta}', '${quiz.respostaCerta}')">
            <div class="cardResposta" id="${resposta}">
                <h4 style="text-align: center;">${resposta}</h4>
            </div>
          </div>
        </c:forEach>
      </div>
      
    </div>
    <br><br><br>
    <div class="container">
      <a href="">
        <h3 style="color: #f2ea46; text-align: right;">Next <span class="glyphicon glyphicon-menu-right"></span></h3>
      </a>
    </div>
      
      <div class="cardHomeBottom">
        <div class="row">
          <div class="col-sm-4">
            <h2 style="color: white;">Heroes</h2>
            <p style="color: white;">Get to know marvel heroes better.</p>
          </div>
          <div class="col-sm-4">
            <h2 style="color: white;" >History</h2>
            <p style="color: white;">Learn a little more about the story of Marvel's most acclaimed heroes.</p>
        </div>
          <div class="col-sm-4">
            <h2 style="color: white;">Comics</h2>
            <p style="color: white;">Discover new things about this great universe.</p>
          </div>
        </div>
    </div>

    <hr>

  </div>

  <footer>
    <br>
    <p style="color: white; text-align: center;">© Grupo Pagumu</p>
    <br>
  </footer>

  <script type="text/javascript" src="/js/quizGame.js"></script>
</body>

</html>
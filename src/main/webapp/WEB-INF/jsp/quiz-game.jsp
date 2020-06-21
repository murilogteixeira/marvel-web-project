<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" th:replace="~{fragments/layout :: layout (~{::body},'index')}">

<body>
  <div class="container">
    <h1 style="color: white;">Quiz</h1>
    </br>
    
    <div class="container"> 
        <h3 style="color: #E6E6E6;">
            <span th:text="${quiz.pergunta}"/>
        </h3>

        <br>

        <div th:if="${quiz.conteudoIsImage} == true">
            <div class="cardHomeBottom">
                <img th:src="${quiz.conteudo}"/>
            </div>
        </div>
        <div th:if="${quiz.conteudoIsImage} == false">
            <h3 style="color: #f2ea46;">
                <span th:text="${quiz.conteudo}"/>
            </h4>
        </div>
        
        <br>

      <div class="row">
        <div class="col-sm-6 text-center">
            <div class="cardResposta">
                <h4 style="text-align: center;" th:text="${quiz.respostas[0]}"/>
            </div>
        </div>
        <div class="col-sm-6 text-center">
            <div class="cardResposta">
                <h4 style="text-align: center;" th:text="${quiz.respostas[1]}"/>
            </div>
        </div>
      </div>
      <br>
      <div class="row">
        <div class="col-sm-6 text-center">
            <div class="cardResposta">
                <h4 style="text-align: center;" th:text="${quiz.respostas[2]}"/>
            </div>
        </div>
        <div class="col-sm-6 text-center">
            <div class="cardResposta">
                <h4 style="text-align: center;" th:text="${quiz.respostas[3]}"/>
            </div>
        </div>
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

  <footer>
    <br>
    <p style="color: white; text-align: center;">© Grupo Pagumu</p>
    <br>
  </footer>

  </div>
</body>

</html>
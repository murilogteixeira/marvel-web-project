<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<body>
  <c:import url="layout/navbar.jsp"/>
  <div class="container">
    <h1 style="color: white;">Quiz Result</h1>
    
    </br>

   <input type="hidden" id="scoreUser" value="${user.score}">

    <h3 style="color: #f2ea46;"> Score: </h3>
    <h3 style="color: #E6E6E6;" id="score">50</h3>

    <br>

    <h3 style="color: #E6E6E6;"> Right Answers: </h3>
    <h3 style="color: green;" id="rightAnswers">50%</h3>

    <br>

    <h3 style="color: #E6E6E6;"> Wrong Answers: </h3>
    <h3 style="color: red" id="wrongAnswers">50%</h3>
    
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

  <script type="module" src="/js/quizResult.js"></script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<body>
    <c:if test="${isLogged == true}">
      <c:import url="layout/navbar-logged.jsp"/>
    </c:if> 
    <c:if test="${isLogged == false}">
      <c:import url="layout/navbar.jsp"/>
    </c:if>

  <div class="container">
    <div class="card">
      <div class="card-body" style="color: #E6E6E6;">
        <h1 style="color: white;">Character Presentation</h1>
        <h4 style="color: #E6E6E6;">The best heroes from Marvel Universe.</h4>
        <br>
        <div class="cardHome">
          <c:forEach items = "${records}" var="record">
            <div class="row">
              <br>
              <h4 style="color: #E6E6E6; text-align: center;"> Nome: </h4>
              <h5 style="color: #E6E6E6; text-align: center;">${record.name}</h5>
              <c:choose> 
                <c:when test="${record.name == null} || ${record.name == ''} || ${record.name.isEmpty() == true}">
                  <h5 style="color: #E6E6E6; text-align: center;">UNKNOWN</h5>
                </c:when>
              </c:choose> 

              <c:if test="${record.description != ''}">
                <h4 id="description" style="color: #E6E6E6; text-align: center;">Description: </h4>
                <div class="divDescription">
                  <h5 style="color: #E6E6E6; text-align: center;">${record.description}</h5>
                </div>
              </c:if>
              
              <div class="divCenter">
                <img style="text-align: center;" src="${record.thumbnail.path}/portrait_incredible.${record.thumbnail.extension}"/>
              </div>
              <br>
            </div>
            <br>
          </c:forEach>
        </div>
      </div>
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
</body>

</html>

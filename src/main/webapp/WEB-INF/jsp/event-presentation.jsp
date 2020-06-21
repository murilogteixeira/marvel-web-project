<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<body>
  <c:import url="layout/navbar.jsp"/>
  <div class="container">
    <div class="card">
      <div class="card-body">
        <h1 style="color: white;">Historical Events Presentation</h1>
        <h4 style="color: #E6E6E6; text-align: center;">The best historical events from Marvel Universe.</h4>
        <br>
        <div class="cardHome">
          <c:forEach items = "${records}" var="record">
            <div class="card">
              <div class="container-fluid">
                <br>
                <div class="row">
                  <div class="col-sm-4">
                    <img src="${record.thumbnail.path}/portrait_incredible.${record.thumbnail.extension}" />
                  </div>
                  <div class="col-sm-8">
                    <h4 style="color: #E6E6E6;"> Title: </h4>
                    <h5 style="color: #E6E6E6; text-align: center;">
                      ${record.title}
                    </h5>
                    <br>
                    <h4 style="color: #E6E6E6;"> Start date: </h4>
                    <h5 style="color: #E6E6E6; text-align: center;">
                      ${record.start}
                    </h5>
                    <br>
                    <h4 style="color: #E6E6E6;"> End date: </h4>
                    <h5 style="color: #E6E6E6; text-align: center;">
                      ${record.end}
                    </h5>
                    <br>
                    <h4 style="color: #E6E6E6;"> Description: </h4>
                    <h5 style="color: #E6E6E6; text-align: center;">
                      ${record.description}
                    </h5>
                  </div>
                  <br>
                </div>
                <br>
              </div>
            </div>
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
      <br>
      <p style="color: white; text-align: center;">© Grupo Pagumu</p>
      <br>
    </footer>

  </div>
</body>
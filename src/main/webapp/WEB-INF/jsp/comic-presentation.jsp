<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<body>
  <c:import url="navbar.jsp"/>

  <div class="container">
    <div class="card">
      <div class="card-body">
        <h1 style="color: white;">Comic Presentation</h1>
        <h4 style="color: #E6E6E6; text-align: center;">The best comics from Marvel Universe.</h4>
        <br>
        <div class="row">
          <div class="cardHome">
            <c:forEach var="record" items="${records}">
              <div>
                <br>
                <h4 style="color: #E6E6E6;"> Title: </h4>
                <h5 style="color: #E6E6E6; text-align: center;">${record.title}</h5>
                <br>
                <h4 style="color: #E6E6E6;"> Author(s): </h4>
                <c:if test="${not empty record.creators.items}">
                  <c:forEach var="item" items="${record.creators.items}">
                    <div>
                      <h5 style="color: #E6E6E6; text-align: center;">${item.name}</h5>
                    </div>
                  </c:forEach>
                </c:if>
                <c:if test="${empty record.creators.items}">
                  <div>
                    <h5 style="color: #E6E6E6; text-align: center;">UNKNOWN</h5>
                  </div>  
                </c:if>
                <div class="divCenter">
                  <img style="text-align: center;" src="${record.thumbnail.path}/portrait_incredible.${record.thumbnail.extension}"/>
                </div>
                <br>
              </div>
            </c:forEach>
          </div>
        </div>
        <br>
        <br>
        <br>
      </div>
      <br>
      </ul>
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
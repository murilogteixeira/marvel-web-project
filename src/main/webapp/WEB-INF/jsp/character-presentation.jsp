
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>

<!-- <html xmlns:th="http://www.thymeleaf.org"
th:replace="~{fragments/layout :: layout (~{::body},'character-presentation')}"> -->

<body>
  <div class="container">
    <div class="card">
      <div class="card-body" style="color: #E6E6E6;">
        <h1 style="color: white;">Character Presentation</h1>
        <h4 style="color: #E6E6E6;">The best heroes from Marvel Universe.</h4>
        <br>
        <div class="cardHome">
          <ul th:each="record : ${records}">
            <br>
            <div class="row">
              <h4 style="color: #E6E6E6; text-align: center;"> Nome: </h4>
              <h5 style="color: #E6E6E6; text-align: center;" th:text="${record.name}" />

              <h4 id="description" style="color: #E6E6E6; text-align: center;"></h4> 

              <script>
                var description;
                description =  "Description:";
                document.getElementById("description").innerHTML = record.description;
              </script>

              <div th:if="${record.name} == null or ${record.name} == '' or ${record.name.isEmpty()} == true">
                <h5 style="color: #E6E6E6; text-align: center;" th:text="UNKNOWN" />
              </div>
              <div class="divDescription">
                <div th:if="${record.name} != null or ${record.name} != '' or ${record.name.isEmpty()} == false">
                  <h5 style="color: #E6E6E6; text-align: center;" th:text="${record.description}" />
                </div>
              </div>
              <img th:src="${record.thumbnail.path} + '/portrait_incredible.' + ${record.thumbnail.extension}" />
            </div>
            <br>
          </ul>
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
<!DOCTYPE html>

<html xmlns:th="http://www.thymeleaf.org" th:replace="~{fragments/layout :: layout (~{::body},'comic-presentation')}">

<body>
  <div class="container">
    <div class="card">
      <div class="card-body">
        <h1 style="color: white;">Comic Presentation</h1>
        <h4 style="color: #E6E6E6; text-align: center;">The best comics from Marvel Universe.</h4>
        <br>
        <div class="row">
          <div class="cardHome">
            <div th:each="record : ${records}">
              <br>
              <h4 style="color: #E6E6E6;"> Title: </h4>
              <h5 style="color: #E6E6E6; text-align: center;" th:text="${record.title}" />
              <br>
              <h4 style="color: #E6E6E6;"> Author(s): </h4>
              <div th:if="${record.creators.items} != null and ${record.creators.items.isEmpty()} == false">
                <div th:each="item : ${record.creators.items}">
                  <h5 style="color: #E6E6E6; text-align: center;" th:text="${item.name}" />
                </div>
              </div>
              <div th:if="${record.creators.items} == null or ${record.creators.items.isEmpty()} == true">
                <h5 style="color: #E6E6E6; text-align: center;" th:text="UNKNOWN" />
              </div>
              <div class="divCenter">
                <img style="text-align: center;"
                  th:src="${record.thumbnail.path} + '/portrait_incredible.' + ${record.thumbnail.extension}" />
              </div>
              <br>
            </div>
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
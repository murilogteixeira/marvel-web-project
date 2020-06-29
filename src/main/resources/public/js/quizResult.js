import {request} from './http-request.js';

$(document).ready(function () {

    var scoreUser = parseInt(document.getElementById('scoreUser'));
    var score = document.getElementById('score');
    var rightAnswers = document.getElementById('rightAnswers');
    var wrongAnswers = document.getElementById('wrongAnswers');

    var perguntas = sessionStorage.getItem('perguntas');
    var acertos = sessionStorage.getItem('acertos');
    var erros = perguntas - acertos;
    var acertosPercentual = acertos / perguntas * 100;
    var errosPercentual = erros / perguntas * 100;

    rightAnswers.innerHTML = acertosPercentual.toPrecision(2) + '%';
    wrongAnswers.innerHTML = errosPercentual.toPrecision(2) + '%';

    scoreUser = isNaN(scoreUser) ? acertos : scoreUser += acertos;

    score.innerHTML = scoreUser;

    getUser((user) => {
        if(!isNaN(acertosPercentual) && !isNaN(errosPercentual)) {
            user.score += parseInt(acertos);
            var userAcertos = user.rightAnswerPercent
            var userErros = user.wrongAnswerPercent
            user.rightAnswerPercent = userAcertos == 0 ? userAcertos : (userAcertos + acertosPercentual) / 2;
            user.wrongAnswerPercent = userErros == 0 ? userErros : (userErros + errosPercentual) / 2;

            sessionStorage.setItem('perguntas', 0);
            sessionStorage.setItem('acertos', 0);
            saveUser(user, (data) => {
                console.log('user: ' + user);
                console.log('api response: ' + data);
            })
        }
    });
});

function getUser(callback) {
    const method = 'GET';
    const uri = 'https://marvel-web-project.herokuapp.com';
    const path = '/api/user';
    const url = uri + path;
    const obj = {
        username: $('#username').val()
    }
    const apiCallback = (data) => {
        callback(data)
    };

    request(method, url, obj, apiCallback);
}

function saveUser(user, callback) {
    const method = 'PUT';
    const uri = 'https://marvel-web-project.herokuapp.com';
    const path = '/api/user/' + user.id;
    const url = uri + path;
    const apiCallback = (data) => {
        callback(data)
    };

    request(method, url, user, apiCallback);
}
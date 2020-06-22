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

    rightAnswers.innerHTML = acertosPercentual + '%';
    wrongAnswers.innerHTML = errosPercentual + '%';

    scoreUser = isNaN(scoreUser) ? acertos : scoreUser += acertos;

    score.innerHTML = scoreUser;

    getUser((user) => {
        console.log(user);
    });
});

function getUser(retorno) {
    const method = 'GET';
    const uri = 'https://marvel-web-project.herokuapp.com';
    const path = '/api/user';
    const url = uri + path;
    const obj = {
        username: sessionStorage.getItem('username')
    }
    const params = new URLSearchParams(obj).toString();
    const callback = (data) => {
        retorno(data)
    };

    request(method, url, params, callback);
}

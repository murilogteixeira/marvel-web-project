$(window).on('load', () => {
    sessionStorage.setItem('sessaoQuizAtiva', true);
});

function verificarResposta(resposta, respostaCerta) {
    var respostaCorreta = false
    if (resposta == respostaCerta) {
        var fundo = document.getElementById(resposta)
        fundo.style.backgroundColor = "green";
        respostaCorreta = true;
    } else {
        var fundoResposta = document.getElementById(resposta)
        var fundoRespostaCerta = document.getElementById(respostaCerta)
        fundoResposta.style.backgroundColor = "red";
        fundoRespostaCerta.style.backgroundColor = "green";
    }

    if (respostaCorreta) {
        var acertos = sessionStorage.getItem('acertos');
        if(acertos == null) {
            sessionStorage.setItem('acertos', 1);
        } else {
            sessionStorage.setItem('acertos', ++acertos);
        }
    }
    
    var perguntas = sessionStorage.getItem('perguntas');
    if(perguntas == null) {
        sessionStorage.setItem('perguntas', 1);
    } else {
        sessionStorage.setItem('perguntas', ++perguntas);
    }

    var respostas = document.getElementsByName('resposta');
    respostas.forEach((div) => {
        div.onclick = function() {
            return false;
        }
    });

    return respostaCorreta;
}
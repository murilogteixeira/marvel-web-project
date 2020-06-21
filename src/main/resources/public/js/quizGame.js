function verificarResposta(resposta, respostaCerta) {
    var retorno = false
    if (resposta == respostaCerta) {
        var fundo = document.getElementById(resposta)
        fundo.style.backgroundColor = "green";
        retorno = true;
    } else {
        var fundoResposta = document.getElementById(resposta)
        var fundoRespostaCerta = document.getElementById(respostaCerta)
        fundoResposta.style.backgroundColor = "red";
        fundoRespostaCerta.style.backgroundColor = "green";
    }

    var respostas = document.getElementsByName('resposta');
    respostas.forEach(desabilitarDivs);

    return retorno;
}

function desabilitarDivs(item) {
    item.onclick = function() {
        return false;
    }
}
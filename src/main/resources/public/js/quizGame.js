function verficarResposta(resposta, respostaCerta, idFundo){

    if (resposta == respostaCerta){
        var fundo = document.getElementById(idFundo)
        fundo.style.backgroundColor = "green";
        return true;
    } else {
        var fundo = document.getElementById(idFundo)
        fundo.style.backgroundColor = "red";
        return false;
    }
}

function showAlert(nome) {
    alert(nome);
}
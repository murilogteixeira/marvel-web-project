
function request(method, url, data, callback) {
    var ajax = new XMLHttpRequest();

    if (method === 'POST' || method === 'post') {
        ajax.open(method, url, true);
        // ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        ajax.setRequestHeader("Content-Type", 'application/json; charset=UTF-8');
        ajax.send(JSON.stringify(data));
    } else if (method === 'GET' || method === 'get') {
        const params = new URLSearchParams(data).toString();
        ajax.open(method, url + '/?' + params, true);
        ajax.send();
    }

    // Cria um evento para receber o retorno.
    ajax.onreadystatechange = () => { // Caso o state seja 4 e o http.status for 200, é porque a requisiçõe deu certo.
        if (ajax.readyState == 4 && ajax.status == 200) { // Retorno do Ajax
            var ajaxReturn = ajax.responseText;
            var data = JSON.parse(ajaxReturn);
            callback(data);
        }
    }
}

export {request};
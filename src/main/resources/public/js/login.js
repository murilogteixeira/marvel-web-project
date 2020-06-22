import {request} from './http-request.js';

$('#submitLogin').click(function (e) { // e.preventDefault();
    var form = $('#formLogin')

    var data = formToJson(form);
    request('post', '/api/login', data, (value) => {
        if (value != null) {
            sessionStorage.setItem('username', value.username);
            console.log(value);
        }
    });
});

function formToJson(form) {
    var json = {};
    var formArray = form.serializeArray();
    formArray.forEach(element => {
        if(json[element.name]) {
            if(!json[element.name].push) {
                json[element.name] = [json[element.name]];
            }
            json[element.name].push(element.value || '');
        } else {
            json[element.name] = element.value || '';
        }
    });
    return json;
}
import {request} from './http-request.js';

$('#submitLogin').on('click', function (e) { // e.preventDefault();
    var form = $('#formLogin')

    var json = formToJson(form);

    console.log(json);
    if(json.username && json.password) {
        e.preventDefault();
        request('post', '/api/login', json, (value) => {
            if (value != null) {
                window.location.href = '/';
            }
        });
    }
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
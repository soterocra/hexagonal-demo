$(document).ready(
    function() {
        getData();
    }
);

function getData() {
    console.log("Enviando...")

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8083/users",
        "method": "GET",
        "headers": {
            "content-type": "application/json",
        }
    }

    $.ajax(settings).done(function (response) {
       console.log("Request Concluído.")
       console.log(response);

       for (var i = 0; i < response.length; i++) {
            var rowData = `<tr>` +
                             `<td class="col-dg-1">${response[i]['userId']}</td>` +
                             `<td class="col-dg-1">${response[i]['name']}</td>` +
                             `<td class="col-dg-2">${response[i]['cpf']}</td>` +
                             `<td class="col-dg-2">${response[i]['phone']}</td>` +
                             `<td class="col-dg-2">${response[i]['email']}</td>` +
                             `<td class="col-dg-2">${response[i]['role']}</td>` +
                             `<td class="col-dg-1 buttontable"><button type="button" class="btntable btn-excluir" onclick="deleteRow(${response[i]['userId']})">Excluir</button></td>` +
                          `</tr>`
            console.log(rowData)
            $("#container-user-data").append(rowData);
       }
      });
}

function deleteRow(userId) {
    console.log("Enviando...")

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8083/users/" + userId,
        "method": "DELETE",
        "headers": {
            "content-type": "application/json",
        }
    }

    $.ajax(settings).done(function (response) {
       console.log("Request Concluído.")
       console.log(response);

       alert("Registro deletado com sucesso!")
       document.location.reload(true);

      });
}
$(document).ready(
    function() {
        document.getElementById("submit").addEventListener("click", function(event){
          event.preventDefault()
          submitForm()
        });
    }
);

function submitForm() {
    console.log("Enviando...")

    var userId = document.getElementById("userId").value
    var cpf = document.getElementById("cpf").value
    var name = document.getElementById("name").value
    var phone = document.getElementById("phone").value
    var email = document.getElementById("email").value
    var role = document.getElementById("role").value

    var settings = {
        "async": true,
        "crossDomain": true,
        "url": "http://localhost:8083/users",
        "method": "POST",
        "headers": {
            "content-type": "application/json",
        },
        "datatype": "json",
        "data": JSON.stringify({
            "userId": userId,
            "name": name,
            "cpf": cpf,
            "phone": phone,
            "email": email,
            "role": role
        })
    }

    $.ajax(settings).done(function (response) {
        console.log("Request Concluído.")
        console.log(response);
        alert("Usuário Inserido com Sucesso!!")
        $(':input','#cadastro')
          .not(':button, :submit, :reset, :hidden')
          .val('')
          .removeAttr('checked')
          .removeAttr('selected');
      });
}
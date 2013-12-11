function checkspace(checkstr) {
    var str = '';
    for (i = 0; i < checkstr.length; i++) {
        str = str + ' ';
    }
    return (str === checkstr);
}
function check()
{
    var u = document.getElementById("email").value;
    if (checkspace(u)) {
        document.LoginForm.username.focus();
        alert("Please enter a username！");
        return false;
    }
    var p = document.getElementById("password").value;
    if (checkspace(p)) {
        document.LoginForm.password.focus();
        alert("Please enter the password！");
        return false;
    }
    authenticate(u, p);
}

//authenticate function to make ajax call
function authenticate(u, p) {
    $.get("http://localhost:8080/GoodIdea/rest/user/check/u=" + u +"/p="+ p, function(result) {
        alert(result);
    });
//        $.ajax
//        ({
//            type: "POST",
//            //the url where you want to sent the userName and password to
//            url: "http://localhost:8080/GoodIdea/rest/user/" + u,
//            async: false,
//            //json object to sent to the authentication url
//            data: p,
//            success: function(result) {
//                alert(result);
//            }
//        });
}

window.onload = function() {
    document.getElementById("signin_submit").onclick = check;
};

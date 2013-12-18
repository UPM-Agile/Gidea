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
        document.LoginForm.email.focus();
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
    console.log("Logging in.");
    $.ajax({
        type: "GET",
        //the url where you want to sent the userName and password to
        url: "http://localhost:8080/GoodIdea/rest/user/check/u=" + u + "/p=" + p,
        async: false,
        //json object to sent to the authentication url
        //data: p,
        headers: {'Content-type': 'application/json'},
        //complete: ,
        success: function(result) {
            if (result) {
                console.log("Creating cookie:");
                $.cookie.raw = true;
                $.cookie("username", u);
                window.location.href = "/GoodIdea/MyIdeasList.html"
            }
            else
            {
                alert("Invalid user or password");
            }
            console.log(result);
        }
    });
}

function getLoggedUser() {
    $.cookie.raw = true;
    var user = $.cookie("username");
    if (user)
    {
        console.log("Reading cookie:");
        return user;
    }
    console.log("Cookie not found");
    return false;
}
;
function logout()
{
    console.log("Removing cookie:");
    if ($.removeCookie("username"))
    {
        //alert("You have logged out succesfully.");
        console.log("Cookie deleted");
        $("#logout").hide();
    } else
    {
        console.log("Cookie not deleted");
    }

}
;
function fillLoginData() {
    console.log("starting logging");
    $("#login_user").hide();
    $("#logout").hide();
    var user = getLoggedUser();
    if (user)
    {
        $("#login_user").append(user);
        $("#logout").bind("click", function() {
            console.log("logout");
            logout();
        });
        $("#login_user").show();
        $("#logout").show();
    }
}
;
window.onload = function() {
    document.getElementById("signin_submit").onclick = check;
    fillLoginData();
};

function checkspace(checkstr) {
  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str === checkstr);
}
function check()
{
var u=document.getElementById("username").value;
  if(checkspace(u)) {
	document.LoginForm.username.focus();
    alert("Please enter a username！");
	return false;
  }
var p=document.getElementById("password").value;
  if(checkspace(p)) {
	document.LoginForm.password.focus();
    alert("Please enter the password！");
	return false;
  }
    
	document.LoginForm.submit();
  }
  
  window.onload = function(){
     document.getElementById("signin_submit").onclick = check;
}

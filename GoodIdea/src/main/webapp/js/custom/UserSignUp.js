function checkspace(checkstr) {
  var str = '';
  for(i = 0; i < checkstr.length; i++) {
    str = str + ' ';
  }
  return (str === checkstr);
}
function CheckSignUp(){

var f=document.getElementById("firstname").value;
  if(checkspace(f)) {
	document.SignUpForm.firstname.focus();
    alert("Please enter a firstname！");
	return false;
  }
  	document.SignUpForm.submit();
}
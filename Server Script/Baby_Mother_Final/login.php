<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
//Getting values
$usercell = $_POST['cell'];
$password = $_POST['password'];

//$password = md5($password1); //encrypted password for security

//importing init.php script
require_once('init.php');

//creating sql $sql_query

$sql = "SELECT * FROM mother WHERE M_Cell = '$usercell' AND M_Pass= '$password'"; //check the encrypted password with user login password

//executing $sql_query
$result = mysqli_query($con,$sql);
$count = mysqli_num_rows($result);

//fetching result
$check = mysqli_fetch_array($result);
//echo $sql."|";
//echo $check;

if(isset($check)){

//displaying Successful
echo "success";

}
else {
  //displaying failure
  echo "failure";
  //echo mysqli_error($con);
  mysqli_close($con);
}
}

?>

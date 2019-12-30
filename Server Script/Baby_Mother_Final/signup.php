<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
//Getting values
$M_Name = $_POST['M_Name'];
$M_Email = $_POST['M_Email'];
$M_Cell = $_POST['M_Cell'];
$M_User = $_POST['M_User'];
$M_Blood = $_POST['M_Blood'];
$M_Week = $_POST['M_Week'];
$M_Pass = $_POST['M_Pass'];
$M_Date = $_POST['M_Date'];

//$password = md5($password1); //encrypted password for security

//importing init.php script
require_once('init.php');

//creating sql $sql_query
$sql = "insert into mother(M_Name,M_Email,M_Cell,M_User,M_Blood,M_Week,M_Pass,M_Date) values('$M_Name','$M_Email','$M_Cell','$M_User','$M_Blood','$M_Week','$M_Pass','$M_Date')";

$result = mysqli_query($con,"SELECT M_Cell FROM mother where M_Cell = '$M_Cell'");

$num_rows = mysqli_num_rows($result);
if($num_rows>0)
{
  echo "exists";
}
else {
  if(mysqli_query($con,$sql))
  {
    echo "success";
  }
  else {
    echo mysqli_error($con);
  }
  mysqli_close($con);
}

}
  ?>

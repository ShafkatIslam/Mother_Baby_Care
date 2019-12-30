<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
//Getting values
$B_Name = $_POST['B_Name'];
$BM_Email = $_POST['BM_Email'];
$B_User = $_POST['B_User'];
$BF_Cell = $_POST['BF_Cell'];
$B_Pass = $_POST['B_Pass'];
$B_BDay = $_POST['B_BDay'];
$B_Gender = $_POST['B_Gender'];

//$password = md5($password1); //encrypted password for security

//importing init.php script
require_once('init.php');

//creating sql $sql_query
$sql = "insert into baby(B_Name,BM_Email,B_User,BF_Cell,B_Pass,B_Day,B_Gender) values('$B_Name','$BM_Email','$B_User','$BF_Cell','$B_Pass','$B_BDay','$B_Gender')";

$result = mysqli_query($con,"SELECT BF_Cell FROM baby where BF_Cell = '$BF_Cell'");

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

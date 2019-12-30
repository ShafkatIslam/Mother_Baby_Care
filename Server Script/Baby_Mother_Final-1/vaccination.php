<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
//Getting values

$cell = $_POST['cell'];
$pdate = $_POST['pdate'];
$ndate = $_POST['ndate'];
$number = $_POST['number'];
$numbers = $_POST['numbers'];

//$password = md5($password1); //encrypted password for security

//importing init.php script
require_once('init.php');

//creating sql $sql_query
$sql = "insert into vaccine(cell,pdate,ndate,number,numbers) values('$cell','$pdate','$ndate','$number','$numbers')";

$result = mysqli_query($con,"SELECT number FROM vaccine where number = '$number' and cell='$cell'");

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

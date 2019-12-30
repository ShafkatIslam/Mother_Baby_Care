<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values



 $cell = $_POST['cell'];
$pdate = $_POST['pdate'];
$ndate = $_POST['ndate'];
$number = $_POST['number'];
$numbers = $_POST['numbers'];


 //importing dbConnect.php script
 require_once('init.php');


   $sql="UPDATE vaccine SET ndate ='$ndate' WHERE number = '$number' AND cell = '$cell' ";




if(mysqli_query($con,$sql))

{

     echo "success";

}

 else
 {
     echo "failure";
 }

 mysqli_close($con);


}


?>

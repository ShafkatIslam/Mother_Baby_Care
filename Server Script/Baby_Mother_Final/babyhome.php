<?php

 if($_SERVER['REQUEST_METHOD']=='GET'){

 $usercell= $_GET['cell'];
 //$get_text= $_GET['text'];

 require_once('init.php');

 $sql = "SELECT * FROM baby WHERE BF_Cell='".$usercell."' ";

/*
if(strlen($get_text)>0)
{
  $sql = "SELECT * FROM contacts WHERE name LIKE '%$get_text%' AND user_cell='".$usercell."' ORDER BY name ASC";
}
*/
 $r = mysqli_query($con,$sql);

// $res = mysqli_fetch_array($r);

 $result = array();


while($res = mysqli_fetch_array($r))
        {

		//Pushing msg and date in the blank array created
		array_push($result,array(
		                "id"=>$res['id'],
		            	"B_Name"=>$res['B_Name'],
		            	"BM_Email"=>$res['BM_Email'],
                  "B_User"=>$res['B_User'],
                  "BF_Cell"=>$res['BF_Cell'],
                  "B_Pass"=>$res['B_Pass'],
                  "B_BDay"=>$res['B_Day'],
                  "B_Gender"=>$res['B_Gender']

		));
	}

 echo json_encode(array("result"=>$result));

 mysqli_close($con);

 }
 ?>

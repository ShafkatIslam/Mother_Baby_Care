<?php

 if($_SERVER['REQUEST_METHOD']=='GET'){

 $usercell= $_GET['cell'];
 //$get_text= $_GET['text'];

 require_once('init.php');

 $sql = "SELECT * FROM mother WHERE M_Cell='".$usercell."' ";

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
		            	"M_Name"=>$res['M_Name'],
		            	"M_Email"=>$res['M_Email'],
                  "M_Cell"=>$res['M_Cell'],
                  "M_User"=>$res['M_User'],
                  "M_Blood"=>$res['M_Blood'],
                  "M_Week"=>$res['M_Week'],
                  "M_Pass"=>$res['M_Pass'],
                  "M_Date"=>$res['M_Date']

		));
	}

 echo json_encode(array("result"=>$result));

 mysqli_close($con);

 }
 ?>

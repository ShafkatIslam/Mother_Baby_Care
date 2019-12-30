<?php

 if($_SERVER['REQUEST_METHOD']=='GET'){

 $usercell= $_GET['cell'];
 //$get_text= $_GET['text'];

 require_once('init.php');

 $sql = "SELECT * FROM vaccine_baby  WHERE cell = '$usercell' ORDER BY ndate ASC";

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
		            	"cell"=>$res['cell'],
		            	"pdate"=>$res['pdate'],
                  "ndate"=>$res['ndate'],
                  "number"=>$res['number'],
                  "numbers"=>$res['numbers'],

		));
	}

 echo json_encode(array("result"=>$result));

 mysqli_close($con);

 }
 ?>

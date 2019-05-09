<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 16:35
 */
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 14:54
 */



$id = isset($_POST['id']) ? $_POST['id'] : '';



if(!empty($id)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "DELETE FROM cost WHERE id=:id";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":id", $id);
    $result = $stmt->execute();
    if($result){
        $returnData = array('status' => true, 'message' => 'Cost Deleted Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Delete Cost...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}



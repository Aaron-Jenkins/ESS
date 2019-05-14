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
    $sql = "DELETE FROM users WHERE id=:id";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":firstName", $firstName);
    $stmt->bindValue(":lastNAme", $lastName);
    $stmt->bindValue(":password", $password);
    $stmt->bindValue(":companyId", $companyId);
    $result = $stmt->execute();
    if($result){
        $returnData = array('status' => true, 'message' => 'User Deleted Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Delete User...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}



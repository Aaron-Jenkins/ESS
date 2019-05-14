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



$id         = isset($_POST['id'])           ? $_POST['id']          : '';
$firstName  = isset($_POST['firstName'])    ? $_POST['firstName']   : '';
$lastName   = isset($_POST['lastName'])     ? $_POST['lastName']    : '';
$password   = isset($_POST['password'])     ? $_POST['password']    : '';
$companyId  = isset($_POST['companyId'])    ? $_POST['companyId']   : '';


if(!empty($id) && !empty($firstName) && !empty($lastName) && !empty($password) ){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "UPDATE users SET firstName=:firstName, lastName=:lastName, password=:password, companyId=:companyId) WHERE id=:id";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":firstName", $firstName);
    $stmt->bindValue(":lastNAme", $lastName);
    $stmt->bindValue(":password", $password);
    $stmt->bindValue(":companyId", $companyId);
    $result = $stmt->execute();
    if($result){
        $returnData = array('status' => true, 'message' => 'User Updated Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Update User...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}





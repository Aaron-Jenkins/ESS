<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 15:34
 */

$firstName  = isset($_POST['firstName'])    ? $_POST['firstName']   : '';
$lastName   = isset($_POST['lastName'])     ? $_POST['lastName']    : '';
$password   = isset($_POST['password'])     ? $_POST['password']    : '';
$companyId  = isset($_POST['companyId'])    ? $_POST['companyId']   : '';


if(!empty($firstName) && !empty($lastName) && !empty($password)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "INSERT INTO service (firstName, lastName, password, companyId) VALUES (:firstName, :lastName, :password, :companyId)";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":firstName", $firstName);
    $stmt->bindValue(":lastNAme", $lastName);
    $stmt->bindValue(":password", $password);
    $stmt->bindValue(":companyId", $companyId);
    $result = $stmt->execute();
    if($result){
        $returnData = array('status' => true, 'message' => 'User Added Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add User...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}



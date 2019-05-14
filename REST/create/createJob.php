<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 14:54
 */



$serviceId  = isset($_POST['serviceId'])    ? $_POST['serviceId']   : '';
$startDate  = isset($_POST['startDate'])    ? $_POST['startDate']   : '';
$endDate    = isset($_POST['endDate'])      ? $_POST['endDate']     : '';


if(!empty($serviceId) && !empty($startDate) && !empty($endDate)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "INSERT INTO company (companyName ) VALUES (:companyName)";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":companyName", $companyName);
    $result = $stmt->execute();
    if($result){
        $returnData = array('status' => true, 'message' => 'Company Added Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add Company...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}





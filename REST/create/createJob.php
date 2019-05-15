<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 14:54
 */



$userId     = isset($_GET['userId'])       ? $_GET['userId']      : '';
$serviceId  = isset($_GET['serviceId'])    ? $_GET['serviceId']   : '';
$startDate  = isset($_GET['startDate'])    ? $_GET['startDate']   : '';
$endDate    = isset($_GET['endDate'])      ? $_GET['endDate']     : '';
$empId      = isset($_GET['empId'])        ? $_GET['empId']       : '';


echo $serviceId . " - " . $userId . " - " . $startDate . " - " .$endDate . " - " . $empId;

if(!empty($userId) && !empty($serviceId) && !empty($startDate) && !empty($endDate)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "INSERT INTO jobs (userId, serviceId, startDate, endDate, empId ) VALUES (:userId, :serviceId, :startDate, :enddate, :empId)";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":id", NULL);
    $stmt->bindValue(":userId", $userId);
    $stmt->bindValue(":serviceId", $serviceId);
    $stmt->bindValue(":startDate", $startDate);
    $stmt->bindValue(":endDate", $endDate);
    $stmt->bindValue(":empId", $empId);
    $result = $stmt->execute();

    if($result){
        $returnData = array('status' => true, 'message' => 'Company Added Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add Company...');
        echo $result;
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}else{
    echo "Error!";
}





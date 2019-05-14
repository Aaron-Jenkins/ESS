<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 14:54
 */



$companyName  = isset($_GET['companyName'])    ? $_GET['companyName']   : '';


if(!empty($companyName)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "INSERT INTO company (companyName) VALUES (:companyName)";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":companyName", $companyName);
    $result = $stmt->execute();
    if($result){
        $results = array('company' => $result);
        $returnData = array('status' => true, 'message' => 'Company Added Successfully...');
        $returnData += $results;
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add Company...');
    }
    header('Content-Type: text/json');
    echo json_encode($returnData);
}





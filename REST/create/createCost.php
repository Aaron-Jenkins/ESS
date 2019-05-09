<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 15:34
 */

$cost       = isset($_GET['cost'])    ? $_GET['cost']   : '';

if(!empty($cost)){
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "INSERT INTO cost (cost) VALUES (:cost)";
    $stmt = $conn->prepare($sql);
    $stmt->bindValue(":cost", $cost);
    $result = $stmt->execute();

    if($result){
        $results = array('cost' => $result);
        $returnData = array('status' => true, 'message' => 'Cost Added Successfully...');
        $returnData += $results;
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add Cost...');
    }


    header('Content-Type: text/json');
    echo json_encode($returnData);
}



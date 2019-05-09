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
        $returnData = array('status' => true, 'message' => 'Cost Added Successfully...');
    }else{
        $returnData = array('status' => false, 'message' => 'Can\'t Add Cost...');
    }


    header('Content-Type: text/json');
    echo json_encode($returnData);
}



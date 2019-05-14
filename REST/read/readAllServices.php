<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 15:34
 */

header('Content-Type: application/json');



try {
    $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
    $sql = "SELECT * FROM services";
    $stmt = $conn->prepare($sql);
    $stmt->execute();
    $results = $stmt->fetchAll(PDO::FETCH_ASSOC);

    if ($results) {
        $returnData = array('status' => true, 'message' => 'Successfully Returned All Services...');
    } else {
        $returnData = array('status' => false, 'message' => 'Can\'t Get Services...');
    }

    $services = array('service' => $results);
    echo json_encode($returnData + $services);

}catch (Exception $e){
    die('Error : ' .$e->getMessage());
}


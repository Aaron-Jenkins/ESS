<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 15:34
 */

header('Content-Type: application/json');
$id = isset($_GET['id']) ? $_GET['id'] : '';



if(!empty($id)) {

    try {
        $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
        $sql = "SELECT * FROM company WHERE id=:id";
        $stmt = $conn->prepare($sql);
        $stmt->bindValue(":id", $id);
        $stmt->execute();
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($result) {
            $returnData = array('status' => true, 'message' => 'Successfully Returned Company...');
        } else {
            $returnData = array('status' => false, 'message' => 'Can\'t Get Company...');
        }

        $services = array('company' => $result);
        echo json_encode($returnData + $services);

    }catch (Exception $e){
        die('Error : ' .$e->getMessage());
    }
}


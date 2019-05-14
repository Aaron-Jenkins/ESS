<?php
/**
 * Created by PhpStorm.
 * User: james
 * Date: 08/05/19
 * Time: 15:34
 */

$username = isset($_POST['username']) ? $_POST['username'] : '';
$password = isset($_POST['password']) ? $_POST['password'] : '';



if(!empty($username) && !empty($password)) {

    try {
        $conn = new PDO ("mysql:host=localhost;dbname=cleaning;", "cleaning", "cleaningpassword");
        $sql = "SELECT id, firstName, lastName FROM users WHERE lower(firstName)=:username AND password=:password";
        $stmt = $conn->prepare($sql);
        $stmt->bindValue(":username", $username);
        $stmt->bindValue(":password", $password);
        $stmt->execute();
        $result = $stmt->fetch(PDO::FETCH_ASSOC);

        if ($result) {
            $returnData = array('status' => true, 'message' => 'Successfully Logged In User...');
        } else {
            $returnData = array('status' => false, 'message' => 'Can\'t Log User In...');
        }

        $services = array('user' => $result);
        header('Content-Type: application/json');
        echo json_encode($returnData + $services);

    }catch (Exception $e){
        die('Error : ' .$e->getMessage());
    }
}


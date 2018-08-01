<?php
    $con = mysqli_connect("localhost", "id6564902_parth", "lifegood12345", "id6564902_kuncoro_login");
    
    $name = $_POST["name"];
    $email = $_POST["email"];
    $phone = $_POST["phone"];
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO login_cred (name, email, phone, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $email, $phone, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>

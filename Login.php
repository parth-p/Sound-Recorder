<?php
    $con = mysqli_connect("localhost", "id6564902_parth", "lifegood12345", "id6564902_kuncoro_login");
    
    $name = $_POST["name"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM login_cred WHERE name = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $name, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $name, $email, $phone, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["name"] = $name;
        $response["email"] = $email;
        $response["phone"] = $phone;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>

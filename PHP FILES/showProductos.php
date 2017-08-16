<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

if($_SERVER["REQUEST_METHOD"]=="POST"){
    require 'connection.php';
    showProductos();
}

function showProductos(){
    global $connect;
    $query = "Select * from products";
    
    $result = mysqli_query($connect, $query);
    $number_of_rows = mysqli_num_rows($result);
    
    $temp_array = array();
    
    if($number_of_rows > 0) {
        while($row = mysqli_fetch_assoc($result)){
            $temp_array[] = $row;
        }
    }
    
    header('Content-Type: application/json');
    echo json_encode(array("products"=>$temp_array));
    mysqli_close($connect);
}
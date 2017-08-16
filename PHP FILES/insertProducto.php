<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


if($_SERVER["REQUEST_METHOD"]=="POST"){
    require 'connection.php';
    createProduct();
}

function createProduct(){
    global $connect;
    
    $name = $_POST["name"];
    $price = $_POST["price"];
    /* @var $_POST type */
    $description = $_POST["description"];
    
    $query = "INSERT INTO products (name, price, description) VALUES ('$name','$price','$description')";
    
    mysqli_query($connect, $query) or die (mysqli_errno($connect));
    mysqli_close($connect);
}

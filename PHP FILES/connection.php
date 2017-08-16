<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define('hostname', 'localhost');
define('user', 'root');
define('password', 'surfing');
define('databaseName', 'clientes');

$connect = mysqli_connect(hostname, user, password, databaseName);
?>

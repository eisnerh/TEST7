<?php 
function getAllClientes()
{
  $mysqli = getConnexion();
  $query = 'SELECT * FROM cliente;';
  return $mysqli->query($query);
}
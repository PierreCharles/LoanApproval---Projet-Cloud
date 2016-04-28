<?php
require_once 'vendor/autoload.php'; 

$app = new Silex\Application(); 

$app->get('/home/', function() { 
    return 'Hello '; 
}); 

$app->run(); 

<?php
require_once '../vendor/autoload.php'; 

$app = new Silex\Application();
$app['debug'] = true;
$app->register(new Silex\Provider\TwigServiceProvider(), array(
    'twig.path' => __DIR__.'/../views/',
));

$app->get('/loanApproval', function() use($app) {
    return $app['twig']->render('form.html.twig', array(
        'path_web' => __DIR__
    ));
});


$app->run(); 

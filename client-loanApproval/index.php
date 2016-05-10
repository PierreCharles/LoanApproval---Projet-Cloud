<?php
require_once 'vendor/autoload.php'; 

$app = new Silex\Application();

define('SMARTY_PATH',__DIR__.'vendor/smarty/smarty/');

use FractalizeR\ServiceProvider as ServiceProviderSmarty;

$app->register(new ServiceProviderSmarty, array(
    'smarty.dir' => SMARTY_PATH,
    'smarty.options' => array(
        'template_dir' => __DIR__.'/views/templates/',
        'compile_dir' => SMARTY_PATH.'demo/templates_c',
        'config_dir' =>  SMARTY_PATH.'demo/configs',
        'cache_dir' => __DIR__.'/views/templates/cache/',)
    )
);

$app->get('/loanApproval', function() use($app) {
    
    $form = $app['smarty']->fetch('form.tpl');
    
    $app['smarty']->assign(array(
        'BODY' => $form
    ));
    
    return $app['smarty']->fetch('header.tpl');
});


$app->run(); 

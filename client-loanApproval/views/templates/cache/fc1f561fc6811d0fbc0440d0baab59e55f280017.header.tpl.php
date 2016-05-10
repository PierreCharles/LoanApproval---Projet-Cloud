<?php
/* Smarty version 3.1.30-dev/68, created on 2016-05-10 11:50:08
  from "/home/julien/Documents/Licence/Cloud/LoanApproval-Projet-Cloud/client-loanApproval/views/templates/header.tpl" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30-dev/68',
  'unifunc' => 'content_5731aed01f75f9_38414262',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    'c4dac55b5dbc1d3ac5b7a4f23a3981ab8b9cd34d' => 
    array (
      0 => '/home/julien/Documents/Licence/Cloud/LoanApproval-Projet-Cloud/client-loanApproval/views/templates/header.tpl',
      1 => 1462873546,
      2 => 'file',
    ),
  ),
  'cache_lifetime' => 3600,
),true)) {
function content_5731aed01f75f9_38414262 (Smarty_Internal_Template $_smarty_tpl) {
?>
<!DOCTYPE html>
<html>
    <head>
        <title>LoanApproval</title>
    </head>
    <body>
        <div class="form">
    <p class="h1">Demande de crédits :</p>
    <form method="POST" action="">
        <table>
            <tr>
                <td>Nom :</td>
                <td><input type="text" id="lastName" name="lastName" required=""></td>
            </tr>
            <tr>
                <td>Prenom :</td>
                <td><input type="text" id="firstName" name="firstName"></td>
            </tr>
            <tr>
                <td>Numéro de compte :</td>
                <td><input type="text" id="accountId" name="accountId"></td>
            </tr>
            <tr>
                <td>Montant de la demande de crédit :</td>
                <td><input type="text" id="amountLoal" name="amountLoal" required=""></td>
            </tr>
            <tr>
                <td><input type="button" class="btn btn-default" value="demander crédit"></td>
            </tr>
        </table>
    </form>
</div>
    </body>
</html><?php }
}

<?php
/* Smarty version 3.1.30-dev/68, created on 2016-05-10 11:50:08
  from "/home/julien/Documents/Licence/Cloud/LoanApproval-Projet-Cloud/client-loanApproval/views/templates/form.tpl" */

/* @var Smarty_Internal_Template $_smarty_tpl */
if ($_smarty_tpl->_decodeProperties($_smarty_tpl, array (
  'version' => '3.1.30-dev/68',
  'unifunc' => 'content_5731aed01e95e7_21394390',
  'has_nocache_code' => false,
  'file_dependency' => 
  array (
    '151da63fbb765bd6cabd585b361a8814a95e8ddb' => 
    array (
      0 => '/home/julien/Documents/Licence/Cloud/LoanApproval-Projet-Cloud/client-loanApproval/views/templates/form.tpl',
      1 => 1462869089,
      2 => 'file',
    ),
  ),
  'cache_lifetime' => 3600,
),true)) {
function content_5731aed01e95e7_21394390 (Smarty_Internal_Template $_smarty_tpl) {
?>
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
</div><?php }
}

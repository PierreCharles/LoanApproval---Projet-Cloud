$(document).ready(function() {
    
    $('.show-form').on('click', function () {
        $('.form').fadeToggle("slow"); 
    });
    
    $('.manage-accounts').on('click', function () {
        window.location.href="/loanApproval/login";
    });
    
    $('.submitLoan').on('click', addLoan);
});

function addLoan() {
    var urlService = "https://intense-everglades-81868.herokuapp.com/loanapproval/creditrequest";
    
    var loan = {
        lastName:$('#lastName').val(),
        firstName:$('#firstName').val(),
        amount:parseFloat($('#amountLoan').val())
    };
    
    $.ajax({
        url : urlService,
        type : 'POST', 
        data : JSON.stringify(loan),
        contentType: 'application/json',
    }).done(function(data) {
       // TRAITEMENT SI CEST VALIDE AVEC LES VALEURS RETOURNEES
       $('.errorLoan').text("La demande de crédit est okay");
    }).fail(function(data) {
        $('.errorLoan').text("Une erreur est survenue lors de la demande de crédit");
    });
}


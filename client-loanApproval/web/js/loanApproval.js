$(document).ready(function() {
    
    $('.show-form').on('click', function () {
        $('.form').fadeToggle("slow"); 
    });
    
    $('.manage-accounts').on('click', function () {
        window.location.href="/loanApproval/login";
    });
    
    $('.submitLoan').on('click', addLoan);
});

/**
 * Method to add a Loan with an AJAX POST Json
 */
function addLoan() {
    
    
    if(!$('#accountId').val()) {
        var loan = {
            lastName:$('#lastName').val(),
            firstName:$('#firstName').val(),
            sold:parseFloat($('#amountLoan').val())
        };
        
        // Webservice url
        var urlService = "https://intense-everglades-81868.herokuapp.com/loanapproval/creditRequestByName";
    } else {
        var loan = {
            idAccount:parseInt($('#accountId').val()),
            sold:parseFloat($('#amountLoan').val())
        }
        
        // Webservice url
        var urlService = "https://intense-everglades-81868.herokuapp.com/loanapproval/creditRequestById";
    }
    
    $.ajax({
        url : urlService,
        type : 'POST', 
        data : JSON.stringify(loan),
        contentType: 'application/json',
    }).done(function(data) {
       if(data.response !== "" || data.response !== undefined) {
            $('.errorLoan').text(data.response);
        } else {
            $('.errorLoan').text("La demande de crédit a rencontré un problème");
        }
    }).fail(function(data) {
        $('.errorLoan').text("Une erreur est survenue lors de la demande de crédit");
    });
}


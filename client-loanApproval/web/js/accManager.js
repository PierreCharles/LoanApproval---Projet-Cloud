$(document).ready(function() {
    
    refreshAccounts();
    
    $('.refresh').on('click',refreshAccounts);
    
    $('.addAccount').on('click', function() {
        $(':input','#form-account-add')
        .not(':button, :submit, :reset, :hidden')
        .val('');

        $('.formAccount').fadeToggle('slow');
    });
    
    $('.disconnect').on('click', function() {
        window.location.href = "/loanApproval/disconnect";
    });
    
    $('.save').on('click', saveAccount);
    
});

/**
 * Method to refresh the list of the accounts with an Ajax request
 */
function refreshAccounts() {
    // WebserviceUrl
    var urlService = "http://accmanager-1294.appspot.com/rest/bankAccount/";
    
    $.ajax({ 
        type: 'GET', 
        url: urlService+"getAccounts"
    }).done(function (data) {
        $('.tbodyAccounts').empty();
        var compteur = 0;
  
        $.each( data, function(index, val) {
            var datasHTML = "data-lastName='"+val.lastName+"' data-firstName='"+val.firstName+"' data-account='"+val.account+"' data-amount='"+val.amount+"' data-risk='"+val.risk+"'";
 
            var boutonDelete = "<input type='button' "+datasHTML+" class='btn-info deleteAccount"+compteur+"' value='Supprimer'> ";
            var boutonUpdate = "<input type='button' "+datasHTML+" class='btn-info updateAccount"+compteur+"' value='Update'> ";
            
            var line = "<tr><td>"+val.account+"</td><td>"+val.lastName+"</td><td>"+val.firstName+"</td><td>"+val.amount+"</td><td>"+val.risk+"</td><td>"+boutonDelete+boutonUpdate+"</td></tr>";
            $('.tbodyAccounts').append(line);

            $('.deleteAccount'+compteur).on('click',function() {
                var idAccount = $(this).data('account');
                
                $.ajax({ 
                    type: 'GET', 
                    url: urlService+"deleteAccount/"+idAccount
                }).done(function (data) { 
                    if(data.message !== "" || data.message !== undefined) {
                        $('.errorRefresh').text(data.message);
                    } else {
                        $('errorRefresh').text(data.error);
                    }   
                }).fail(function(data) {         
                    $('errorRefresh').text(data.message);   
                });
                
                window.location.reload();
            });

            $('.updateAccount'+compteur).on('click',function() {
                $('#lastName').val($(this).data('lastname'));
                $('#firstName').val($(this).data('firstname'));
                $('#account').val($(this).data('account'));
                $('#risk').val($(this).data('risk'));
                $('#amount').val($(this).data('amount'));
                $('#update').val(1);
                $('.formAccount').fadeToggle('slow');
            });
            
            compteur++;
        });

        if(compteur === 0) {
            var line = "<tr>Il n'y a pas de comptes actuellement</tr>";
            $('.tbodyAccounts').append(line);
        }     
    }).fail(function () {
        $('.errorRefresh').text("Une erreur est survenue lors de la récupération des comptes");
    });
}

/**
 * Method to save an account, POST Json to the webservice with Ajax
 */
function saveAccount() {
    var urlService = "http://accmanager-1294.appspot.com/rest/bankAccount/";
    
    var account = {
        lastName:$('#lastName').val(),
        firstName:$('#firstName').val(),
        account:$('#account').val(),
        amount:parseFloat($('#amount').val()),
        risk:$('#risk').val()
    };
    
    if($('#update').val() == 1) {
        var path = 'updateAccount';
    } else {
        var path = 'createAccount';
    }

    $.ajax({
        url : urlService+path,
        type : 'POST', 
        data : JSON.stringify(account),
        contentType: 'application/json'
    }).done(function(data) {
        if(data.message !== "" || data.message !== undefined) {
            $('.errorRefresh').text(data.message);
        } else {
            $('errorRefresh').text(data.error);
        }     

        refreshAccounts();
    }).fail(function(data) {
        $('.errorRefresh').text("Une erreur est survenue lors de l'ajout ou de la modification du compte: "+account.account);
    });
    
}

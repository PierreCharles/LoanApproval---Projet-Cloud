$(document).ready(function() {
    
    refreshAccounts();
    
    $('.refresh').on('click',refreshAccounts);
    
    $('.addAccount').on('click', function() {
        $('.formAccount').fadeToggle('slow');
    });
    
    $('.disconnect').on('click', function() {
        window.location.href = "/loanApproval/disconnect";
    });
    
    $('.save').on('click', saveAccount);
    
});

function refreshAccounts() {
    
    var urlService = "http://accmanager-1294.appspot.com/rest/bankAccount/";
    
    $.ajax({ 
        type: 'GET', 
        url: urlService+"getAccounts"
    }).done(function (data) {
        $('.tbodyAccounts').empty();
        var compteur = 0;
         var boutonDelete = "<input type='button' class='btn-info deleteAccount' value='Supprimer'> ";
            var boutonUpdate = "<input type='button' class='btn-info updateAccount' value='Update'> ";
            
        $.each( data, function(index, val) {
            var line = "<tr><td>"+val.account+"</td><td>"+val.lastName+"</td><td>"+val.firstName+"</td><td>"+val.amount+"</td><td>"+val.risk+"</td><td>"+boutonDelete+boutonUpdate+"</td></tr>";
            $('.tbodyAccounts').append(line);
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

function saveAccount() {
    
}
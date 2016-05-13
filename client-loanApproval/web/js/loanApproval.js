$(document).ready(function() {
    
    $('.show-form').on('click', function () {
        $('.form').fadeToggle("slow"); 
    });
    
    $('.manage-accounts').on('click', function () {
        window.location.href="/loanApproval/login";
    });
});




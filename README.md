# LoanApproval---Projet-Cloud
-------
Implementation of Loan Approval services 

Log in to AppEngine or Heroku : Login : projetcloud.loanapproval@gmail.com Password : projetcloud2016


## Client 

The client is a simple interface with bootstrap and the framework Silex, allow to test the differents webservices. 
Use Chrome plug-in 'enable cross-origin' to disable : Access-Control-Allow-Origin --> CORS when try to post informations.

This plugin allows the user to add/update informations because the appengine of Google deny the request which come from other Origin, it's the only solution what we find

#### Run the client :

Go into client-loanApproval directory:

    cd client-loanApproval/

Install all dependencies:

    composer install

Run a server:

    php -S localhost:8080 -t web/

Launch a web page:

Go to [http://localhost:8080/loanApproval](http://localhost:8080/loanApproval)

Connect into admin interface with login "admin" and password "admin"

## AccManager

AppManager is deploying here : https://1-dot-accmanager-1294.appspot.com/rest/bankAccount/

The accManager is a webservice CRUD on bank account, allow to Create, Read, Update, Delete accounts with a firstName, lastName, account id, amount of the account and the risk 

- /getAccounts --> method to return all the accounts
- /createAccount --> method to create an account with JSON POST
- /getAccount/{accountId} --> return an account with his ID in param
- /getAccountByProperty --> return an account with the lastName and firstName in param POST ( JSON datas)
- /updateAccount --> method to update an account wich is send in POST or PUT HTTP Request
- /deleteAccount/{idAccount} --> delete an account with his ID with GET or DELETE HTTP Request

Add an account with POST methode:

    curl -XPOST -H 'Content-Type:application/json' -d '{"firstName": "Julien","lastName": "BRAT","account": "00001","amount": "10000","risk": "true"}' http://accmanager-1294.appspot.com/rest/bankAccount/createAccount

Update an account with POST methode:

    curl -XPOST -H 'Content-Type:application/json' -d '{"firstName": "Pierre","lastName": "Charles","account": "00001","amount": "10000","risk": "true"}' http://accmanager-1294.appspot.com/rest/bankAccount/updateAccount

 Get list accounts with GET methode:

    curl -i -H Accept:application/json -X GET http://accmanager-1294.appspot.com/rest/bankAccount/getAccounts

Get account by id with GET methode:

    curl -i -H Accept:application/json -X GET http://accmanager-1294.appspot.com/rest/bankAccount/getAccount/00001

Get account by lastName and firstName POST method : 

    curl -i -H Accept:application/json -X POST http://accmanager-1294.appspot.com/rest/bankAccount/getAccountByProperty -H Content-Type:application/json -d '{"lastName": "BRAT", "firstName" : "Julien"}'
    
Deleting an account by id with GET methode:

    curl -i -H Accept:application/json -X GET http://accmanager-1294.appspot.com/rest/bankAccount/deleteAccount/00001

## AppManager

AppManager is deploying here : http://1-dot-appmanager-1280.appspot.com/rest/approval/

The AppManager is a webservice CRUD on approval, allow to Create, Read, Update, Delete approvals with a firstName, lastName, id, response of the approval 

- /getApprovals --> method to return all the approvals
- /createApproval --> method to create an approval with JSON POST
- /getApproval/{approvalId} --> return to get an approval with his ID in param
- /updateApproval --> method to update an approval wich is send in POST or PUT HTTP Request
- /deleteApproval/{idApproval} --> delete an approval with his ID with GET or DELETE HTTP Request

For testing JSON rweb service result, in a terminal, try these commands:

Add an approval with POST methode:

    curl -XPOST -H 'Content-Type:application/json' -d '{"firstName": "Pierre","lastName": "Charles","id": "00001","response": "approved"}' http://1-dot-appmanager-1280.appspot.com/rest/approval/createApproval

Update an approval with POST methode:

    curl -XPOST -H 'Content-Type:application/json' -d '{ "firstName": "Pierre UPDATE","lastName": "CHARLES UPDATE","id": "00001","response": "approved"}' http://1-dot-appmanager-1280.appspot.com/rest/approval/updateApproval

 Get list approvals with GET methode:

    curl -i -H Accept:application/json -X GET http://1-dot-appmanager-1280.appspot.com/rest/approval/getApprovals

Get approval by id with GET methode:

    curl -i -H Accept:application/json -X GET http://1-dot-appmanager-1280.appspot.com/rest/approval/getApproval/00001

Deleting an approval by id with GET methode:

    curl -i -H Accept:application/json -X GET http://1-dot-appmanager-1280.appspot.com/rest/approval/deleteApproval/00001


## CheckAccount

CheckAccount is deployed on Heroku here : https://afternoon-everglades-21216.herokuapp.com/checkaccount/

Test CheckAccount with this command line :

/checkrisk --> method to return the risk of the account

    curl -i -H Accept:application/json -X GET https://afternoon-everglades-21216.herokuapp.com/checkaccount/checkrisk/00001


## LoanApproval

LoanApproval is deployed on Heroku here :  https://intense-everglades-81868.herokuapp.com/loanapproval/
Test this service with curl :

/creditRequestByName :

    curl -XPOST -H 'Content-Type:application/json' -d '{"firstName": "Pierre", "lastName" : "CHARLES", "sold" : "10000" }' https://intense-everglades-81868.herokuapp.com/loanapproval/creditRequestByName

/creditRequestById :

    curl -XPOST -H 'Content-Type:application/json' -d '{"idAccount": "00001", "sold" : "11000"}' https://intense-everglades-81868.herokuapp.com/loanapproval/creditRequestById




# LoanApproval---Projet-Cloud
-------

Implémenter sous forme de services le Loan approval (demande de crédit).

## AccManager

The accManager is a webservice CRUD on bank account, allow to Create, Read, Update, Delete accounts with a firstName, lastName, account id, amount of the account and the risk 

- /getAccounts --> method to return all the accounts
- /createAccount --> method to create an account with JSON POST
- /getAccount/{accountId} --> return to get an account with his ID in param
- /updateAccount --> method to update an account wich is send in POST or PUT HTTP Request
- /deleteAccount/{idAccount} --> delete an account with his ID with GET or DELETE HTTP Request

## AppManager

For testing JSON rweb service result, in a terminal, try these commands:

    curl -XGET -H "Accept: application/json" http://appmanager-1280.appspot.com/rest/appmanager/getApprovals
    curl -XGET -H "Accept: application/json" http://appmanager-1280.appspot.com/rest/appmanager/getApproval/picharles

Also, try to create new approval using JSON :

    curl -XPOST -H "Accept: application/json" -H 'Content-Type: application/json' \
    -d '{ "name": "picharles", "manualResponse": "true"}' \
    http://appmanager-1280.appspot.com/rest/appmanager/createApproval


## CheckAccount

CheckAccount is deployed on Heroku here : https://afternoon-everglades-21216.herokuapp.com/checkaccount/checkrisk/1000

Commande for create the checkaccount web service with maven and deploying into heroku :

    mvn archetype:generate -DarchetypeArtifactId=jersey-heroku-webapp -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=fr.checkaccount -DartifactId=checkaccount -Dpackage=fr.checkaccount -DarchetypeVersion=2.22.2

    cd checkaccount

    mvn clean package

    git init

    heroku create

    git add src/ pom.xml Procfile system.properties

    git commit -m "Initial commit"

    git push heroku master


## LoanApproval

LoanApproval is deployed on Heroku here :  https://intense-everglades-81868.herokuapp.com/myresource

    mvn archetype:generate -DarchetypeArtifactId=jersey-heroku-webapp -DarchetypeGroupId=org.glassfish.jersey.archetypes -DinteractiveMode=false -DgroupId=fr.loanapproval -DartifactId=loanapproval -Dpackage=fr.loanapproval -DarchetypeVersion=2.22.2

    cd loanapproval

    mvn clean package

    git init

    heroku create

    git add src/ pom.xml Procfile system.properties

    git commit -m "Initial commit"

    git push heroku master


## Client

The client is a simple interface with bootstrap and the framework Silex, allow to test the differents webservices. 

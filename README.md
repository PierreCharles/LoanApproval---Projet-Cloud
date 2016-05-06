# LoanApproval---Projet-Cloud

Implémenter sous forme de services le Loan approval (demande de crédit).

## AccManager

## AppManager

For testing JSON rweb service result, in a terminal, try these commands:

- Get all approvals :
    curl -XGET -H "Accept: application/json" http://appmanager-1280.appspot.com/rest/appmanager/getApprovals
- Get approval by id : 
    curl -XGET -H "Accept: application/json" http://appmanager-1280.appspot.com/rest/appmanager/getApproval/picharles
- Also, try to create new approval using JSON :
    curl -XPOST -H "Accept: application/json" -H 'Content-Type: application/json' \
    -d '{ "name": "picharles", "manualResponse": "true"}' \
    http://appmanager-1280.appspot.com/rest/appmanager/createApproval

## CheckAccount

## LoanApproval

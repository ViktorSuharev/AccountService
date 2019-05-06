# AccountService

Installation:
1. mvn clean install
2. cd target
3. java -Dport=${CUSTOM_PORT} -jar com.visu.account-1.0-SNAPSHOT-jar-with-dependencies.jar
   It is possible to specify tcp port by passing property port. By default 8080 is used.

Usage:
- GET /accounts/{id}
  returns Account by provided id

- POST /accounts
   body sample: {"id" : "1", "balance" : "1"}
   creates Account

- PUT /accounts
   body sample: {"id" : "1", "balance" : "1"}
   updates Account

- DELETE /accounts/{id}
deletes Account by provided id

- PUT /accounts/transfer
   body sample: {"senderId" : "1", "receiverId" : "2", "amount" : "1"}
   does transfer between accounts

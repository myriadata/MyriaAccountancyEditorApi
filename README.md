# MyriaInvoiceApi
API REST - Invoice PDF generation

## Develop

To run the application in development mode with hot reload :
```
mvn compile quarkus:dev -Ddebug=true
```
You can now attach your java debugger to 5005 port (by default).
Instead of true value, debug parameter accept the port number of your choice.

## Dependencies upgrade

Maven allow to know dependencies have to be upgrade.
```
mvn versions:display-dependency-updates
```
A lot of dependencies in Dependency Managment can't be directly upgrade because it manage mainly by quarkus.
But you can easily upgrade dependencies in Dependencies at the end of the command result.
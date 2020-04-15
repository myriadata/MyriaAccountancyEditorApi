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

## Commits

### Commit Message Format
```
<type>(<scope>): <subject>
<BLANK LINE>
<body>
```

### Type

Must be one of the following :
- **build** : Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)
- **ci** : Changes to our CI configuration files and scripts (example scopes: Circle, BrowserStack, SauceLabs)
- **docs** : Documentation only changes
- **feat** : A new feature
- **fix** : A bug fix
- **perf** : A code change that improves performance
- **refactor** : A code change that neither fixes a bug nor adds a feature
- **style** : Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- **test** : Adding missing tests or correcting existing tests

### Scope

Scope is to provide the context. Actual contexts are :
- **GLOBAL** : for anything about technical needs
- **INVOICE** : about invoice generation and domain
- **REFERENTIAL** : about all referential data

### Subject

Subject is require and has to be shorter than 50 caracters. It is an overview of what commit doing.

###

Body is optional, it can be multiline, and it is not size limited.

# MyriaAccountancyEditorApi
API REST - Accountancy documents PDF generation

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

If you upgrade GraalVM or Java version, you have to update docker based image used in CircleCI configuration.
Deploy job use clever-tools and baconjs dependency that can be upgraded too. 

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

### Body

Body is optional, it can be multiline, and it is not size limited.

## Build

Quarkus allow to build project in native mode to improve boot time, first response time and memory print.

### Prerequisites

- GraalVM

### Native mode

You before need to install native-image if not done :
```
export PATH="$GRAALVM_HOME"/bin:"$PATH"
gu install native-image
```

On OsX, GraalVM can be damaged, that's mean to authorize it :
```
sudo xattr -r -d com.apple.quarantine /Library/Java/JavaVirtualMachines/{{graalvm-folder}}
```

Then build with native profile :
```
mvn clean package -Pnative
```

### Executable

```
./target/MyriaAccountancyEditorApi-{{version}}-runner
```

## CI/CD

CircleCI configuration is made up two jobs :
- build
- containerize

For each commit pushed in develop branch a build job is trigger. Contenainerize job is only for master branch. Contenairize job require build job.

### Build job

Checkout projet and build it natively. Executable and Dockerfile is store on workspace to eventually be used by other jobs. This build run unit tests.

### Containerize job

Create docker image from builded executable and Dockerfile. Docker image is versioned and push to DockerHub registry.
https://hub.docker.com/r/myriadata/myria-accountancy-editor-api.

### Run application in docker mode

Launch application from last binary available on docker hub registry : 

```
docker run --rm -p 8080:8080 myriadata/myria-accountancy-editor-api:latest
```
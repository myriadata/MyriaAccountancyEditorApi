FROM registry.access.redhat.com/ubi8/ubi-minimal:8.2
WORKDIR /work/
COPY target/MyriaAccountancyEditorApi-*-runner runner
RUN chmod 775 /work
EXPOSE 8080
ENTRYPOINT ["./runner", "-Dquarkus.http.host=0.0.0.0"]
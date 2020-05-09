FROM registry.access.redhat.com/ubi8/ubi-minimal:8.2
WORKDIR /work/
COPY target/MyriaInvoiceApi-*-runner runner
RUN chmod 775 /work
EXPOSE 8080
CMD ["./runner", "-Dquarkus.http.host=0.0.0.0"]
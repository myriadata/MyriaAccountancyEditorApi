package fr.myriadata.myriaaccountancyeditor.api.resource.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @Override
    public Response toResponse(Throwable e) {
        LOGGER.error("Unhandled error", e);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

}

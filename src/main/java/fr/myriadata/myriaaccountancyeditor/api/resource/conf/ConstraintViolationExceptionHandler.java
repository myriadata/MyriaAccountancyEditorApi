package fr.myriadata.myriaaccountancyeditor.api.resource.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.*;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConstraintViolationExceptionHandler.class);
    private static final List<String> allowedPrefixPropertyPaths = List.of("generate.");

    @Override
    public Response toResponse(ConstraintViolationException e) {
        checkConstraints(e);

        Map<String, List<String>> violationsByField = new HashMap<>();
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            String field = field(constraintViolation.getPropertyPath());
            if (!violationsByField.containsKey(field)) {
                violationsByField.put(field, new ArrayList<>());
            }

            String constraint = constraint(constraintViolation.getConstraintDescriptor());
            violationsByField.get(field).add(constraint);
        }

        LOGGER.warn("Constraint violation : " + violationsByField.toString());

        return Response.status(Response.Status.BAD_REQUEST)
                       .type(MediaType.APPLICATION_JSON)
                       .entity(violationsByField)
                       .build();
    }

    private void checkConstraints(ConstraintViolationException e) {
        for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
            if (!isAllowedPropertyPath(constraintViolation.getPropertyPath())) {
                throw new IllegalStateException("ConstraintViolationException never expected !", e);
            }
        }
    }

    private String field(Path propertyPath) {
        // example : from generate.arg0.number to invoice.number

        String prefix = prefixPropertyPath(propertyPath).get(); // Unsafe .get(), but indirectly check in toRespond method
        String pathWithoutPrefix = propertyPath.toString().substring(prefix.length());
        String property = pathWithoutPrefix.substring(pathWithoutPrefix.indexOf(".") + 1);
        return "invoice." + property;
    }

    private boolean isAllowedPropertyPath(Path propertyPath) {
        return prefixPropertyPath(propertyPath).isPresent();
    }

    private Optional<String> prefixPropertyPath(Path propertyPath) {
        for (String prefix : allowedPrefixPropertyPaths) {
            if (propertyPath.toString().startsWith(prefix)) {
                return Optional.of(prefix);
            }
        }

        return Optional.empty();
    }

    private String constraint (ConstraintDescriptor constraintDescriptor) {
        String completeName = constraintDescriptor.getAnnotation().annotationType().getName();
        String rootName = constraintDescriptor.getAnnotation().annotationType().getPackageName() + ".";
        return completeName.substring(rootName.length());
    }

}

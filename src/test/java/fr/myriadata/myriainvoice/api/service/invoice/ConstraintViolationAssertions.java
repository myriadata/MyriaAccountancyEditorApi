package fr.myriadata.myriainvoice.api.service.invoice;

import org.junit.jupiter.api.Assertions;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ConstraintViolationAssertions {

    private ConstraintViolationAssertions() {}

    public static void asserts(Map<String, List<String>> expected, Set<ConstraintViolation<?>> actual) {
        assertAllActualConstraintsExistsInExpectedConstraints(expected, actual);
        assertAllExpectedConstraintsExistsInActualConstraints(expected, actual);
        Assertions.assertEquals(expectedConstraintViolation(expected), actual.size());
    }

    private static void assertAllActualConstraintsExistsInExpectedConstraints(Map<String, List<String>> expected, Set<ConstraintViolation<?>> actual) {
        for (ConstraintViolation constraintViolation : actual) {
            String field = constraintViolation.getPropertyPath().toString();
            String fieldMessage = "field [" + field + "]";
            String constraint = constraintViolation.getMessageTemplate();
            String constraintMessage = "constraint [" + constraint + "]";

            Assertions.assertTrue(expected.containsKey(field),
                    fieldMessage + " doesn't expect any constraint");
            Assertions.assertTrue(expected.get(field).contains(constraint),
                    constraintMessage + " isn't expected for " + fieldMessage);
        }
    }

    private static void assertAllExpectedConstraintsExistsInActualConstraints(Map<String, List<String>> expected, Set<ConstraintViolation<?>> actual) {
        for (Map.Entry<String, List<String>> entry : expected.entrySet()) {
            String field = entry.getKey();
            String fieldMessage = "field [" + field + "]";
            for (String constraint : entry.getValue()) {
                String constraintMessage = "constraint [" + constraint + "]";
                Assertions.assertTrue(actual.stream().anyMatch(
                        c -> c.getPropertyPath().toString().equals(field) && c.getMessageTemplate().equals(constraint)),
                        constraintMessage + " is required for " + fieldMessage);
            }
        }
    }

    private static long expectedConstraintViolation(Map<String, List<String>> expected) {
        return expected.values().stream().flatMap(List::stream).count();
    }

}

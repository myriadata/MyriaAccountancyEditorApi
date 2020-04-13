package fr.myriadata.myriainvoice.api.model.payment;

public enum PaymentMethod {

    CASH        ("en espèces"),
    CHEQUE      ("par chèque"),
    TRANSFER    ("par virement");

    private String label;

    PaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}

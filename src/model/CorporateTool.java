package model;

public class CorporateTool extends Tool {

    private static final int MIN_LICENSES = 1;

    private final int activeLicensesCount;//عددهم لانو لشركة

    public CorporateTool(String name, double basePrice,
                         String tariff, int activeLicensesCount) {
        super(name, basePrice, tariff);

        if (activeLicensesCount < MIN_LICENSES) {
            System.out.println("[WARN] Corporate licenses count must be >= " + MIN_LICENSES + ". Received: " + activeLicensesCount + ". Forced to " + MIN_LICENSES + ".");
            activeLicensesCount = MIN_LICENSES;
        }
        this.activeLicensesCount = activeLicensesCount;
    }

    @Override
    public double calculateBillingPrice() {
        return getBasePrice() * getTariffMultiplier() * activeLicensesCount;
    }

    public int getActiveLicensesCount() {
        return activeLicensesCount;
    }
}
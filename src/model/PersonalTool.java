package model;

public class PersonalTool extends Tool {//كلمة إكستندس تعني أن هذا الكلاس يرث من كلاس تول. أي أنه ابنه، ويرث كل حقوله وميثودزه.

    public PersonalTool(String name, double basePrice) {
        super(name, basePrice);// كلمة سوبر تعني الأب. نمرر له الاسم والسعر. الأب سيكمل التعامل مع التصنيف الافتراضي.
    }

    public PersonalTool(String name, double basePrice, String tariff) {
        super(name, basePrice, tariff);
    }

    @Override//كلمة أوفررايد تعني إعادة تعريف
    public double calculateBillingPrice() {
        return getBasePrice() * getTariffMultiplier();
    }
}
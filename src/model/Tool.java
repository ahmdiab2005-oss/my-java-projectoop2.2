package model;
//كلاس مجرّد (abstract class):
//هو كلاس يمثل مفهوماً عاماً. لا يمكن إنشاء كائن منه مباشرة.
public abstract class Tool {
    //protected حتى الالأبناء PersonalTool و corporateTool يوصولو لها فقط
    protected static final double HOUSEHOLD_COEFFICIENT = 1.00;
    protected static final double SEMIPRO_COEFFICIENT = 1.40;
    protected static final double PROFESSIONAL_COEFFICIENT = 1.90;

    protected static final int MIN_BATTERY_COUNT = 1;
    protected static final int MAX_BATTERY_COUNT = 2;

    protected static final double MIN_BASE_PRICE = 100.0;//ابت قيمته مئة. الحد الأدنى للسعر الأساسي.

    private final String name;
    private final double basePrice;
    private final String tariff;
    private final double tariffMultiplier;//معامل الضرب

    private Battery appliedBattery = null;
    private int batteryCount = 0;

    public Tool(String name, double basePrice) {
        this(name, basePrice, "HOUSEHOLD");
    }

    public Tool(String name, double basePrice, String tariff) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Tool name cannot be empty.");
        }

        if (basePrice <= 0) {
            System.out.println("[WARN] Base price must be > 0. Received: "
                    + basePrice + ". Forced to " + MIN_BASE_PRICE + ".");
            basePrice = MIN_BASE_PRICE;
        }
        //نعرّف متغيراً نصياً اسمه سيف تاريف. إذا كان التصنيف فارغاً نضع هوسهولد، وإلا نضع التصنيف بأحرف كبيرة. علامة الاستفهام تعني الشرط الثلاثي.
        String safeTariff = (tariff == null || tariff.isBlank()
                ? "HOUSEHOLD" : tariff.toUpperCase());


        this.name = name;
        this.basePrice = basePrice;
        this.tariff = safeTariff;

        this.tariffMultiplier = switch (safeTariff) {
            case "HOUSEHOLD"    -> HOUSEHOLD_COEFFICIENT;
            case "SEMIPRO"      -> SEMIPRO_COEFFICIENT;
            case "PROFESSIONAL" -> PROFESSIONAL_COEFFICIENT;
            default -> {
                System.out.println("[WARN] Unknown tariff '" + safeTariff
                        + "'. Falling back to HOUSEHOLD (1.00). "
                        + "Please notify administrator.");
                yield HOUSEHOLD_COEFFICIENT;//لو ما ادخل تاريف برجع هادي
            }
        };
    }
    //ميثود مجرّدة. لا تحتوي على كود تنفيذي. كل ابن ملزم بإعادة تعريفها. نوع الإرجاع رقم عشري
    //كل نوع أداة له معادلة حساب مختلفة:
    //
    //الفردية: basePrice × tariffMultiplier
    //
    //الشركات: basePrice × tariffMultiplier × licensesCount
    public abstract double calculateBillingPrice();

    public double calculateBillingPrice(Battery battery) {//نسخة محملة 1
        attachBattery(battery);
        return calculateBillingPrice() + battery.getPrice();
    }

    public double calculateBillingPrice(Battery battery, int count) {//نسخة محملة 2
        attachBattery(battery, count);
        return calculateBillingPrice() + battery.getPrice() * count;
    }

    public void attachBattery(Battery battery) {
        validateBattery(battery);
        appliedBattery = battery;
        batteryCount = 1;
    }

    public void attachBattery(Battery battery, int count) {
        validateBattery(battery);
        if (count < MIN_BATTERY_COUNT || count > MAX_BATTERY_COUNT) {
            throw new IllegalArgumentException("Battery count must be 1 or 2.");
        }
        appliedBattery = battery;
        batteryCount = count;
    }

    public String getName() {
        return name;
    }
    public double getBasePrice() {
        return basePrice;
    }
    public String getTariff() {
        return tariff;
    }
    public double getTariffMultiplier() {
        return tariffMultiplier;
    }
    public Battery getAppliedBattery() {
        return appliedBattery;
    }
    public int getBatteryCount() {
        return batteryCount;
    }

    private static void validateBattery(Battery battery) {
        if (battery == null) {
            throw new IllegalArgumentException("Battery cannot be null.");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{name='" + name + "', basePrice=" + basePrice
                + ", tariff='" + tariff + "'}";
    }
}
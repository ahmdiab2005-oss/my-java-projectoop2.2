import billing.ToolBilling;
import model.Battery;
import model.CorporateTool;
import model.PersonalTool;
import service.impl.EmailNotification;
import service.impl.SmsNotification;

public class Main {

    public static void main(String[] args) {

        System.out.println("=========== CASE 1: Personal Tool + SMS ===========");

        ToolBilling smsBilling = new ToolBilling(new SmsNotification());//حقنتو رسالة محددة ويمكن بنفس الكلاس احقن شو مبدي

        PersonalTool drill = new PersonalTool("Drill", 50.0, "SEMIPRO");
        double price1 = drill.calculateBillingPrice();
        System.out.printf("Personal tool price: %.2f%n", price1);
        smsBilling.processPayment(drill, "+79161234567", price1);

        System.out.println("\n=========== CASE 2: Personal Tool + Battery ===========");

        Battery battery = new Battery("18V Battery", 25.0);

        PersonalTool jack = new PersonalTool("Jackhammer", 120.0, "PROFESSIONAL");
        double price2 = jack.calculateBillingPrice(battery, 2);
        smsBilling.processPayment(jack, "+79161234567", price2);

        System.out.println("\n=========== CASE 3: Corporate Tool + Email ===========");

        ToolBilling emailBilling = new ToolBilling(new EmailNotification());

        CorporateTool corpSaw = new CorporateTool("Circular Saw", 80.0, "SEMIPRO", 5);
        double price3 = corpSaw.calculateBillingPrice();
        emailBilling.processPayment(corpSaw, "billing@company.com", price3);

        System.out.println("\n=========== CASE 4: Validation Tests ===========");

        System.out.println("\n[Test 1] Invalid SMS:");
        smsBilling.processPayment(drill, "12345", 100.0);

        System.out.println("\n[Test 2] Invalid Email:");
        emailBilling.processPayment(corpSaw, "invalid-email", 100.0);

        System.out.println("\n[Test 3] Base price <= 0:");
        PersonalTool freeTool = new PersonalTool("Free", -10.0, "HOUSEHOLD");
        System.out.printf("Free tool price (should be 100.0): %.2f%n",
                freeTool.calculateBillingPrice());

        System.out.println("\n[Test 4] Licenses < 1:");
        CorporateTool zeroLic = new CorporateTool("Zero", 200.0, "BASIC", 0);
        System.out.printf("Zero-license price (should be 200.0): %.2f%n",
                zeroLic.calculateBillingPrice());

        System.out.println("\n[Test 5] Unknown tariff:");
        PersonalTool goldTool = new PersonalTool("Gold", 100.0, "GOLD");
        System.out.printf("Gold tool price (falls back to 1.0): %.2f%n",//%n new line
                goldTool.calculateBillingPrice());

        ToolBilling.printGlobalStats();
    }
    //%s -> string printf("%s", "Ali")
    //%n -> new line
    //%d -> integer printf("%d", 25)
    //%f -> float
    //%c ->char
    //%.f .>"%.2f", 3.14159265);=3.14
    //protected ->نفس الكلاس + الأبناء + نفس الحزمة
    //==============================================================
    //1-extends تعني: "أنا ابن هذا الكلاس، أرث كل شيء منه".Dog هو نوع من Animal
    //
    //Dog يرث كل ما في Animal
    //
    //Dog يمكنه إضافة أشياء جديدة
    //
    //Dog يمكنه إعادة تعريف الميثودز
    //=========================================================================
    // 2-implements  تعني: "أنا أوعد أن أنفذ كل ما في هذه الواجهة".EmailNotification يوقّع عقداً مع NotificationService
    //
    //يلتزم بتوفير كل الميثودز الموجودة في الواجهة
    //
    //لو نسيت ميثوداً، المترجم سيرفض الكود
}
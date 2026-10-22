    package billing;

    import model.Tool;//نستورد كلاس تول من مجلد موديل.
    import service.NotificationService;//نستورد الواجهة نوتيفيكيشن سيرفس من مجلد سيرفس
    //«Это сердце проекта. Он принимает сервис уведомлений через конструктор. Он не знает, Email это или SMS.
    // Он знает только, что это сервис уведомлений. Это называется внедрением зависимостей.»
    public class ToolBilling {//نعرّف كلاساً اسمه تول بيلينغ. هذا هو الكلاس الذي يدير الفواتير والإشعارات.

        private final NotificationService notificationService;//متغير خاص لا يتغير. نوعه هو الواجهة وليس كلاساً معيناً. هذا هو جوهر الحقن.
        //لا يعرف نوع الرسالة هل ايميل او اس ام اس


        private static int totalOrdersCount = 0;
        private static double totalRevenue = 0.0;
        //كونستراكتور. يستقبل خدمة إشعارات. هذا هو الحقن عبر الكونستراكتور.
        public ToolBilling(NotificationService notificationService) {
            if (notificationService == null) {
                throw new IllegalArgumentException("NotificationService cannot be null.");
            }
            this.notificationService = notificationService;
        }

        public void processPayment(Tool tool, String contact, double finalPrice) {
            if (tool == null) {
                throw new IllegalArgumentException("Tool cannot be null.");
            }
            if (finalPrice < 0) {
                throw new IllegalArgumentException("Final price cannot be negative.");
            }

            totalOrdersCount++;
            totalRevenue += finalPrice;

            System.out.printf("Rental registered: %s | Tariff: %s | Final price: %.2f%n",
                    tool.getName(), tool.getTariff(), finalPrice);

            String message = String.format(
                    "Rental payment of %.2f received for '%s' (tariff: %s).",//رقم عشري بمنزلتين
                    finalPrice, tool.getName(), tool.getTariff());

            notificationService.sendNotification(message, contact);
        }

        public static int getTotalOrdersCount() {
            return totalOrdersCount;
        }
        public static double getTotalRevenue() {
            return totalRevenue;
        }

        public static void printGlobalStats() {
            System.out.println();
            System.out.println("========== GLOBAL RENTAL STATISTICS ==========");
            System.out.println("Total orders : " + totalOrdersCount);
            System.out.printf("Total revenue: %.2f%n", totalRevenue);
            System.out.println("==============================================");
        }
    }
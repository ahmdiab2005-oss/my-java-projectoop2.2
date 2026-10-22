package service.impl;//الملف موجود داخل مجلد سيرفس ثم مجلد فرعي اسمه إمبل.

import service.NotificationService;//نستورد الواجهة التي سننفذها من المجلد الأعلى

public class EmailNotification implements NotificationService {// implements تعني أن هذا الكلاس ينفذ عقد الواجهة نوتيفيكيشن سيرفس. أي أنه ملزم بتوفير ميثود سيند نوتيفيكيشن.

    @Override// تعني أننا نعيد تعريف ميثود موجودة في الأب أو الواجهة. تستقبل الرسالة والمستقبل.
    public void sendNotification(String message, String recipient) {
        if (recipient == null || recipient.isBlank()) {
            System.out.println("[WARN] Email recipient is empty. Notification skipped.");
            return;
        }
        if (!recipient.contains("@") || !recipient.contains(".")) {
            System.out.println("[WARN] Invalid email address: '" + recipient
                    + "'. Must contain '@' and '.'. Notification skipped.");
            return;
        }

        System.out.printf("[EMAIL] На адрес %s отправлено письмо: %s%n",
                recipient, message);
    }
}
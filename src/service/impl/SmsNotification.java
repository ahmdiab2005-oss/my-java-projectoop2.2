package service.impl;

import service.NotificationService;

public class SmsNotification implements NotificationService {

    private static final String PREFIX_PLUS7 = "+7";
    private static final String PREFIX_8 = "8";
    private static final int MIN_LENGTH = 11;

    @Override
    public void sendNotification(String message, String recipient) {
        if (recipient == null || recipient.isBlank()) {
            System.out.println("[WARN] SMS recipient is empty. Notification skipped.");
            return;
        }

        boolean validPrefix = recipient.startsWith(PREFIX_PLUS7)
                || recipient.startsWith(PREFIX_8);
        boolean validLength = recipient.length() >= MIN_LENGTH;

        if (!validPrefix || !validLength) {
            System.out.println("[WARN] Invalid SMS number: '" + recipient
                    + "'. Must start with '+7' or '8' and have at least "
                    + MIN_LENGTH + " characters. Notification skipped.");
            return;
        }

        System.out.printf("[SMS] На номер %s отправлено сообщение: %s%n",
                recipient, message);
    }
}
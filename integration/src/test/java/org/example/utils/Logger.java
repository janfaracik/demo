package org.example.utils;

public class Logger {

    public static void info(String message, Object... args) {
        // Trim each argument and replace "{}" with "%s"
        Object[] trimmedArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String) {
                trimmedArgs[i] = ((String) args[i]).trim();
            } else {
                trimmedArgs[i] = args[i];
            }
        }

        // Format the message using String.format
        String formattedMessage = String.format(message.replace("{}", "%s"), trimmedArgs);

        // Print the formatted message to the console
        System.out.println(formattedMessage);
    }
}

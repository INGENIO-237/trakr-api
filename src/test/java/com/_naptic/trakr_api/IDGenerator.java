package com._naptic.trakr_api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public abstract class IDGenerator {
    public static String generate(String prefix) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        int nextInt = ThreadLocalRandom.current().nextInt();

        return "%s.%s.%d".formatted(prefix, timestamp, nextInt);
    }
}

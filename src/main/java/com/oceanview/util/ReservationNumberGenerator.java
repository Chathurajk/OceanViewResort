package com.oceanview.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
public class ReservationNumberGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1);
    public static synchronized String generate() {
        String d = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return "OVR-" + d + "-" + String.format("%04d", counter.getAndIncrement());
    }
}

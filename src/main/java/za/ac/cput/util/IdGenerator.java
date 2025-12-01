package za.ac.cput.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    // In a real application, this map would be initialized by reading the last ID
    // from the database for each entity type (ST, SF, AD) on application startup.
    // For this example, we'll start the count at 1.
    private final AtomicLong studentCounter = new AtomicLong(0);
    private final AtomicLong staffCounter = new AtomicLong(0);
    private final AtomicLong adminCounter = new AtomicLong(0);

    // This method needs to be synchronized to prevent race conditions
    // when multiple users try to create a new entity at the same time.
    public synchronized String generateNextId(String prefix) {
        AtomicLong counter;
        switch (prefix) {
            case "ST":
                counter = studentCounter;
                break;
            case "SF":
                counter = staffCounter;
                break;
            case "AD":
                counter = adminCounter;
                break;
            default:
                throw new IllegalArgumentException("Invalid ID prefix: " + prefix);
        }

        long nextValue = counter.incrementAndGet();
        // Format the ID as Prefix + 4-digit number (e.g., ST0001)
        return String.format("%s%04d", prefix, nextValue);
    }
    // Optional: Methods to initialize counters from database
    public void initializeStudentCounter(long initialValue) {
        studentCounter.set(initialValue);
    }

    public void initializeStaffCounter(long initialValue) {
        staffCounter.set(initialValue);
    }

    public void initializeAdminCounter(long initialValue) {
        adminCounter.set(initialValue);
    }
}

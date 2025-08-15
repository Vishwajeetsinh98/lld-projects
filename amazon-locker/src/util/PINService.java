package util;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PINService {
    private static final int PIN_MIN = 100_000;
    private static final int PIN_MAX = 999_999; // inclusive
    private static final long PIN_VALIDITY_MILLIS = 3 * 24 * 60 * 1000; // 3 days

    private final SecureRandom secureRandom;
    private final Set<Integer> activePINs;
    private final Map<Integer, Long> pinExpiryMap;

    private PINService() {
        secureRandom = new SecureRandom();
        activePINs = ConcurrentHashMap.newKeySet();
        pinExpiryMap = new ConcurrentHashMap<>();
    }

    public int getOtp() {
        cleanupExpiredPINs();
        int pin;
        do {
            pin = secureRandom.nextInt((PIN_MAX - PIN_MIN) + 1) + PIN_MIN;
        } while (!activePINs.add(pin)); // loop until a unique PIN is added

        pinExpiryMap.put(pin, Instant.now().toEpochMilli() + PIN_VALIDITY_MILLIS);
        return pin;
    }

    public boolean validateOtp(int pin) {
        cleanupExpiredPINs();
        return activePINs.contains(pin);
    }

    public void invalidateOtp(int pin) {
        activePINs.remove(pin);
        pinExpiryMap.remove(pin);
    }

    private void cleanupExpiredPINs() {
        long now = Instant.now().toEpochMilli();
        pinExpiryMap.forEach((pin, expiry) -> {
            if (expiry <= now) {
                activePINs.remove(pin);
                pinExpiryMap.remove(pin);
            }
        });
    }

    // Singleton:
    private static class Holder {
        private static final PINService INSTANCE = new PINService();
    }

    public static PINService getInstance() {
        return Holder.INSTANCE;
    }
}

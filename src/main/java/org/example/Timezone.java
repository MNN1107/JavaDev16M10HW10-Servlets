package org.example;

import java.time.zone.ZoneRulesException;
import java.util.TimeZone;

public class Timezone {
    public static void validateTimezone(String timezone) throws IllegalArgumentException, ZoneRulesException {

        String[] availableTimezones = TimeZone.getAvailableIDs();
        boolean isValid = false;
        for (String availableTimezone : availableTimezones) {
            if (availableTimezone.equals(timezone)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new IllegalArgumentException("Invalid timezone: " + timezone);
        }
    }
}

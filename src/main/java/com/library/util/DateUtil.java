package com.library.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

public class DateUtil {
    public static LocalDate toLocalDate(Long time) {
        return Optional.ofNullable(time)
                .map(i -> new Timestamp(i).toLocalDateTime().toLocalDate())
                .orElse(null);
    }
}

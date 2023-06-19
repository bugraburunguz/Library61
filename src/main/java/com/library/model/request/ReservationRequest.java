package com.library.model.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationRequest {
    private Long userId;
    private Long bookId;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate rentalStartDate;
    @DateTimeFormat(pattern = "yyyy.MM.dd")
    private LocalDate rentalEndDate;
}
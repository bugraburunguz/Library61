package com.library.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationResponse {
    private Long id;
    private String bookTitle;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
}
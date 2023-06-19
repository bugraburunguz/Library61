package com.library.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private List<Reservation> reservations;

    @Getter
    @Setter
    public static class Reservation {
        private Long reservationId;
        private String bookName;
        private LocalDate rentalStartDate;
        private LocalDate rentalEndDate;
    }
}
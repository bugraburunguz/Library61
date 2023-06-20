package com.library.controller;

import com.library.model.request.ReservationRequest;
import com.library.model.response.ReservationResponse;
import com.library.persistance.jpa.entity.ReservationEntity;
import com.library.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public void createReservation(@RequestBody ReservationRequest request) {
        reservationService.createReservation(request);
    }


    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
    }

    @GetMapping("/user/{userId}")
    public List<ReservationResponse> getReservationsByUserId(@PathVariable Long userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @PutMapping("/{id}")
    public ReservationEntity updateReservationDates(@PathVariable Long id, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return reservationService.updateReservationDates(id, startDate, endDate);
    }
}

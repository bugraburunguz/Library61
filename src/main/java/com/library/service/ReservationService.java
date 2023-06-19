package com.library.service;

import com.library.advice.exceptions.BookNotFoundException;
import com.library.advice.exceptions.UserNotFoundException;
import com.library.converter.ReservationConverter;
import com.library.model.request.ReservationRequest;
import com.library.model.response.ReservationResponse;
import com.library.persistance.jpa.entity.BookEntity;
import com.library.persistance.jpa.entity.ReservationEntity;
import com.library.persistance.jpa.entity.UserEntity;
import com.library.persistance.jpa.repository.BookRepository;
import com.library.persistance.jpa.repository.ReservationRepository;
import com.library.persistance.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public void createReservation(ReservationRequest request) {
        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(UserNotFoundException::new);

        BookEntity book = bookRepository.findById(request.getBookId())
                .orElseThrow(BookNotFoundException::new);

        if (!book.isAvailability()) {
            throw new IllegalStateException("Book with ID " + request.getBookId() + " is not available");
        }
        long daysBetween = ChronoUnit.DAYS.between(request.getRentalEndDate(), request.getRentalStartDate());
        if (daysBetween > 14) {
            throw new IllegalArgumentException("The reservation period cannot exceed 14 days");
        }

        ReservationEntity reservation = new ReservationEntity();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setRentalStartDate(request.getRentalStartDate());
        reservation.setRentalEndDate(request.getRentalEndDate());

        book.setAvailability(false);
        bookRepository.save(book);
        reservationRepository.save(reservation);
    }


    public void cancelReservation(Long reservationId) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found"));

        BookEntity book = reservation.getBook();
        book.setAvailability(true);
        bookRepository.save(book);

        reservationRepository.deleteById(reservationId);
    }

    public List<ReservationResponse> getReservationsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + userId + " not found"));

        return ReservationConverter.convertToReservationResponseList(user.getReservations());
    }

    public ReservationEntity updateReservationDates(Long reservationId, LocalDate newStartDate, LocalDate newEndDate) {
        ReservationEntity reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Reservation with ID " + reservationId + " not found"));

        long daysBetween = ChronoUnit.DAYS.between(newStartDate, newEndDate);
        if (daysBetween > 14) {
            throw new IllegalArgumentException("The reservation period cannot exceed 14 days");
        }

        reservation.setRentalStartDate(newStartDate);
        reservation.setRentalEndDate(newEndDate);
        return reservationRepository.save(reservation);
    }
}

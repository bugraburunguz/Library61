package com.library.converter;

import com.library.model.response.ReservationResponse;
import com.library.persistance.jpa.entity.ReservationEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReservationConverter {

    public static ReservationResponse convertToReservationResponse(ReservationEntity reservationEntity) {
        ReservationResponse response = new ReservationResponse();
        response.setId(reservationEntity.getId());
        response.setBookTitle(reservationEntity.getBook().getTitle());
        response.setRentalStartDate(reservationEntity.getRentalStartDate());
        response.setRentalEndDate(reservationEntity.getRentalEndDate());
        return response;

    }

    public static List<ReservationResponse> convertToReservationResponseList(Set<ReservationEntity> reservationEntities) {
        return reservationEntities.stream()
                .map(ReservationConverter::convertToReservationResponse)
                .collect(Collectors.toList());
    }
}

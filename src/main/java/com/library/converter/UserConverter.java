package com.library.converter;

import com.library.model.request.UserRequest;
import com.library.model.response.UserResponse;
import com.library.persistance.jpa.entity.UserEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {
    public static UserRequest convertToUserRequest(UserEntity userEntity) {
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(userEntity.getUsername());
        userRequest.setPassword(userEntity.getPassword());
        userRequest.setEmail(userEntity.getEmail());
        userRequest.setPhoneNumber(userEntity.getPhoneNumber());
        return userRequest;
    }

    public static UserResponse convertToUserResponse(UserEntity userEntity) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(userEntity.getUsername());
        userResponse.setEmail(userEntity.getEmail());
        userResponse.setPhoneNumber(userEntity.getPhoneNumber());
        userResponse.setReservations(userEntity.getReservations().stream()
                .map(reservation -> {
                    UserResponse.Reservation reservationResponse = new UserResponse.Reservation();
                    reservationResponse.setBookName(reservation.getBook().getTitle());
                    reservationResponse.setRentalStartDate(reservation.getRentalStartDate());
                    reservationResponse.setRentalEndDate(reservation.getRentalEndDate());
                    return reservationResponse;
                })
                .collect(Collectors.toList()));
        return userResponse;
    }

    public static List<UserResponse> convertToUserResponseList(List<UserEntity> userEntities) {
        return userEntities.stream()
                .map(UserConverter::convertToUserResponse)
                .collect(Collectors.toList());
    }
}

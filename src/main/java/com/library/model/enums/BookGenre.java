package com.library.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookGenre {
    NOVEL("Novel"),
    CLASSIC_LITERATURE("Classic_Literature"),
    DETECTIVE_FICTION("Detective_Fiction"),
    SCIENCE_FICTION("Science_Fiction"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    ADVENTURE("Adventure"),
    HISTORY("History"),
    AUTOBIOGRAPHY("Autobiography"),
    BIOGRAPHY("Biography"),
    PHILOSOPHY("Philosophy"),
    PSYCHOLOGY("Psychology"),
    BUSINESS_AND_LEADERSHIP("Business_and_Leadership"),
    POLITICS("Politics"),
    SCIENCE("Science"),
    POETRY("Poetry"),
    DRAMA("Drama"),
    TRAGEDY("Tragedy"),
    COMEDY("Comedy"),
    TRAVEL_WRITING("Travel_Writing"),
    CHILDREN_AND_YOUNG_ADULT_LITERATURE("Children_And_Young_Adult_Literature"),
    LOVE("Love"),
    MYSTERY("Mystery");

    private String genreName;
}


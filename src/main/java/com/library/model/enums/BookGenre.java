package com.library.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookGenre {
    NOVEL(0),
    CLASSIC_LITERATURE(1),
    DETECTIVE_FICTION(2),
    SCIENCE_FICTION(3),
    FANTASY(4),
    HORROR(5),
    ADVENTURE(6),
    HISTORY(7),
    AUTOBIOGRAPHY(8),
    BIOGRAPHY(9),
    PHILOSOPHY(10),
    PSYCHOLOGY(11),
    BUSINESS_AND_LEADERSHIP(12),
    POLITICS(13),
    SCIENCE(14),
    POETRY(15),
    DRAMA(16),
    TRAGEDY(17),
    COMEDY(18),
    TRAVEL_WRITING(19),
    CHILDREN_AND_YOUNG_ADULT_LITERATURE(20),
    LOVE(21);

    private int genreNumber;
}


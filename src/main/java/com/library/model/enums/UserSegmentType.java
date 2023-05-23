package com.library.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserSegmentType {
        USER(1),
        ADMIN(2);

        private int status;
}

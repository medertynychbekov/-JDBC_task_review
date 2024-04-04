package org.peaksoft.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Car {

    Long id;
    String model;
    LocalDate yearOfRelease;
    String color;

    public Car(String model, LocalDate yearOfRelease, String color) {
        this.model = model;
        this.yearOfRelease = yearOfRelease;
        this.color = color;
    }
}

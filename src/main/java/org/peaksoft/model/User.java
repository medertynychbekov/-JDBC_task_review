package org.peaksoft.model;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    String name;
    String lastName;
    Byte age;
    Long carId;

    public User(String name, String lastName, Byte age, Long carId) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.carId = carId;
    }

}
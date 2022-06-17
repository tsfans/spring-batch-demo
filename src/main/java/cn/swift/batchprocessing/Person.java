package cn.swift.batchprocessing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    private int id;

    private String lastName;

    private String firstName;

}

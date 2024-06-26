package com.one_api_for_all.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeTypeA {
    private int id;
    private String name;
    private String position;
    private int yearsOfExperience;
    private int PTOs;

    public EmployeeTypeA(int id, String name, String position, int yearsOfExperience, int PTOs) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.yearsOfExperience = yearsOfExperience;
        this.PTOs = PTOs;
    }
}

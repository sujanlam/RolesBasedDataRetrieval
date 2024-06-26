package com.one_api_for_all.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
        private int id;
        private String name;
        private String position;
        private int yearsOfExperience;
        private int PTOs;
        private boolean leaveOfAbsence;
        private String department;
        private double salary;
}

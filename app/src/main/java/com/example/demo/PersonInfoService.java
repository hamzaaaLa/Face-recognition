package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class PersonInfoService {

    public PersonInfo getPersonInfo(byte[] imageBytes) {
        // Implement logic to process imageBytes and extract person information
        // Use your existing face detection and recognition logic here

        // Example: Placeholder logic
        String nom = "John";
        String prenom = "Doe";
        int age = 30;

        PersonInfo personInfo = new PersonInfo();
        personInfo.setNom(nom);
        personInfo.setPrenom(prenom);
        personInfo.setAge(age);

        return personInfo;
    }
}


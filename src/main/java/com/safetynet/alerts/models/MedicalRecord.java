package com.safetynet.alerts.models;

import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;

import java.util.List;

public class MedicalRecord {
    private String birthdate;
    private List<Medication> medications;
    private List<Allergy> allergies;

    public MedicalRecord() {
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
}

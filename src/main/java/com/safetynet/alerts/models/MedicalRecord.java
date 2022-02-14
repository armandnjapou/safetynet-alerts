package com.safetynet.alerts.models;

import com.safetynet.alerts.models.valueobjects.Allergy;
import com.safetynet.alerts.models.valueobjects.Medication;

import java.util.List;
import java.util.Objects;

public class MedicalRecord {
    private String firstName;
    private String lastName;
    private String birthdate;
    private List<Medication> medications;
    private List<Allergy> allergies;

    public MedicalRecord() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicalRecord that = (MedicalRecord) o;

        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(birthdate, that.birthdate)) return false;
        if (!Objects.equals(medications, that.medications)) return false;
        return Objects.equals(allergies, that.allergies);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        result = 31 * result + (medications != null ? medications.hashCode() : 0);
        result = 31 * result + (allergies != null ? allergies.hashCode() : 0);
        return result;
    }
}

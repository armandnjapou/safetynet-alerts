package com.safetynet.alerts.utils;

import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

class ModelMapperImplTest {

    private static ModelMapper modelMapper;
    private Throwable throwable;
    private JSONObject json;

    @BeforeAll
    private static void setUp() {
        modelMapper = new ModelMapperImpl();
    }

    @Test
    public void should_return_json_object_of_size_3_when_map_data_from_local_json() throws IOException, ParseException {
        json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/data.json");
        int expectedSize = 3;
        Assertions.assertEquals(expectedSize, json.size());
    }

    @Test
    public void should_throw_io_exception_when_map_data_from_unexisting_file() {
        try {
            json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/file.json");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof IOException);
    }

    @Test
    public void should_throw_parse_exception_when_map_data_from_non_json_file() {
        try {
            json = modelMapper.mapDatafromJsonFileToJsonObject("src/main/java/com/safetynet/alerts/SafetyNetAlertsApplication.java");
        } catch (Throwable throwable) {
            this.throwable = throwable;
        }
        Assertions.assertTrue(throwable instanceof ParseException);
    }

    @Nested
    class ModelMapperImplNestedTest {
        @BeforeEach
        public void init() throws IOException, ParseException {
            json = modelMapper.mapDatafromJsonFileToJsonObject("src/test/resources/data.json");
        }

        @Test
        public void should_return_list_of_23_persons_when_map_person_from_json() throws IOException, ParseException {
            List<Person> persons =  modelMapper.mapPersonsFromJson((JSONArray) json.get("persons"));
            Assertions.assertEquals(23, persons.size());
        }

        @Test
        public void should_return_true_when_verify_if_all_mapped_persons_are_not_null() throws IOException, ParseException {
            List<Person> persons =  modelMapper.mapPersonsFromJson((JSONArray) json.get("persons"));
            Assertions.assertTrue(persons.stream().allMatch(Objects::nonNull));
        }

        @Test
        public void should_return_true_when_verify_if_all_mapped_persons_contains_non_null_values() throws IOException, ParseException {
            List<Person> persons =  modelMapper.mapPersonsFromJson((JSONArray) json.get("persons"));
            Assertions.assertTrue(persons.stream().allMatch(person -> person.getFirstName() != null && person.getLastName() != null
                    && person.getAddress() != null && person.getZip() != null && person.getCity() != null && person.getPhone() != null && person.getEmail() != null ));
        }

        @Test
        public void should_return_list_of_23_medical_records_when_map_medical_records_from_json() {
            List<MedicalRecord> medicalRecords =  modelMapper.mapMedicalRecordsFromJson((JSONArray) json.get("medicalrecords"));
            Assertions.assertEquals(23, medicalRecords.size());
        }

        @Test
        public void should_return_true_when_verify_if_all_mapped_medical_records_are_not_null() {
            List<MedicalRecord> medicalRecords = modelMapper.mapMedicalRecordsFromJson((JSONArray) json.get("medicalrecords"));
            Assertions.assertTrue(medicalRecords.stream().allMatch(Objects::nonNull));
        }

        @Test
        public void should_return_true_when_verify_if_all_mapped_medical_records_contains_non_null_values() {
            List<MedicalRecord> medicalRecords = modelMapper.mapMedicalRecordsFromJson((JSONArray) json.get("medicalrecords"));
            Assertions.assertTrue(medicalRecords.stream().allMatch(medicalRecord -> medicalRecord.getFirstName() != null && medicalRecord.getLastName() != null
            && medicalRecord.getBirthdate() != null && medicalRecord.getMedications() != null && medicalRecord.getAllergies() != null));
        }

        @Test
        public void should_throw_null_pointer_exception_when_map_medical_record_with_non_existing_parameter() {
            try {
                List<MedicalRecord> medicalRecords = modelMapper.mapMedicalRecordsFromJson((JSONArray) json.get("medicalrecord"));
            } catch (Throwable t) {
                throwable = t;
            }
            Assertions.assertTrue(throwable instanceof NullPointerException);
        }

        @Test
        public void should_throw_null_pointer_exception_when_map_medical_record_with_wrong_parameter() {
            try {
                List<MedicalRecord> medicalRecords = modelMapper.mapMedicalRecordsFromJson((JSONArray) json.get("firestations"));
            } catch (Throwable t) {
                throwable = t;
            }
            Assertions.assertTrue(throwable instanceof NullPointerException);
        }
    }
}
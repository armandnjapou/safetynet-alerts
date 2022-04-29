package com.safetynet.alerts.services;

import com.safetynet.alerts.data.firestation.FireStationData;
import com.safetynet.alerts.data.firestation.FireStationDataImpl;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordData;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordDataImpl;
import com.safetynet.alerts.data.person.PersonData;
import com.safetynet.alerts.data.person.PersonDataImpl;
import com.safetynet.alerts.models.FireStation;
import com.safetynet.alerts.models.MedicalRecord;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GeneralServiceImpl implements GeneralService {

    private static final Logger LOGGER = LogManager.getLogger(GeneralServiceImpl.class);

    private final PersonData personData = new PersonDataImpl();
    private final MedicalRecordData medicalRecordData = new MedicalRecordDataImpl();
    private final FireStationData fireStationData = new FireStationDataImpl();

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject getChildrenFromAddress(String address) throws IOException, ParseException {
        String methodName = "getChildrenFromAddress";
        LOGGER.info("Start method : {}", methodName);

        JSONObject result = new JSONObject();
        JSONArray children = new JSONArray();
        JSONArray otherMember = new JSONArray();
        List<Person> people = personData.findAll();
        people = people.stream()
                .filter(p -> p.getAddress().equals(address))
                .toList();
        if (people.size() != 0) {
            for (Person person : people) {
                MedicalRecord medicalRecord = medicalRecordData.findByFirstNameAndLastName(person.getFirstName(), person.getLastName());
                int age = Utils.computeAgeFromBirthdate(medicalRecord.getBirthdate());
                JSONObject object = new JSONObject();
                object.put("firstName", person.getFirstName());
                object.put("lastName", person.getLastName());
                object.put("age", age);
                if (age < 18) {
                    children.add(object);
                } else {
                    otherMember.add(object);
                }
            }
            result.put("children", children);
            result.put("otherMembers", otherMember);
        } else result = null;
        LOGGER.info("End method : {}", methodName);
        return result;
    }

    @Override
    public List<String> getPhoneNumbersByStationNumber(int stationNumber) throws IOException, ParseException {
        String methodName = "getPhoneNumbersByStationNumber";
        LOGGER.info("Start method : {}", methodName);
        List<String> result = new ArrayList<>();
        FireStation fireStation = fireStationData.findByStationNumber(stationNumber);
        if (fireStation != null) {
            List<Person> people = personData.findAll();
            for (Person p : people) {
                if (p.getAddress().equals(fireStation.getAddress())) {
                    result.add(p.getPhone());
                }
            }
        } else {
            LOGGER.error("No fire station found with station number : {}", stationNumber);
            return null;
        }
        LOGGER.info("End method : {}", methodName);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONArray getFloodByStations(List<Integer> stations) throws IOException, ParseException {
        String methodName = "getFloodByStations";
        LOGGER.info("Start method : {}", methodName);
        JSONArray result = new JSONArray();
        List<Person> people = personData.findAll();
        for (Integer i : stations) {
            FireStation f = fireStationData.findByStationNumber(i);
            if (f != null) {
                JSONObject home = new JSONObject();
                List<Person> personList = people.stream()
                        .filter(p -> p.getAddress().equals(f.getAddress()))
                        .toList();
                JSONArray personsInHome = new JSONArray();
                if (personList.size() > 0) {
                    for (Person p : personList) {
                        MedicalRecord medicalRecord = medicalRecordData.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                        int age = Utils.computeAgeFromBirthdate(medicalRecord.getBirthdate());
                        JSONObject object = new JSONObject();
                        object.put("firstName", p.getFirstName());
                        object.put("lastName", p.getLastName());
                        object.put("phone", p.getPhone());
                        object.put("age", age);
                        object.put("medications", medicalRecord.getMedications());
                        object.put("allergies", medicalRecord.getAllergies());
                        personsInHome.add(object);
                    }
                    home.put("station " + i, personsInHome);
                    result.add(home);
                } else LOGGER.error("No person found for address : {}", f.getAddress());
            } else LOGGER.error("No fire station found for station number : {}", i);
        }
        LOGGER.info("End method : {}", methodName);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject getPersonInfo(String firstName, String lastName) throws IOException, ParseException {
        String methodName = "getPersonInfo";
        LOGGER.info("Start method : {}", methodName);
        JSONObject result = new JSONObject();
        List<Person> people = personData.findAll().stream()
                .filter(person -> person.getFirstName().equals(firstName) && person.getLastName().equals(lastName))
                .toList();
        if (!people.isEmpty()) {
            JSONArray persons = new JSONArray();
            for (Person p : people) {
                JSONObject object = new JSONObject();
                MedicalRecord medicalRecord = medicalRecordData.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
                int age = Utils.computeAgeFromBirthdate(medicalRecord.getBirthdate());
                object.put("firstName", p.getFirstName());
                object.put("lastName", p.getLastName());
                object.put("address", p.getAddress());
                object.put("email", p.getEmail());
                object.put("age", age);
                object.put("medications", medicalRecord.getMedications());
                object.put("allergies", medicalRecord.getAllergies());
                persons.add(object);
            }
            result.put("persons", persons);
        } else LOGGER.error("No person found with firstname : {} and lastname : {}", firstName, lastName);
        LOGGER.info("End method : {}", methodName);
        return result;
    }

    @Override
    public List<String> getCommunityEmail(String city) throws IOException, ParseException {
        String methodName = "getCommunityEmail";
        LOGGER.info("Start method : {}", methodName);
        List<String> result = new ArrayList<>();
        List<Person> people = personData.findAll().stream()
                .filter(p -> p.getCity().equals(city))
                .toList();
        if (!people.isEmpty()) {
            for (Person p : people) {
                result.add(p.getEmail());
            }
        }
        LOGGER.info("End method : {}", methodName);
        return result;
    }
}

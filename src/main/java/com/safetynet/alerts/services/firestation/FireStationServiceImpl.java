package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.data.firestation.FireStationData;
import com.safetynet.alerts.data.firestation.FireStationDataImpl;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordData;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordDataImpl;
import com.safetynet.alerts.data.person.PersonData;
import com.safetynet.alerts.data.person.PersonDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
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
public class FireStationServiceImpl implements FireStationService{

    private final FireStationData fireStationData = new FireStationDataImpl();
    private final PersonData personData = new PersonDataImpl();
    private final MedicalRecordData medicalRecordData = new MedicalRecordDataImpl();
    private static final Logger LOGGER = LogManager.getLogger(FireStationDataImpl.class);

    @Override
    public List<FireStation> findAll() throws IOException, ParseException {
        return fireStationData.findAll();
    }

    @Override
    public void add(FireStation fireStation) throws AlreadyExistingException {
        fireStationData.add(fireStation);
    }

    @Override
    public void update(FireStation fireStation) {
        fireStationData.update(fireStation);
    }

    @Override
    public void delete(FireStation fireStation) {
        fireStationData.delete(fireStation);
    }

    @Override
    @SuppressWarnings("unchecked")
    public JSONObject getPersonsCoveredByFireStation(int id) throws IOException, ParseException {
        String methodName = "getPersonsCoveredByFireStation";
        LOGGER.info("Start method : {}", methodName);
        JSONObject result = new JSONObject();
        JSONArray resultArray = new JSONArray();
        int adultCounter = 0, childCounter = 0;

        List<FireStation> fireStations = fireStationData.findAll()
                .stream()
                .filter(f -> f.getStation() == id)
                .toList();
        List<Person> personList = new ArrayList<>();
        for (FireStation f : fireStations) {
            List<Person> persons = personData.findAll().stream()
                    .filter(p -> p.getAddress().equals(f.getAddress()))
                    .toList();
            personList.addAll(persons);
        }

        for (Person p : personList) {
            JSONObject object = new JSONObject();
            object.put("firstName", p.getFirstName());
            object.put("lastName", p.getLastName());
            object.put("address", p.getAddress());
            object.put("phone", p.getPhone());
            resultArray.add(object);

            MedicalRecord m = medicalRecordData.findByFirstNameAndLastName(p.getFirstName(), p.getLastName());
            if (Utils.computeAgeFromBirthdate(m.getBirthdate()) > 18) {
                adultCounter++;
            } else childCounter++;
        }
        result.put("persons", resultArray);
        result.put("adultCounter", adultCounter);
        result.put("childCounter", childCounter);
        LOGGER.info("End method : {}", methodName);
        return result;
    }
}

package com.safetynet.alerts.data.person;

import com.safetynet.alerts.data.ModelReader;
import com.safetynet.alerts.data.ModelReaderImpl;
import com.safetynet.alerts.models.Person;
import com.safetynet.alerts.utils.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonDataImpl implements PersonData {

    private static final ModelReader modelReader = new ModelReaderImpl();

    @Override
    public List<Person> findAll() throws IOException, ParseException {
        JSONObject json = modelReader.readDatafromJsonFile(Constants.JSON_PATH);
        JSONArray persons = (JSONArray) json.get(Constants.PERSONS);
        List<Person> personList = new ArrayList<>();
        for(Object object : persons){
            JSONObject personObject = (JSONObject) object;
            Person person = new Person();
            person.setFirstName(personObject.get(Constants.FIRSTNAME).toString());
            person.setLastName(personObject.get(Constants.LASTNAME).toString());
            person.setAddress(personObject.get(Constants.ADDRESS).toString());
            person.setCity(personObject.get(Constants.CITY).toString());
            person.setZip(personObject.get(Constants.ZIP).toString());
            person.setPhone(personObject.get(Constants.PHONE).toString());
            person.setEmail(personObject.get(Constants.EMAIL).toString());
            personList.add(person);
        }
        return personList;
    }
}

package com.safetynet.alerts.data.person;

import com.safetynet.alerts.data.ModelReader;
import com.safetynet.alerts.data.ModelReaderImpl;
import com.safetynet.alerts.models.Person;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonData implements IPersonData{

    private static final ModelReader modelReader = new ModelReaderImpl();

    @Override
    public List<Person> findAll() throws IOException, ParseException {
        JSONObject json = modelReader.readDatafromJsonFile("src/main/resources/static/data.json");
        JSONArray persons = (JSONArray) json.get("persons");
        List<Person> personList = new ArrayList<>();
        for(Object object : persons){
            JSONObject personObject = (JSONObject) object;
            Person person = new Person();
            person.setFirstName(personObject.get("firstName").toString());
            person.setLastName(personObject.get("lastName").toString());
            person.setAddress(personObject.get("address").toString());
            person.setCity(personObject.get("city").toString());
            person.setZip(personObject.get("zip").toString());
            person.setPhone(personObject.get("phone").toString());
            person.setEmail(personObject.get("email").toString());
            personList.add(person);
        }
        return personList;
    }
}

package com.safetynet.alerts.services.firestation;

import com.safetynet.alerts.data.firestation.FireStationData;
import com.safetynet.alerts.data.firestation.FireStationDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.FireStation;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FireStationServiceImpl implements FireStationService{

    private final FireStationData fireStationData = new FireStationDataImpl();

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
}

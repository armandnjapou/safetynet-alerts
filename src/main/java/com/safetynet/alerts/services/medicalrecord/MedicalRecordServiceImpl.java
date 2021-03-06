package com.safetynet.alerts.services.medicalrecord;

import com.safetynet.alerts.data.medicalrecord.MedicalRecordData;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordDataImpl;
import com.safetynet.alerts.exceptions.AlreadyExistingException;
import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordData medicalRecordData = new MedicalRecordDataImpl();

    @Override
    public List<MedicalRecord> findAll() throws IOException, ParseException {
        return medicalRecordData.findAll();
    }

    @Override
    public void add(MedicalRecord medicalRecord) throws AlreadyExistingException {
        medicalRecordData.add(medicalRecord);
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        medicalRecordData.update(medicalRecord);
    }

    @Override
    public void delete(MedicalRecord medicalRecord) {
        medicalRecordData.delete(medicalRecord);
    }
}

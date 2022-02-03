package com.safetynet.alerts.services.medicalrecord;

import com.safetynet.alerts.data.medicalrecord.MedicalRecordData;
import com.safetynet.alerts.data.medicalrecord.MedicalRecordDataImpl;
import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordData medicalRecordData = new MedicalRecordDataImpl();

    @Override
    public List<MedicalRecord> findAll() throws IOException, ParseException {
        return medicalRecordData.findAll();
    }
}

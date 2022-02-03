package com.safetynet.alerts.data.medicalrecord;

import com.safetynet.alerts.models.MedicalRecord;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public interface IMedicalRecordData {
    List<MedicalRecord> findAll() throws IOException, ParseException;
}

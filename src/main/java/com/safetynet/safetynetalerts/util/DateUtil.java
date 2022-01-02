package com.safetynet.safetynetalerts.util;

import com.safetynet.safetynetalerts.model.MedicalRecord;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class DateUtil {
	public static LocalDate convert(Date date) {
		return date.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();
	}

	public static int computeAge(MedicalRecord medicalRecord) {
		return Period.between(convert(medicalRecord.getBirthdate()), LocalDate.now()).getYears();
	}
}

package com.ftn.aukcija.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class PointsService {

	public long dayDifferenceBetweenDates(String datumPonude, String datumZahtjeva) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date firstDate = sdf.parse(datumPonude);
        Date secondDate = sdf.parse(datumZahtjeva);
        
        long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        return diff;
	}
	
	public Double points (String datumPonude, String datumZahtjeva, Double cijenaPonude, Double cijenaZahtjeva) throws ParseException {
		
		Double points = 0.0;
		long diff = dayDifferenceBetweenDates(datumPonude, datumZahtjeva);
		
		if (datumPonude.compareTo(datumZahtjeva) > 0) {       //poslije zadatog roka (lose!)
            points += diff * 10;
        }
        else if (datumPonude.compareTo(datumZahtjeva) < 0) {      //prije zadatog roka (dobro!)
            points += diff * 100;
        }
        else {                              //u zadatom roku
            points += 0;
        }
		
		double resultCijena = cijenaPonude - cijenaZahtjeva;
        if (resultCijena < 0) {
        	points += Math.abs(resultCijena) * 10;
        }
        else if (resultCijena > 0) {
        	points += Math.abs(resultCijena);
        }
        else {
        	points += 0;
        }
		
		return points;
	}
	
}

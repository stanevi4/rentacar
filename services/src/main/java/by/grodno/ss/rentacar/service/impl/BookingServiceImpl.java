package by.grodno.ss.rentacar.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BookingDao;
import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Inject
	private BookingDao bookingDao;
	
	@Override
	public Long count(BookingFilter filter) {
		return bookingDao.count(filter);
	}

	@Override
	public List<Booking> find(BookingFilter filter) {
		return bookingDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Booking booking) {
		if (booking.getId() == null) {
			bookingDao.insert(booking);
        } else {
        	bookingDao.update(booking);
        }	

	}
	@Override
	public void delete(Booking booking) {
		bookingDao.delete(booking.getId());
	}
	
	@Override
	public String convertDurationToString(Date dateFrom, Date dateTo) {
		long time = dateTo.getTime() - dateFrom.getTime();
		long timeToMinutes = TimeUnit.MILLISECONDS.toMinutes(time); 
		
		String stringTime = "";

		long days = timeToMinutes / (24 * 60);
		long hours = (timeToMinutes % (24 * 60)) / 60;
		long minutes = (timeToMinutes % (24 * 60)) % 60;
		
		String sDays;
		String sHours;
		String sMins;
		
		if (days==0){
			sDays = "";
		}else{
			sDays = (days==1) ? "1 day" : (days + " days");
		}
		
		if (hours==0){
			sHours = "";
		}else{
			sHours = (hours==1) ? "1 hour" : (hours + " hours");
		}
		
		if (minutes==0){
			sMins = "";
		}else{
			sMins = (minutes==1) ? "1 min" : (minutes + " mins");
		}

		stringTime = String.format("%1$s %2$s %3$s", sDays, sHours, sMins);
		
		return stringTime;
	}

}

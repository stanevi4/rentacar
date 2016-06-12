package by.grodno.ss.rentacar.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.BookingDao;
import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
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
			booking.setCreated(new Date());
			bookingDao.insert(booking);
			LOGGER.info("Order regirstred: {}", booking.getId());
        } else {
        	bookingDao.update(booking);
        }	

	}
	@Override
	public void delete(Booking booking) {
		LOGGER.info("Order deleted: {}", booking.getId());
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

	@Override
	public BigDecimal getTotalPrice(Date dateFrom, Date dateTo, BigDecimal pricePerHour) {
		
		long time = dateTo.getTime() - dateFrom.getTime();
		long timeToMinutes = TimeUnit.MILLISECONDS.toMinutes(time); 
		BigDecimal pricePerMinut = pricePerHour.divide(new BigDecimal("60"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal total = pricePerMinut.multiply(new BigDecimal(timeToMinutes));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP );
		
		return total;
	}

	@Override
	public BigDecimal getPricePerDay(BigDecimal pricePerHour) {
		BigDecimal pricePerDay = pricePerHour.multiply(new BigDecimal("24"));
		pricePerDay = pricePerDay.setScale(2, BigDecimal.ROUND_HALF_UP );
		return pricePerDay;
	}

	@Override
	public BigDecimal getRequiredDeposit(BigDecimal total, int percent) {
		
		BigDecimal percentTobd = new BigDecimal(percent);
		BigDecimal multiplicator = percentTobd.divide(new BigDecimal("100"), 20, BigDecimal.ROUND_HALF_UP);
		BigDecimal deposit = total.multiply(multiplicator);
		deposit = deposit.setScale(2, BigDecimal.ROUND_HALF_UP );
		return deposit;
	}

}

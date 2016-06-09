package by.grodno.ss.rentacar.service.impl;

import java.util.List;

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

}

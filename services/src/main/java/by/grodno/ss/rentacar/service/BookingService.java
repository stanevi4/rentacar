package by.grodno.ss.rentacar.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;

public interface BookingService {
	
	Long count(BookingFilter filter);

    List<Booking> find(BookingFilter filter);

    @Transactional
    void saveOrUpdate(Booking booking);

    @Transactional
    void delete(Booking booking);
    
    String convertDurationToString(Date dateFrom, Date dateTo);
    
    BigDecimal getTotalPrice(Date dateFrom, Date dateTo, BigDecimal pricePerHour);
    
    BigDecimal getPricePerDay(BigDecimal pricePerHour);
    
    BigDecimal getRequiredDeposit(BigDecimal total, int percent);
}

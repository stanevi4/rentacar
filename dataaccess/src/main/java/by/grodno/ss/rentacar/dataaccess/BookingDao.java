package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.dataaccess.filters.CarFilter;
import by.grodno.ss.rentacar.datamodel.Booking;
import by.grodno.ss.rentacar.datamodel.Car;

public interface BookingDao extends AbstractDao<Booking, Long> {

	Long count(BookingFilter filter);

    List<Booking> find(BookingFilter filter);
    
    List<Car> choose(BookingFilter filter);
}

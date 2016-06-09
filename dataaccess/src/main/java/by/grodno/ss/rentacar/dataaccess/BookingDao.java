package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.BookingFilter;
import by.grodno.ss.rentacar.datamodel.Booking;

public interface BookingDao extends AbstractDao<Booking, Long> {

	Long count(BookingFilter filter);

    List<Booking> find(BookingFilter filter);
}

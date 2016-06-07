package by.grodno.ss.rentacar.dataaccess;

import java.util.List;

import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Reason;

public interface ReasonDao extends AbstractDao<Reason, Long> {

	Long count(ReasonFilter filter);

    List<Reason> find(ReasonFilter filter);
}

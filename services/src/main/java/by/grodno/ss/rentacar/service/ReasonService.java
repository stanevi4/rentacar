package by.grodno.ss.rentacar.service;

import java.util.List;

import javax.transaction.Transactional;

import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Reason;

public interface ReasonService {

	Long count(ReasonFilter filter);

    List<Reason> find(ReasonFilter filter);

    @Transactional
    void saveOrUpdate(Reason location);

    @Transactional
    void delete(Reason location);
}

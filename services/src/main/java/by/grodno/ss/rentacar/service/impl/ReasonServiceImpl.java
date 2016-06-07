package by.grodno.ss.rentacar.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.grodno.ss.rentacar.dataaccess.ReasonDao;
import by.grodno.ss.rentacar.dataaccess.filters.ReasonFilter;
import by.grodno.ss.rentacar.datamodel.Reason;
import by.grodno.ss.rentacar.service.ReasonService;

@Service
public class ReasonServiceImpl implements ReasonService {

	@Inject
	private ReasonDao reasonDao;

	@Override
	public Long count(ReasonFilter filter) {
		return reasonDao.count(filter);
	}

	@Override
	public List<Reason> find(ReasonFilter filter) {
		return reasonDao.find(filter);
	}

	@Override
	public void saveOrUpdate(Reason reason) {
		if (reason.getId() == null) {
			reasonDao.insert(reason);
		} else {
			reasonDao.update(reason);
		}

	}

	@Override
	public void delete(Reason reason) {
		reasonDao.delete(reason.getId());

	}

}

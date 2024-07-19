package com.alphonse.bankback.services.servicesImpl;

import java.sql.Date;
import java.util.List;

import com.alphonse.bankback.dao.TraceDataRepository;
import com.alphonse.bankback.entities.TraceData;
import com.alphonse.bankback.services.ITraceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class TraceDataServiceImpl implements ITraceDataService {
	@Autowired
	private TraceDataRepository traceDataDao;
	@Override
	public TraceData save(TraceData traceData) {
		return traceDataDao.save(traceData);
	}

	@Override
	public List<TraceData> findAll() {
		return Streamable.of(traceDataDao.findAll()).toList();
	}
	
	@Override
	public List<TraceData> findByDateLessThan(Date date) {
		return traceDataDao.findByDateLessThan(date);
	}
	
	@Override
	public void deleteByDateLessThan(Date date) {
		traceDataDao.deleteByDateLessThan(date);
	}
}

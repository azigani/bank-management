package com.alphonse.bankback.services;

import com.alphonse.bankback.entities.TraceData;

import java.sql.Date;
import java.util.List;

public interface ITraceDataService {
	TraceData save(TraceData traceData);
	List<TraceData> findAll();
	List<TraceData> findByDateLessThan(Date date);
	void deleteByDateLessThan(Date date);
}

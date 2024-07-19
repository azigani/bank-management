package com.alphonse.bankback.services;

import java.io.OutputStream;
import java.util.List;

import com.alphonse.bankback.entities.TraceData;

public interface IExportService {
	void exportTraceData(List<TraceData> tracesData, OutputStream output);
}

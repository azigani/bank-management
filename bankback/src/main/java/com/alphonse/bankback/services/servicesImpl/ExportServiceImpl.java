package com.alphonse.bankback.services.servicesImpl;

import java.io.OutputStream;
import java.util.List;

import com.alphonse.bankback.entities.TraceData;
import com.alphonse.bankback.export.TraceDataExporter;
import com.alphonse.bankback.services.IExportService;
import org.springframework.stereotype.Service;

@Service
public class ExportServiceImpl implements IExportService {

	@Override
	public void exportTraceData(List<TraceData> tracesData, OutputStream output) {
		TraceDataExporter exporter = new TraceDataExporter();
		exporter.export(tracesData, output);
	}
}

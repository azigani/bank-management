package com.alphonse.bankback.controller;
import java.io.IOException;
import java.util.List;
import com.alphonse.bankback.entities.TraceData;
import com.alphonse.bankback.services.IExportService;
import com.alphonse.bankback.services.ITraceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/logs")
@PreAuthorize("hasAuthority('GESTION_LOGS')")
public class LogsController {
	@Autowired
	private ITraceDataService traceDataService;
	
	@Autowired
	private IExportService exportService;
	
	@GetMapping
	public ResponseEntity<List<TraceData>> getTracesData(){
		return ResponseEntity.ok(traceDataService.findAll());
	}
	
	@GetMapping("export")
	public void export(HttpServletResponse response){
		response.setContentType("application/octet-stream");
		List<TraceData> tracesData = traceDataService.findAll();
		try {
			exportService.exportTraceData(tracesData, (ServletOutputStream) response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

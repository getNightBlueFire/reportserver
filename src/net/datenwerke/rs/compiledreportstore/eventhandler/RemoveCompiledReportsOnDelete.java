package net.datenwerke.rs.compiledreportstore.eventhandler;

import net.datenwerke.rs.compiledreportstore.CompiledReportStoreService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventHandler;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class RemoveCompiledReportsOnDelete implements EventHandler<RemoveEntityEvent> {


	@Inject private CompiledReportStoreService service;
	
	@Override
	public void handle(RemoveEntityEvent event) {
		Report report = (Report) event.getObject();

		service.unsetForReport(report);
	}

}

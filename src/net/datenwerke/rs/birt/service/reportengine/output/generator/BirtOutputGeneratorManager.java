package net.datenwerke.rs.birt.service.reportengine.output.generator;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.birt.service.reportengine.hooks.BirtOutputGeneratorProviderHook;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BirtOutputGeneratorManager extends AbstractReportOutputGeneratorManager<BirtOutputGenerator> {

	@Inject
	public BirtOutputGeneratorManager(HookHandlerService hookHandler) {
		super(hookHandler, BirtOutputGeneratorProviderHook.class);
	}

}

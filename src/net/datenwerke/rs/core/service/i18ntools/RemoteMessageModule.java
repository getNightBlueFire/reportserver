package net.datenwerke.rs.core.service.i18ntools;

import net.datenwerke.gf.service.localization.RemoteMessageService;

import com.google.inject.AbstractModule;

public class RemoteMessageModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RemoteMessageService.class).to(RemoteMessageServiceImpl.class);
	}

}

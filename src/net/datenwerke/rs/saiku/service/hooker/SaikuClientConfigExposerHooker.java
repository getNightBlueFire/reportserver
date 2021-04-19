package net.datenwerke.rs.saiku.service.hooker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.google.inject.Inject;

import net.datenwerke.gf.service.config.ClientConfigExposerHook;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.rs.utils.config.ConfigService;

public class SaikuClientConfigExposerHooker implements ClientConfigExposerHook {

	private final ConfigService configService;
	
	@Inject
	public SaikuClientConfigExposerHooker(ConfigService configService) {
		this.configService = configService;
	}



	@Override
	public Map<String, String> exposeConfig(String identifier) {
		if(! "saiku.properties".equals(identifier))
			return null;
		
		Map<String,String> map = new HashMap<>();
		
		Configuration c = configService.getConfigFailsafe(SaikuModule.CONFIG_FILE);
		if(c instanceof XMLConfiguration){
			XMLConfiguration config = (XMLConfiguration) c;
			List<HierarchicalConfiguration> subconfigs = config.configurationsAt("saiku.settings.property");
			if(null != subconfigs){
				for(HierarchicalConfiguration property : subconfigs){
					String key = property.getString("[@key]");
					String value = property.getString("[@value]");
					
					map.put(key, value);
				}
			}
		}
		
		
		return map;
	}

}

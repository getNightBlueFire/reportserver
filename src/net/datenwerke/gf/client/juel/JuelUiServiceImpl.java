package net.datenwerke.gf.client.juel;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;

import com.google.inject.Inject;

public class JuelUiServiceImpl implements JuelUiService {

	private final JuelDao juelDao;

	@Inject
	public JuelUiServiceImpl(JuelDao juelDao) {
		super();
		this.juelDao = juelDao;
	}
	
	@Override
	public void evaluateExpression(String expression, RsAsyncCallback<JuelResultDto> callback){
		juelDao.evaluateExpression(expression, callback);
	}
}

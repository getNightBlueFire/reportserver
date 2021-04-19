package net.datenwerke.rs.dashboard.client.dashboard;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

import com.google.inject.Inject;

public class DashboardTreeManagerDao extends TreeDbManagerDao {

	@Inject
	public DashboardTreeManagerDao(DashboardTreeManagerAsync treeManager) {
		super(treeManager);
	}

	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractDashboardManagerNodeDto.newPosoMapper();
	}

}

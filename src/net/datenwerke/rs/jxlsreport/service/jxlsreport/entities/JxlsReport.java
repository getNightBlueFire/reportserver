package net.datenwerke.rs.jxlsreport.service.jxlsreport.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gf.base.service.annotations.Indexed;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.JxlsReportService;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.treedb.service.treedb.annotation.TreeDBAllowedChildren;

import org.hibernate.envers.Audited;

import com.google.inject.Injector;

@Entity
@Table(name="JXLS_REPORT")
@Audited
@Indexed
@TreeDBAllowedChildren({
	JxlsReportVariant.class
})
@GenerateDto(
	dtoPackage="net.datenwerke.rs.jxlsreport.client.jxlsreport.dto",
	createDecorator=true,
	typeDescriptionMsg=net.datenwerke.rs.jxlsreport.client.jxlsreport.locale.JxlsReportMessages.class,
	typeDescriptionKey="reportTypeName",
	icon="xls"
)
@InstanceDescription(
	msgLocation=JxlsReportMessages.class,
	objNameKey="reportTypeName",
	icon = "file"
)
public class JxlsReport extends Report {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8629111900959636920L;

	@EnclosedEntity
	@ExposeToClient
	@OneToOne(cascade={CascadeType.ALL})
	private JxlsReportFile reportFile;
	
	@ExposeToClient
	private boolean jxlsOne;

	public void setReportFile(JxlsReportFile reportFile) {
		this.reportFile = reportFile;
	}
	
	public JxlsReportFile getReportFile() {
		return reportFile;
	}

	public void setJxlsOne(boolean jxlsOne) {
		this.jxlsOne = jxlsOne;
	}
	public boolean isJxlsOne() {
		return jxlsOne;
	}
	
	@Override
	protected Report createVariant(Report adjustedReport) {
		if(! (adjustedReport instanceof JxlsReport))
			throw new IllegalArgumentException("Expected JxlsReport");
		
		JxlsReportVariant variant = new JxlsReportVariant();
		
		/* copy parameter instances */
		initVariant(variant, adjustedReport);
		
		variant.setJxlsOne(((JxlsReport)adjustedReport).isJxlsOne());
		
		return variant;
		
	}

	@Override
	public void replaceWith(Report aReport, Injector injector) {
		if(! (aReport instanceof JxlsReport))
			throw new IllegalArgumentException("Expected JxlsReport");
		
		super.replaceWith(aReport, injector);
		
		JxlsReport report = (JxlsReport) aReport;
		
		/* set any fields from this report */
		if(null != reportFile)
			injector.getInstance(JxlsReportService.class).remove(reportFile);
		setReportFile(report.getReportFile());
	}

}

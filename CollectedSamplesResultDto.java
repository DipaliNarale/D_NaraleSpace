package in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Value;

@Value
public class CollectedSamplesResultDto {
	
	//TestPlanMechParameter
	private String mechParameter;
	private BigDecimal valueAim;
	private BigDecimal valueMax;
	private BigDecimal valueMin;
	//MechanicalTestResult
	private String batchNo;
	private String mechResult;
	private String resultChngReason;
	private String changedBy;
	private Date changeTime;
	private BigDecimal resultStatus;
	
}

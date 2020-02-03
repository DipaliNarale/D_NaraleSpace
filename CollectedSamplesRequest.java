package in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class CollectedSamplesRequest {
	private String workCenterCode;
	private String testPlanNo;
	private BigDecimal testPlanSeq;
	private String prodOrderNo;
	private String schNo;
	private String batchNo;
	private String ipBatchNo;
	private String material;
	private String seg;
	private String sampleId;
}

package in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto;

import java.math.BigDecimal;
import java.util.List;

import in_.net.usit.mesweb.modules.quality.tpmpsample.TpMpSample;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectedSamplesDto {
	// TestPlanBatchDetail
		private String batchNo;
		private String ipBatchNo;
		private String soNo;
		private String soItemNo;
		private String testPlanNo;
		private String custName;
	
		private BigDecimal testPlanSeq;
		private String prodOrderNo;
		private String schNo;
		private String workCenterCode;
		
		private String material;
		private String seg;
		
		//QCTL$_TP_MP_SMPL
		private List<TpMpSample> sampleList ;
		
		public CollectedSamplesDto(String batchNo, String ipBatchNo, String soNo, String soItemNo, String testPlanNo,
				String custName, BigDecimal testPlanSeq, String prodOrderNo, String schNo, String workCenterCode,
				String material, String seg) {
			super();
			this.batchNo = batchNo;
			this.ipBatchNo = ipBatchNo;
			this.soNo = soNo;
			this.soItemNo = soItemNo;
			this.testPlanNo = testPlanNo;
			this.custName = custName;
			this.testPlanSeq = testPlanSeq;
			this.prodOrderNo = prodOrderNo;
			this.schNo = schNo;
			this.workCenterCode = workCenterCode;
			this.material = material;
			this.seg = seg;
		}

		public CollectedSamplesDto() {
		}
		
		
		

}

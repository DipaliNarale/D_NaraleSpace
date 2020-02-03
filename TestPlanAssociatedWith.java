package in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto;

import lombok.Value;

@Value
public class TestPlanAssociatedWith {
	//TestBatchAssoHeader
	private String soItemNo;
	private String soNo;
	//InventoryMaster
	private String batchStatus;
}

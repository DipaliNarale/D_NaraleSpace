package in_.net.usit.mesweb.modules.quality.mechanicallabtest;

import java.math.BigDecimal;
import java.util.List;

import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesDto;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesRequest;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesResultDto;


public interface MechanicalLabTestService {
	
	List<String> getTestPlanNoByWorkCenter(String workCenter, String testPlanNo,String soNo, String soItemNo,String batchNo);
	List<String> soSoItemSearchFilterList(String workCenter, String testPlanNo,String soNo, String soItemNo,String batchNo);
	List<String> getBatchesBySoSoitemNo(String workCenter, String testPlanNo,String soNo, String soItemNo,String batchNo);

	List<CollectedSamplesDto> getCollectedSamples(String workcenter,String soNo,String testPlanNo, String soItemNo, String batchNo,String sampleId);
	List<CollectedSamplesResultDto> getCollectedSamplesResults(CollectedSamplesRequest request);
	List<CollectedSamplesResultDto> saveMechanicalTestResult(List<CollectedSamplesResultDto> list);
		
	
}

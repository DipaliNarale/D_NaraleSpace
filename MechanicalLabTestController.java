package in_.net.usit.mesweb.modules.quality.mechanicallabtest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import in_.net.usit.mesweb.common.util.MESConstants;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesDto;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesRequest;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesResultDto;


@CrossOrigin(origins = MESConstants.CROSS_ORIGIN_URL)
@RestController
@RequestMapping("/quality")
public class MechanicalLabTestController {
	@Autowired
	private MechanicalLabTestService mechanicalLabTestService;

	@GetMapping("/searchfilter-testplan")
	public List<String> getTestPlanNoByWorkCenter(@RequestParam(
			value="workcenter",required = false) String workcenter,
			@RequestParam(value="testPlanNo",required = false) String testPlanNo,
			@RequestParam(value="soNo",required = false) String soNo,
			@RequestParam(value="soItemNo",required = false) String soItemNo,
			@RequestParam(value="batchNo",required = false) String batchNo) {
		return mechanicalLabTestService.getTestPlanNoByWorkCenter(workcenter, testPlanNo, soNo, soItemNo, batchNo);
	}
	
	@GetMapping("/searchfilter-sosoitem")
	public List<String> soSoItemSearchFilterList(
			@RequestParam(value="workcenter",required = false) String workcenter,
			@RequestParam(value="testPlanNo",required = false) String testPlanNo,
			@RequestParam(value="soNo",required = false) String soNo,
			@RequestParam(value="soItemNo",required = false) String soItemNo,
			@RequestParam(value="batchNo",required = false) String batchNo) {
		return mechanicalLabTestService.soSoItemSearchFilterList(workcenter,testPlanNo,soNo, soItemNo,batchNo);
	}
	
	@GetMapping("/searchfilter-batchnos")
	public List<String> getBatchesBySoSoitemNo(
			@RequestParam(value="workcenter",required = false) String workcenter,
			@RequestParam(value="testPlanNo",required = false) String testPlanNo,
			@RequestParam(value="soNo",required = false) String soNo,
			@RequestParam(value="soItemNo",required = false) String soItemNo,
			@RequestParam(value="batchNo",required = false) String batchNo) {
		return mechanicalLabTestService.getBatchesBySoSoitemNo(workcenter,testPlanNo,soNo, soItemNo,batchNo);
	}
	
	
	@GetMapping("/qcmechlabtest-collected-samples")
	public List<CollectedSamplesDto> getCollectedSamples(
			@RequestParam(value="workcenter",required = false) String workcenter,
			@RequestParam(value="testPlanNo",required = false) String testPlanNo,
			@RequestParam(value="soNo",required = false) String soNo,
			@RequestParam(value="soItemNo",required = false) String soItemNo,
			@RequestParam(value="batchNo",required = false) String batchNo,
			@RequestParam(value="sampleId",required = false) String sampleId)
			{
		return mechanicalLabTestService.getCollectedSamples(workcenter,testPlanNo,soNo,soItemNo,batchNo,sampleId);
	}
	
	@GetMapping("/qcmechlabtest-collsampl-result")
	public List<CollectedSamplesResultDto> getCollectedSamplesResult(
			@RequestParam(value="testPlanNo",required = false) String testPlanNo,
			@RequestParam(value="testPlanSeq",required = false) BigDecimal testPlanSeq,
			@RequestParam(value="prodOrderNo",required = false) String prodOrderNo,
			@RequestParam(value="schNo",required = false) String schNo,
			@RequestParam(value="workCenterCode",required = false) String workCenterCode,
			@RequestParam(value="sampleId",required = false) String sampleId,
			@RequestParam(value="batchNo",required = false) String batchNo,
			@RequestParam(value="ipBatchNo",required = false) String ipBatchNo,
			@RequestParam(value="material",required = false) String material,
			@RequestParam(value="seg",required = false) String seg
			) {
		CollectedSamplesRequest request = new CollectedSamplesRequest(workCenterCode,testPlanNo,testPlanSeq,prodOrderNo,schNo,batchNo,ipBatchNo,material,seg,sampleId);
		return mechanicalLabTestService.getCollectedSamplesResults(request);
	}
	
	@PostMapping("/qcmechlabtest-save-result")
	public List<CollectedSamplesResultDto> saveMechanicalTestResult(@RequestBody List<CollectedSamplesResultDto> list) {
		return mechanicalLabTestService.saveMechanicalTestResult(list);
	}
}

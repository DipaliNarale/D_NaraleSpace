package in_.net.usit.mesweb.modules.quality.mechanicallabtest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesDto;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesRequest;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesResultDto;
import in_.net.usit.mesweb.modules.quality.mechanicaltestresult.MechanicalTestResult;
import in_.net.usit.mesweb.modules.quality.mechanicaltestresult.MechanicalTestResultRepository;
import in_.net.usit.mesweb.modules.quality.tpmpsample.TpMpSample;
import in_.net.usit.mesweb.modules.quality.tpmpsample.TpMpSampleRepository;

@Service
public class MechanicalLabTestServiceImpl implements MechanicalLabTestService {
	@Autowired
	private MechanicalLabTestRepository mechanicalLabTestRepository;
	@Autowired
	private TpMpSampleRepository tpMpSampleRepository;
	@Autowired
	private MechanicalTestResultRepository mechanicalTestResultRepository;
	
	@Override
	public List<String> getTestPlanNoByWorkCenter(String workCenter, String testPlanNo,String soNo, String soItemNo,String batchNo) {
		return mechanicalLabTestRepository.getTestPlanNoByWorkCenter(workCenter, testPlanNo, soNo, soItemNo, batchNo);
	}
	
	@Override
	public List<String> soSoItemSearchFilterList(String workCenter, String testPlanNo,String soNo, String soItemNo,String batchNo) {
		return mechanicalLabTestRepository.soSoItemSearchFilterList(workCenter, testPlanNo, soNo, soItemNo, batchNo);
	}
	
	@Override
	public List<String> getBatchesBySoSoitemNo(String workCenter, String testPlanNo, String soNo, String soItemNo,String batchNo) {
		return mechanicalLabTestRepository.getBatchesBySoSoitemNo(workCenter, testPlanNo, soNo, soItemNo, batchNo);
	}

	@Override
	public List<CollectedSamplesDto> getCollectedSamples(String workcenter,String soNo,String testPlanNo, String soItemNo, String batchNo,
			String sampleId) {
		
		List<CollectedSamplesDto> list1 = new ArrayList<CollectedSamplesDto>();
		List<CollectedSamplesDto> list =  mechanicalLabTestRepository.getCollectedSamples(workcenter,testPlanNo,soNo,soItemNo,batchNo, sampleId);
		for (CollectedSamplesDto collectedSamplesDto : list) {
			List<TpMpSample> sampleList = tpMpSampleRepository.getSampleListOfBatch(collectedSamplesDto.getBatchNo(), collectedSamplesDto.getIpBatchNo());
			if(sampleList!=null && !sampleList.isEmpty()) {
				collectedSamplesDto.setSampleList(sampleList);
				list1.add(collectedSamplesDto);
			}
		}
		return list1;
	}
	
	@Override
	public List<CollectedSamplesResultDto> getCollectedSamplesResults(CollectedSamplesRequest request) {
		return mechanicalLabTestRepository.getCollectedSamplesResults(request);
	}


	@Override
	public List<CollectedSamplesResultDto> saveMechanicalTestResult(List<CollectedSamplesResultDto> list) {
		for (CollectedSamplesResultDto collectedSamplesResultDto : list) {
			Optional<MechanicalTestResult>  result = mechanicalTestResultRepository.findById(collectedSamplesResultDto.getBatchNo());
			if(result.isPresent()) {
				MechanicalTestResult resultObj = result.get();
				if(resultObj != null) {
					resultObj.setMechResult(collectedSamplesResultDto.getMechResult());
					resultObj.setResultChngReason(collectedSamplesResultDto.getResultChngReason());
					resultObj.setResultStatus(collectedSamplesResultDto.getResultStatus());
					mechanicalTestResultRepository.save(resultObj);
				}
			}
		}
		return list;
	}

}

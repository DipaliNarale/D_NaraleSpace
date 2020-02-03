package in_.net.usit.mesweb.modules.quality.mechanicallabtest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesDto;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesRequest;
import in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesResultDto;

@Repository
public class MechanicalLabTestRepositoryImpl implements MechanicalLabTestRepository {
	@Autowired  EntityManager entityManager;
	
	@Override
	public List<String> getTestPlanNoByWorkCenter(String workCenter, String testPlanNo, String soNo, String soItemNo,String batchNo) {
		TypedQuery<String> q = entityManager.createQuery("SELECT  distinct(t.testPlanNo) FROM TestPlanBatchDetail t"
				+ " where " 
				+ " (:workCenter is  null or t.currWc = :workCenter) "
				+ " and (:testPlanNo is  null or lower(t.testPlanNo) LIKE lower(concat('%', :testPlanNo,'%')))"
				+ " and (:soNo is null or t.soNo = :soNo) "
				+ " and (:soItemNo is null or t.soItemNo = :soItemNo)"
				+ " and (:batchNo is null or t.id.batchNo = :batchNo) "
				+ " ORDER BY t.testPlanNo",	String.class);
				q.setParameter("workCenter", workCenter);
				q.setParameter("testPlanNo", "%"+testPlanNo+"%");
				q.setParameter("soNo", soNo);
				q.setParameter("soItemNo", soItemNo);
				q.setParameter("batchNo", batchNo);
				return q.getResultList();
	}
	
	@Override
	public List<String> soSoItemSearchFilterList(String workCenterCode, String testPlanNo, String soNo, String soItemNo,String batchNo) {
		soNo = soNo!=null? "%"+soNo+"%" : soNo;
		soItemNo = soItemNo!=null? "%"+soItemNo+"%" : soItemNo;
		TypedQuery<String> q = entityManager.createQuery("SELECT distinct(concat(tpd.soNo, '-', tpd.soItemNo)) as soNosoItem"
		 		 + " FROM TestPlanBatchDetail tpd "
                 + " where"
                 + " (:workCenterCode is null or tpd.currWc = :workCenterCode) "
                 + " and (:testPlanNo is  null or tpd.testPlanNo  = :testPlanNo) " 
                 + " and (:soNo is null or lower(tpd.soNo) LIKE lower(concat('%', :soNo,'%')))"
                 + " and (:soItemNo is null or lower(tpd.soItemNo) LIKE lower(concat('%', :soItemNo,'%')))"
                 + " and  (:batchNo is null or tpd.id.batchNo = :batchNo) "
                 + " ORDER BY soNosoItem ASC", String.class);
		q.setParameter("workCenterCode", workCenterCode);
		q.setParameter("testPlanNo", testPlanNo);
		q.setParameter("soNo", soNo);
		q.setParameter("soItemNo", soItemNo);
		q.setParameter("testPlanNo", testPlanNo);
		 q.setParameter("batchNo", batchNo);
		 return  q.getResultList();
	}
	
	@Override
	public List<String> getBatchesBySoSoitemNo(String workCenter, String testPlanNo, String soNo, String soItemNo,String batchNo) {
		TypedQuery<String> q = entityManager.createQuery("SELECT t.id.batchNo "
				+ " FROM TestPlanBatchDetail t "
				+ " where "
				+ " (:soNo is null or t.soNo = :soNo) "
				+ " and  (:soItemNo is null or t.soItemNo = :soItemNo)"
				+ " and (:workCenter is  null or t.currWc = :workCenter) "
				+ " and  (:testPlanNo is  null or  t.testPlanNo = :testPlanNo)"
				+ " and  (:batchNo is null or lower(t.id.batchNo) LIKE lower(concat('%', :batchNo,'%')))"
				+ " and (SELECT count(b) FROM TpMpSample b"
				+ " WHERE "
				+ " b.id.batchNo = t.id.batchNo "
				+ " and b.id.ipBatchNo = t.id.ipBatchNo) > 0 ORDER BY t.id.batchNo" , String.class);
		 q.setParameter("workCenter", workCenter);
		q.setParameter("soNo", soNo);
		q.setParameter("soItemNo", soItemNo);
		q.setParameter("testPlanNo", testPlanNo);
		 q.setParameter("batchNo", "%"+batchNo+"%");
		 return  q.getResultList();
	}
		
	@Override
	public List<CollectedSamplesDto>getCollectedSamples(String workcenter,String soNo,String testPlanNo, String soItemNo, String batchNo,
			String sampleNo) {
		TypedQuery<CollectedSamplesDto> q = entityManager.createQuery("SELECT new in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesDto("
				+ " tpbd.id.batchNo,"
				+ " tpbd.id.ipBatchNo,"
				+ " tpbd.soNo,"
				+ " tpbd.soItemNo,"
				+ " tpbd.testPlanNo,"
				+ " tpbd.custName,"
				+ " tpbd.testPlanSeq,"
				+ " tpbd.prodOrderNo,"
				+ " tpbd.schNo,"
				+ " tpbd.workCenterCode,"
				+ " tpbd.material,"
				+ " tpbd.seg"
				+ " ) "
				+ " FROM TestPlanBatchDetail tpbd "
				+ " where"
				+ " (:workCenterCode is  null or tpbd.currWc = :workCenterCode) "
				+ " and (:testPlanNo is  null or  tpbd.testPlanNo = :testPlanNo) "
				+ " and (:soNo is null or tpbd.soNo = :soNo) "
     			+ " and (:soItemNo is null or tpbd.soItemNo = :soItemNo) "
     			+ " and (:batchNo is null or tpbd.id.batchNo = :batchNo) ORDER BY tpbd.testPlanNo ASC"
				 ,CollectedSamplesDto.class);	
		q.setParameter("workCenterCode", workcenter);
		q.setParameter("testPlanNo", testPlanNo);
		q.setParameter("soNo", soNo);
		q.setParameter("soItemNo", soItemNo);
		q.setParameter("batchNo", batchNo);
		 return  q.getResultList();
	}

	public List<String> getSamplesList(CollectedSamplesDto collectedSapmlesDto,String sampleId,String testPlanNo) {
		TypedQuery<String> q = entityManager.createQuery("SELECT tpbd.sampleNo "
				+ " FROM TestPlanBatchDetail tpbd "
				+ " where tpbd.testPlanNo = :tpNO and (:sampleId is null and tpbd.sampleNo = :sampleId)"
				 ,String.class);
		q.setParameter("sampleId",sampleId );
		q.setParameter("collectedSapmlesDto",collectedSapmlesDto );
		q.setParameter("testPlanNo",testPlanNo );
		 return  q.getResultList();
	}
	 
	
	@Override
	public List<CollectedSamplesResultDto> getCollectedSamplesResults(CollectedSamplesRequest request) {

		TypedQuery<CollectedSamplesResultDto> q = entityManager.createQuery("SELECT new in_.net.usit.mesweb.modules.quality.mechanicallabtest.dto.CollectedSamplesResultDto("
				+ " tpmp.id.mechParameter, "
				+ " tpmp.valueAim,"
				+ " tpmp.valueMax,"
				+ " tpmp.valueMin,"
				+ " mtr.batchNo,"
				+ " mtr.mechResult,"
				+ " mtr.resultChngReason,"
				+ " mtr.modifiedBy,"
				+ " mtr.modifiedDate,"
				+ " mtr.resultStatus"
				+ ")"
				+ " FROM TestPlanMechParameter tpmp, MechanicalTestResult mtr"
				+ " WHERE tpmp.id.batchNo = mtr.batchNo AND tpmp.id.mechParameter = mtr.mechParameter"
				+ " AND (:testPlanNo is null or tpmp.id.testPlanNo = :testPlanNo)"
				+ " AND (:batchNo is null or tpmp.id.batchNo = :batchNo)"
				+ " AND (:ipBatchNo is null or tpmp.id.ipBatchNo = :ipBatchNo)"
				+ " AND (:materialCode is null or tpmp.id.materialCode = :materialCode)"
				+ " AND (:seg is null or tpmp.id.seg = :seg)"
				+ " AND (:workCenterCode is null or tpmp.id.workCenterCode = :workCenterCode)"
				+ " AND (:sampleNo is null or mtr.sampleNo = :sampleNo)"
				,CollectedSamplesResultDto.class);
		q.setParameter("testPlanNo", request.getTestPlanNo());
		q.setParameter("batchNo", request.getBatchNo());
		q.setParameter("ipBatchNo", request.getIpBatchNo());
		q.setParameter("materialCode", request.getMaterial());
		q.setParameter("seg", request.getSeg());
		q.setParameter("workCenterCode",request.getWorkCenterCode());
		q.setParameter("sampleNo", request.getSampleId());
		 return  q.getResultList();
	}

	
	
}


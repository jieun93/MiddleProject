package kr.or.ddit.dao.knowledge;

import java.util.List;

import kr.or.ddit.vo.A_FormJoinForm_FileVO;
import kr.or.ddit.vo.A_FormVO;
import kr.or.ddit.vo.A_TerminologyVO;
import kr.or.ddit.vo.Form_FileVO;
import kr.or.ddit.vo.Related_LawVO;

public interface IKnowledgeDao {
	
	public List<A_FormJoinForm_FileVO> joinForm();
	
	public List<A_TerminologyVO> getAllTerm();
	
	public List<Related_LawVO> getAllLaw();
	
	public List<A_TerminologyVO> searchTerms(String a_ter_name); //용어 이름으로 검색
	
	public List<A_FormVO> getAllForm(); // 경매 서식 정보 출력
	public List<Form_FileVO> getAllFile(); // 경매 서식 정보에 필요한 파일 정보 출력
	
	public List<Related_LawVO> searchLaw(String rel_l_name);
	
	//--------------------------------------------- 경매용어
	
	public List<A_TerminologyVO> getTerms();
	
	public int insertTerms(A_TerminologyVO termVo);
	
	public int updateTerms(A_TerminologyVO termVo);
	
	public int deleteTerms(A_TerminologyVO termVo);

	//--------------------------------------------- 경매서식
	
	public String insertForm(A_FormVO formVo);
	
	public int insertFile(Form_FileVO fileVo);
	
	public int updateForm(A_FormVO formVo);
	
	public int updateFile(Form_FileVO fileVo);
	
	public int deleteForm(A_FormVO formVo);
	
	public int deleteFile(Form_FileVO fileVo);
	
	//--------------------------------------------- 관련법률 
	
	public int insertLaw(Related_LawVO lawVo);
	
	public int updateLaw(Related_LawVO lawVo);
	
	public int deleteLaw(Related_LawVO lawVO);

	public List<Form_FileVO> getFormList(String a_form_no); 
	
}

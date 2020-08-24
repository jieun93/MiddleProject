package kr.or.ddit.service.InformationUse;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.FAQVO;


public interface IFAQService extends Remote {
	
	/**
	 * 모든 FAQ를 검색해서 반환하는 메서드
	 * @return 검색된 FAQ리스트!
	 */
	public List<FAQVO> getAllFAQList()throws RemoteException ;
	
	/**
	 * FAQ_Num을 매개변수로 받아 해당하는 FAQ를 반환하는 메서드
	 * @param faqNum 검색번호
	 * @return 검색된 FAQ리스트
	 */
	public List<FAQVO> getDetailsList(String faqNum)throws RemoteException ;
	
	public List<FAQVO> getPagingFAQList(Map<String, Integer> paraMap) throws RemoteException;
}
package kr.or.ddit.service.member;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import kr.or.ddit.dao.member.IMemberDao;
import kr.or.ddit.dao.member.MemberImplDao;
import kr.or.ddit.vo.MemberVO;

public class ServiceImpl extends UnicastRemoteObject implements IService{

	private static ServiceImpl service;
	
	private IMemberDao dao;
	
	public ServiceImpl() throws RemoteException{
		dao = MemberImplDao.getInstance(); 
		}
	
	public static IService getInstance() {
		try {
			if(service == null)
				service = new ServiceImpl();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return service;
	}
	
	
	
	@Override
	public int insertMemberJoin(MemberVO memVO) throws RemoteException {
		return dao.insertMemberJoin(memVO);
	}

	@Override
	public MemberVO getMemberId(String mem_id) throws RemoteException{
		return dao.getMemberId(mem_id);
	}

}

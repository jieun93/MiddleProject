package kr.or.ddit.controller.login;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.AlertDialog.AlertUtil;
import kr.or.ddit.controller.findIdPass.FindIdController;
import kr.or.ddit.controller.findIdPass.FindPassController;
import kr.or.ddit.controller.main.MainPageController;
import kr.or.ddit.service.login.ILoginService;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.MemberVO;

public class loginController {
	private String nextPage;
	
	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	private ObservableList<MemberVO> memberList;
	private ILoginService service;
	private Session session;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

    @FXML
    private AnchorPane an_logo;

	@FXML
	private TextField tfId;

	@FXML
	private Button btn_confirm;

	@FXML
	private PasswordField tfPass;

	private MemberVO memberVO;
	
	@FXML
    private Button btn_find_id;

    @FXML
    private Button btn_find_pass;
    
	
	@FXML
	void btn_confirm_clicked(MouseEvent event) {
		try {
			Stage confirm = (Stage) btn_confirm.getScene().getWindow();
			String inputId = tfId.getText();
			String inputPass = tfPass.getText();
			memberVO = new MemberVO();
			memberVO.setMem_id(inputId);
			memberVO.setMem_pass(inputPass);
			
			MemberVO memVo = service.getmemUser(memberVO);

			if(memVo != null) {
				if(memVo.getMem_active().equals("y")){
					session.loginUser = memVo;
					if(!nextPage.equals(""))
					setnextDialog(nextPage);
				}else if(memVo.getMem_active().equals("n")){
					AlertUtil.showAlertOnlyConfirm(confirm, "이미 탈퇴한 회원입니다.", "확인");
					return;
				}
				
			}else {
				AlertUtil.showAlertOnlyConfirm(confirm, "아이디나 비밀번호가 일치하지 않습니다.", "확인");
			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}

	

	}
	
	
	void setnextDialog(String nextPage) {
		try {
			Parent root = FXMLLoader.load(loginController.class.getResource(nextPage));
			Stage stage = (Stage)btn_confirm.getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
    void an_logo_clicked(MouseEvent event) {
		try {
			
			FXMLLoader loader = new FXMLLoader(loginController.class.getResource("../../fxml/main/MainPage.fxml"));
			Parent root = loader.load();
			MainPageController logCon = loader.getController();
			Stage stage = (Stage) btn_confirm.getScene().getWindow();
			Scene scene = new Scene(root);

			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
    @FXML
    void btn_find_id_clicked(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(loginController.class.getResource("../../fxml/findIdPass/FindId.fxml"));
			Parent root = loader.load();
			FindIdController logCon = loader.getController();
			Stage stage = (Stage) btn_confirm.getScene().getWindow();
			Scene scene = new Scene(root);

			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btn_find_pass_clicked(MouseEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(loginController.class.getResource("../../fxml/findIdPass/FindPass.fxml"));
			Parent root = loader.load();
			FindPassController logCon = loader.getController();
			Stage stage = (Stage) btn_confirm.getScene().getWindow();
			Scene scene = new Scene(root);

			stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
//	@FXML
//    void btn_find_clicked(MouseEvent event) {
//		try {
//			
//			FXMLLoader loader = new FXMLLoader(loginController.class.getResource("../../fxml/findIdPass/FindIdPass.fxml"));
//			Parent root = loader.load();
//			FindIdPassController logCon = loader.getController();
//			Stage stage = (Stage) btn_confirm.getScene().getWindow();
//			Scene scene = new Scene(root);
//
//			stage.setScene(scene);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    }

	@FXML
	void initialize() {

		// service객체 생성
		try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.118",8888);
			service = (ILoginService) reg.lookup("login");
		} catch (RemoteException e) {
			e.printStackTrace();

		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}

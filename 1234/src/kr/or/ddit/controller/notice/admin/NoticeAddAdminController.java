package kr.or.ddit.controller.notice.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import kr.or.ddit.AlertDialog.AlertUtil;
import kr.or.ddit.service.InoticeServer;
import kr.or.ddit.session.Session;
import kr.or.ddit.vo.Answer_FileVO;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.Notice_FileVO;

public class NoticeAddAdminController {
	private NoticeAdminController controller;
	
	public void setController(NoticeAdminController controller) {
		this.controller = controller;
	}

	private ObservableList<File> fileData;
	private InoticeServer service;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tf_Title;

    @FXML
    private HTMLEditor html_Content;

    @FXML
    private Button btn_AddFile;

    @FXML
    private Button btn_DeleteFile;

    @FXML
    private ListView<File> lv_File;

    @FXML
    private Button btn_AddNotice;



    @FXML
    void onClickedBtn_AddFile(ActionEvent event) {
    	Stage stage = (Stage) btn_AddNotice.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All file", "*.*"));

		File showDir = new File("d:/D_other/");
		fileChooser.setInitialDirectory(showDir);

		File selectedFile = fileChooser.showOpenDialog(stage);
		fileData = FXCollections.observableArrayList(selectedFile);
		lv_File.getItems().addAll(fileData);
    }

    @FXML
    void onClickedBtn_AddNotice(ActionEvent event) {
    	if(!AlertUtil.showAlert((Stage)btn_AddFile.getScene().getWindow(), "저장하시겠습니까?")) {
    		return;
    	}
    	
    	boolean insertChk = false;
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setNotice_content(html_Content.getHtmlText());
		noticeVO.setNotice_title(tf_Title.getText());
//		noticeVO.setNotice_writer(Session.loginUser.getMem_id()); // session을 구현하면 해당아이디에 세션을 넣어준다.
		noticeVO.setNotice_writer("관리자"); 
		
		
		try {
		
				insertChk = service.insertNotice(noticeVO);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	
		
	
		if(lv_File.getItems().size()!=0) {
			for (File file : lv_File.getItems()) {
				try {
					FileInputStream fis = new FileInputStream(file);
					byte[] data = new byte[(int) file.length()];
					fis.read(data);
					FileInfoVO fVo = new FileInfoVO();
					fVo.setFileName(file.getName()); // file의 이름을 가져와 객체에 설정한다.
					fVo.setFileData(data); // 읽어온 데이터를 객체에 설정한다.
					
					String filePath =service.clientToServer("NoticeFile", fVo);
					
					Notice_FileVO notice_FileVO = new Notice_FileVO();
					notice_FileVO.setFile_addr(filePath);

					
					insertChk = service.insertNotice_file(notice_FileVO);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		Stage stage = (Stage)btn_AddFile.getScene().getWindow();
		controller.getNoticeList();
		stage.close();
		
		
    }

    
    
  
    @FXML
    void onClickedBtn_DeleteFile(ActionEvent event) {
    	if (lv_File.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		lv_File.getItems().remove(lv_File.getSelectionModel().getSelectedItem());
    }

    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("localhost", 8888);
			service = (InoticeServer) reg.lookup("notice");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		

    }
}

package kr.or.ddit.controller.notice.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import kr.or.ddit.AlertDialog.AlertUtil;
import kr.or.ddit.service.InoticeServer;
import kr.or.ddit.vo.FileInfoVO;
import kr.or.ddit.vo.NoticeVO;

public class NoticePopupController {
	private InoticeServer service;
	private NoticeVO noticeVO;


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tf_title;

    @FXML
    private Label tf_writer;

    @FXML
    private WebView html_content;

    @FXML
    private ListView<File> lt_file;
   
    public void setInfoData(NoticeVO noticeVO) {
    	this.noticeVO = noticeVO;
    	System.out.println(this.noticeVO);
    	tf_title.setText(noticeVO.getNotice_title());
    	tf_writer.setText(noticeVO.getNotice_writer());
    	html_content.getEngine().loadContent(noticeVO.getNotice_content());
    
    	

    	ObservableList<File> fileData = null;
    	List<File> fileList = new ArrayList<File>();
    	List<FileInfoVO> dataList = null;
		try {
			dataList = service.noticeServerToClient(service.getNoticeFileList(noticeVO.getNotice_no()));
		} catch (RemoteException e1) {
	
			e1.printStackTrace();
		}
    	
    	if(dataList==null) {
    		return;
    	}
    	
		try {
			for (FileInfoVO filedate : dataList) {
				File file = new File(filedate.getFileName());
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(filedate.getFileData());
				fos.flush();
				fileList.add(file);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	fileData= FXCollections.observableArrayList(fileList);
    	lt_file.setItems(fileData);
    }
  


	@FXML
    void onClickedDownload(MouseEvent event) {
    	if(lt_file.getSelectionModel()==null) return;
		
		Stage stage = (Stage)tf_title.getScene().getWindow();
    	AlertUtil.showDonwload(stage,lt_file.getSelectionModel().getSelectedItem());
    	
    }
    
    @FXML
    void initialize() {
    	
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.118",8888);
			service = (InoticeServer) reg.lookup("notice");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	lt_file.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
			
			@Override
			public ListCell<File> call(ListView<File> param) {			
				return new ListCell<File>() {
					@Override
					protected void updateItem(File item, boolean empty) {
						super.updateItem(item, empty);
						if(!empty) {
				    		setText(item.getName());
						}else {
							setText(null);
						}
					}
				};
			}
			
		});
    	html_content.setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				html_content.onContextMenuRequestedProperty().addListener((Observable, oldValue, newValue) -> {
//    				if(!newValue.matches("^[0-9]+$")) {
//    					tfSearch_2.setText(newValue.replaceAll("[^0-9]", ""));
//    				}
					System.out.println("호출");
    			});
				
			}
		});
    	
    	
    }

    

}

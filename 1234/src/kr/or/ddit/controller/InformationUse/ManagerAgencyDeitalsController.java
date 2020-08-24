package kr.or.ddit.controller.InformationUse;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import kr.or.ddit.AlertDialog.AlertDialogController;
import kr.or.ddit.AlertDialog.AlertUtil;
import kr.or.ddit.service.InformationUse.IAgencyService;
import kr.or.ddit.service.InformationUse.IManagerAgencyService;
import kr.or.ddit.vo.AgencyVO;


public class ManagerAgencyDeitalsController {
	private AnchorPane dialog;
    private ObservableList<AgencyVO> agencyList;
    private IManagerAgencyService service;
    private AgencyVO agencyVo;
    String couNo;
    private AlertDialogController alert;
    
    public AnchorPane getDialog() {
		return dialog;
	}

	public void setDialog(AnchorPane dialog) {
		this.dialog = dialog;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane contentsArea;

    @FXML
    private Label lableAddr;

    @FXML
    private Label lableTel;

    @FXML
    private Label lableRoad;

    @FXML
    private ImageView courtRoad;

    @FXML
    private TextField tfAddr;

    @FXML
    private TextField tfTel;

    @FXML
    private Label labelName;

    @FXML
    private TextField tfName;


    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnFrontModi;
    
    //추가창의 등록버튼
    @FXML
    private Button btnNewModify;
    
    //지도 영역
    @FXML
    private AnchorPane anchorMap;
    
   

   
	
	public AgencyVO getAgencyVoInfo() {
		return agencyVo;
	}

	public void setAgencyVoInfo(AgencyVO agencyVo) {
		this.agencyVo = agencyVo;
	}
	
	public void setName(AgencyVO agencyVo, String state) {
//    	this.faqVo = faqVo;
//    	tfTitle.setText(faqVo.getFaq_title());
//    	tfContents.setText(faqVo.getFaq_content());
		
		tfName.setText(agencyVo.getCou_name());
		tfAddr.setText(agencyVo.getCou_loc());
		tfTel.setText(agencyVo.getCou_tel());
		couNo = agencyVo.getCou_no(); 
		
		//지
		
		btnFrontModi.setText(state);
    }
	
	

   
	
	@FXML
    void btnNewModifyClicked(ActionEvent event) {

    }
	
    @FXML
    void btnDeleteClicked(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(ManagerAgencyDeitalsController.class.getResource("../../fxml/InformationUse/ManagerAgency.fxml"));
    		Parent root = loader.load();
    		ManagerAgencyController magenCon = loader.getController();
    		magenCon.setDialog(dialog);
    		magenCon.setAgencyVoInfo(getAgencyVoInfo());
    		
    		if(dialog.getChildren().size()!=0) {
    			for(int i=0; i<dialog.getChildren().size(); i++) {
    				dialog.getChildren().remove(i);
    			}
    		}
    		dialog.getChildren().add(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void btnFrontModiClcied(ActionEvent event) {
    	
    	if(AlertUtil.showAlert((Stage) btnDelete.getScene().getWindow(), "법원정보를 등록하시겠습니까?")) {
    	agencyVo = new AgencyVO();	
    	agencyVo.setCou_name(tfName.getText());
    	agencyVo.setCou_loc(tfAddr.getText());
    	agencyVo.setCou_tel(tfTel.getText());
    	agencyVo.setCou_no(couNo);
    	
    	//alert창
    	
    	if(btnFrontModi.getText().equals("수정")) {
    		try {
				service.updateCourt(agencyVo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else if(btnFrontModi.getText().equals("등록")) {
    		try {
				service.insertCourt(agencyVo);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	//들어가면 다시 앞페이지로
    	
	    	try {
				FXMLLoader loader = new FXMLLoader(ManagerAgencyDeitalsController.class.getResource("../../fxml/InformationUse/ManagerAgency.fxml"));
	    		Parent root = loader.load();
	    		ManagerAgencyController magenCon = loader.getController();
	    		magenCon.setDialog(dialog);
	    		magenCon.setAgencyVoInfo(agencyVo);
	    		
	    		if(dialog.getChildren().size()!=0) {
	    			for(int i=0; i<dialog.getChildren().size(); i++) {
	    				dialog.getChildren().remove(i);
	    			}
	    		}
	    		dialog.getChildren().add(root);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
	
    }
    

    @FXML
    void courtRoadClicked(MouseEvent event) {
    	
    }

    @FXML
    void tfAddrClicked(ActionEvent event) {
    	tfAddr.requestFocus();
    }

    @FXML
    void tfNameClicked(ActionEvent event) {
    	tfName.requestFocus();
    }

    @FXML
    void tfTelclicked(ActionEvent event) {
    	tfTel.requestFocus();
    }
    
  //지도
//    private static void initFX() {
//
//    	JFrame frame = new JFrame("FX");
//
////    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    	frame.getContentPane().setLayout(null);
//
//    	final JButton jButton = new JButton("Button");
//
//    	final JFXPanel fxPanel = new JFXPanel();
//
//    	frame.add(jButton);
//
//    	frame.add(fxPanel);
//
//    	frame.setVisible(true);
//
//    	jButton.setSize(new Dimension(200, 27));
//
//    	fxPanel.setSize(new Dimension(300, 300));
//
//    	fxPanel.setLocation(new Point(0, 27));
//
//    	frame.getContentPane().setPreferredSize(new Dimension(300, 327));
//
//    	frame.pack();
//
//    	frame.setResizable(false);
//
//    	Platform.runLater(new Runnable() {
//
//    		public void run() {
//
//    			initAndLoadWebView(fxPanel);
//
//    		}
//
//    	});
//
//    }
//
//    	
//
//    private static void initAndLoadWebView(final JFXPanel fxPanel) {
//
//    	Group group = new Group();
//
//    	Scene scene = new Scene(group);
//
//    	fxPanel.setScene(scene);
//
//    	WebView webView = new WebView();
//
//    	group.getChildren().add(webView);
//
//    	webView.setMinSize(490, 510);
//
//    	webView.setMaxSize(490, 510);
//
//    	WebEngine webEngine = webView.getEngine();
//        webEngine.load("http://localhost:8082/z_map/KakaoMap.html");
//
//    }

    @FXML
    void initialize() {

    	//service객체 생성
    	try {
			Registry reg = LocateRegistry.getRegistry(8888);
			service = (IManagerAgencyService) reg.lookup("ManagerAgency");
		} catch (RemoteException e) {
			e.printStackTrace();
			
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
        agencyList = FXCollections.observableArrayList();
        
//        WebView webView = new WebView();
//        WebEngine webEngine = webView.getEngine();
//        webEngine.load("http://localhost:8082/z_map/KakaoMap.html");
//        anchorMap.getChildren().add(webView);
   
       
        
      
        
    }
}
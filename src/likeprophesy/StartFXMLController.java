/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package likeprophesy;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author VarunJain
 */
public class StartFXMLController implements Initializable {
    
    String accessToken = "CAACEdEose0cBAGnJUwERDv4DT8LIUWsvfrtYhXO9cSB7JTHBZATyOd8kssFDnpBnJN5rKVKIfPs33OHVFYLeNnSZCillN5IPDSAWClNaMOrrr1vRstpr3Pii7jFxRNrsMZCtaM0uh5wZCpBzIamnbMYPGntyGmbyA1X6ogIjR7MjzXDoMlyKyQZCU1QYr34tnAU0aB9ZBITxybOsZAWW7IS";
    
    @FXML
    private TextField t1;
    @FXML
    private Button jButton1;
    @FXML
    private Label jLabel1;
    @FXML
    private TextField t2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t1.setText("first time cool cool hololens");
        t2.setText(accessToken);
    }    

    @FXML
    private void goButtonClick(ActionEvent event) {
        jLabel1.setText("Projected New Likes = " + Integer.toString(LikeProphesy.prophesize(t1.getText(), t2.getText())));
        FBHashGet.totalPosts = 0;
        FBHashGet.userCountList.clear();
    }
    
}

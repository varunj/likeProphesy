/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package likeprophesy;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import static likeprophesy.LikeProphesy.result1;

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
    @FXML
    private Slider slider;
    @FXML
    private BarChart<String, Double> graph;

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
        LikeProphesy.prophesize(t1.getText(), t2.getText());
        jLabel1.setText("Projected New Likes by algo1 = " + Integer.toString(result1.size()));
        FBHashGet.totalPosts = 0;
        FBHashGet.userCountList.clear();
        
        //--------------------------------------------
        graph.getData().removeAll();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        graph.setTitle("Friends v/s Probability of Liking");
        xAxis.setLabel("Name");       
        yAxis.setLabel("Probability(like)");
 
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        Set<Map.Entry<String, Double>> set = result1.entrySet();
        List<Map.Entry<String, Double>> list = new ArrayList<Map.Entry<String, Double>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Double>>()
        {
            public int compare( Map.Entry<String, Double> o1, Map.Entry<String, Double> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Double> entry:list)
        {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }  
        graph.getData().add(series);     
        
    }
    
}

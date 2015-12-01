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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import static likeprophesy.LikeProphesy.countlikesFrom1;
import static likeprophesy.LikeProphesy.countlikesFrom2;
import static likeprophesy.LikeProphesy.result1;

/**
 * FXML Controller class
 *
 * @author VarunJain
 */
public class StartFXMLController implements Initializable {
    
    String accessToken = "CAACEdEose0cBAPglFU9BXr9znnnQ5f9ROxikg1RwVUHMeVbZAwdgrja7uOVmSLXH3V6d1v3Ko3nz5qlKTA0h8YzTZBHC99Ehrg5SfTTQlZCMYZBZCyQUSs37v2N3wwwo477ZAd9K6xFIMg1TNjyFZCUvdH1sGy4xCjXZCMy8JBghMx8cEbXEtFdC6VV4DZBf1HtO0xdPF9wu1vQZDZD";
    
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
    @FXML
    private Label sliderLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t1.setText("first time cool cool hololens");
        t2.setText(accessToken);
        slider.setMin(0);
        slider.setMax(100);
        slider.setValue(9);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);
        
        slider.valueProperty().addListener(new ChangeListener<Number>() 
        {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) 
            {
                if (!slider.isValueChanging() || newValue.doubleValue() == slider.getMax() || newValue.doubleValue() == slider.getMin())
                {
                    sliderLabel.setText("" + slider.getValue());
                }
            }
        });
    }    

    @FXML
    private void goButtonClick(ActionEvent event) {

        // predict
        LikeProphesy.prophesize(t1.getText(), t2.getText(), slider.getValue());
        jLabel1.setText("Projected New Likes by algo1 = " + countlikesFrom1 + " from 2: " + countlikesFrom2);
        
        // plot data
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
        
        // reinit fields
        graph.getData().clear();
        graph.getData().add(series);   
        FBHashGet.totalPosts = 0;
        FBHashGet.userCountList.clear();
    }
    
}

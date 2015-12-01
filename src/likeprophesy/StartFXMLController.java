/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package likeprophesy;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import static likeprophesy.LikeProphesy.countlikesFrom1;
import static likeprophesy.LikeProphesy.countlikesFrom2;
import static likeprophesy.LikeProphesy.countlikesFrom3;
import static likeprophesy.LikeProphesy.result1;

/**
 * FXML Controller class
 *
 * @author VarunJain
 */
public class StartFXMLController implements Initializable {
    
    String accessToken = "CAACEdEose0cBAASspZC5PZA3IV9pD6hSgvnEHV92FeYaBctX4sXplPADDleCEQZBPEqIHndDPpcBg7ZC81W2kqJD0w7ARW1WgJjEXYZBVh0Sr2EaUDx6uVGz0Yf6sMG7CDl43NzDMf1BeQJX6XekW71lpWDNE0oxmKe0eVrlcZARSsaxZBKs8GUx3rcdl2f4GGPc1bebx49bQZDZD";
    String imagePath = "";
    
    @FXML
    private TextArea t1;
    @FXML
    private Button jButton1;
    @FXML
    private TextArea jLabel1;
    @FXML
    private TextField t2;
    @FXML
    private Slider slider;
    @FXML
    private BarChart<String, Double> graph;
    @FXML
    private Label sliderLabel;
    @FXML
    private ImageView iv1;
    @FXML
    private Button UploadButton;
    @FXML
    private ProgressBar progressBar;
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
    private void goButtonClick(ActionEvent event)throws InterruptedException  {
        for (int i = 0; i < 6; i++)
        {
            progressBar.setProgress((i*1.0)/10);
            Thread.sleep(90);
        }
        // predict
        for (String x : t1.getText().split("\n"))
        {
            if (x.length() > 0)
            {
                System.out.print(x + "--------------");
            }
        }
//        LikeProphesy.prophesize(t1.getText(), t2.getText(), slider.getValue());
        progressBar.setProgress(1);
        jLabel1.setText("Projected New Likes by algo1 = " + countlikesFrom1 + " from 2: " + countlikesFrom2 + " from 3: " + countlikesFrom3);
        
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
        FBHashGet.numberLikes.clear();
    }
    
    @FXML
    private void UploadButtonClick(ActionEvent event) {
        try
        {
            Node node = (Node) event.getSource();
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open File");
            File x = chooser.showOpenDialog(node.getScene().getWindow());
            if (x == null) 
            {
                imagePath = "NULL";
            }
            else           
            {
                File dest = new File("./src//1.png");
                imagePath = x.getPath();
                Files.copy(x.toPath(), dest.toPath(), REPLACE_EXISTING);
                Image img = new Image("1.png");
                iv1.setImage(img);
            }

            t1.setDisable(true);
        }
        catch (Exception e)
        {
            System.out.println("Error in Displaying Photo!!!");
        }
        
    }
}

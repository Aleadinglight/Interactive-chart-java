package Chart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;

public class Draw {
    private static String title;
    private static String keyword;
    private static ArrayList<Integer> data;
    // This newLink will be used to send signal back to the main program
    // There is 3 cases:
    //  	- "-1": this is a back signal, meaning going back to the previous chart
    //		- "-2": this is a signal meaning that the program was ended by user
    //   	- "some other number": this is the column that the user clicked, we will display data in that column
    private String newLink="-2";

    public void setData(ArrayList<Integer> newData, String newTitle, String newKeyword){
    	this.title = newTitle;
    	this.keyword = newKeyword;
    	this.data = new ArrayList<Integer>(newData);
    }


    public String run() {
    	Stage stage = new Stage();
    	title= getTitle();

    	//System.out.println(this.title);
        stage.setTitle(this.title);
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        // This button send the "Back" signal, which is a -1;
        Button button = new Button("Back");
        button.setOnMouseClicked(event -> {
        	newLink="-1";
        	stage.close();
        });
        // Dont let the user go back if there is no more data above this chart
        if (this.keyword.equals("Year")){
        	button.setDisable(true);
        }
        bc.setTitle(this.title);
        yAxis.setLabel("kWh");
        xAxis.setLabel(this.keyword);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Energy used");
        Integer j = new Integer(10);

        for (int i=0; i<data.size(); i++){
        	series1.getData().add(new XYChart.Data(""+(i+1), data.get(i)));
        }
        bc.getData().addAll(series1);
        // Add event to all the columns
        for (XYChart.Series <String,Number> serie: bc.getData()){
            for (XYChart.Data<String, Number> item: serie.getData()){
                item.getNode().setOnMousePressed((MouseEvent event) -> {
                    // "Day" is the last type of data, meaning we cant move any futher
                	// if it's not Day then we can go futher
                	if (!this.keyword.equals("Day")){
                    	newLink = item.getXValue();
                    	stage.close();
                    }
                });
            }
        }

        BorderPane border = new	BorderPane();
        border.setCenter(bc);
        border.setBottom(button);
        Scene scene  = new Scene(border,1200,800);
        stage.setScene(scene);
        stage.showAndWait();

        return newLink;
    }

    public String getTitle() {
		return title;
	}
}
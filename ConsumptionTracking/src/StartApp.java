import java.util.ArrayList;
import java.io.File;
import java.io.FilenameFilter;
import Reader.ReadFile;
import Chart.Draw;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Chart.Draw;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.lang.StringBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
public class StartApp extends Application	{

	public static void Plot(){
		String AddLink="DATA";
		StringBuilder DataLink= new StringBuilder("D:/ProjectJava");
		ArrayList<Integer> newData = new ArrayList<Integer>();
		String newTitle="The consumption tracking of ";
		String newKeyword="DATA";
		ReadFile newReader = new ReadFile();

		while (true){
			// Modify the link to data
			String oldKeyword=newKeyword;
			if (AddLink.equals("-2"))
				break;
			if (AddLink.equals("-1")){
				// This whole step is just to cut the link if there is a "back" signal
				// and update the AddLink so we can append it in the descriptions
				AddLink="";
				for (int i=DataLink.length()-1; i>=0; i--){
					if (DataLink.charAt(i)=='/'){
						DataLink.deleteCharAt(i);
						break;
					}
					else
						DataLink.deleteCharAt(i);

				}
				for (int i=DataLink.length()-1; i>=0; i--)
					if (DataLink.charAt(i)=='/')
						break;
					else
						AddLink=AddLink+DataLink.charAt(i);
				AddLink = new StringBuffer(AddLink).reverse().toString();

				// this is just the keyword in the display of the chart
				if (newKeyword.equals("Month")){
					newKeyword="Year";
					oldKeyword="Data";
				}
				else if (newKeyword.equals("Week")){
					newKeyword="Month";
					oldKeyword="Year";
				}
				else if (newKeyword.equals("Day")){
					newKeyword="Week";
					oldKeyword="Month";
				}
			}
			else{
				// This means the user clicked into a column in the chart, we will update the chart to
				// display the data type stored in that column.

				// Add the link to the DataLink
				DataLink.append("/"+AddLink);
				// If the previous keyword is Year then when user clicked in a column to choose a year
				// we will have to display the months of that year.
				// Same logic for the others.
				if (newKeyword.equals("DATA"))
					newKeyword="Year";
				else if (newKeyword.equals("Year"))
					newKeyword="Month";
				else if (newKeyword.equals("Month"))
					newKeyword="Week";
				else if (newKeyword.equals("Week"))
					newKeyword="Day";
			}

			int maxVal=0;
			// maxVal is the numbers of data we need to display
			// There is 6 years in this database
			if (newKeyword.equals("Year")){
				maxVal=6;
			}
			// There is 12 months per year
			if (newKeyword.equals("Month")){
				maxVal=12;
			}
			// There is 4 weeks per month
			if (newKeyword.equals("Week")){
				maxVal=4;
			}
			// There is  7 days per week
			if (newKeyword.equals("Day")){
				maxVal=7;
			}

			// Clear the old data and update the new one
			newData.clear();
			for (int i=1; i<=maxVal; i++){
				newData.add(newReader.GetData(DataLink.toString()+"/"+i));
			}

			// Create and display the chart
			newTitle="The consumption tracking of this "+oldKeyword.toLowerCase()+" "+AddLink+" based on "+newKeyword.toLowerCase()+"s";
			Draw a = new Draw();
			a.setData(newData, newTitle, newKeyword);
			AddLink = a.run();
		}
	}

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Start Application");
        Label text = new Label();
        text.setText("Welcome, this app let you view the chart\n and you can even interact with it! \n Sound goods?");
        text.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 20));

        Button btn = new Button();
        btn.setText("Get started!");
        btn.setOnAction(e->Plot());

        VBox root = new VBox();
        root.getChildren().addAll(text, btn);
        root.setAlignment(Pos.CENTER);
        primaryStage.setScene(new Scene(root, 500, 200));
        primaryStage.show();
    }
}
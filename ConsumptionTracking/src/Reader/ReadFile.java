package Reader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	private String inputFile="data.txt";

	public int GetData(String link){
		try(BufferedReader input = new BufferedReader(new FileReader(link+"/"+inputFile))){
			String data = input.readLine();
			return Integer.parseInt(data);
		}
		catch (FileNotFoundException ex1){
			return -1;
		}
		catch (IOException ex2){
			return -2;
		}
	}

}

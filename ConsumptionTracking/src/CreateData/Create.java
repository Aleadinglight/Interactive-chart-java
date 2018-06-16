package CreateData;
import java.io.File;
import java.util.Random;
import java.io.*;
public class Create {
	public static void make(String currDir, int i){
		String nextDir = currDir+"/"+i+"/";
		File dir = new File(nextDir);
		dir.mkdirs();
	}

	public static void put(String currDir, int i, int value){
		String nextDir = currDir+"/"+i+"/";
		File file = new File(nextDir+"data.txt");
		try{
			file.createNewFile();
			PrintWriter output = new PrintWriter(new FileOutputStream(nextDir+"data.txt"), true);
			output.print(value);
			output.close();
		}
		catch (FileNotFoundException ex1) {
			System.out.print("File Not Found!");
		}
		catch (IOException ex2){
			System.out.print("IOException!");
		}
	}

	public static void main(String[] args) {
		String base = "D:/ProjectJava/DATA", yearDir;
		Random rand = new Random();
		File file;
		for (int year=2012; year<=2017; year++){
			yearDir = base+"/"+year+"/";
			make(base, year);
			int sumMonth=0;
			for (int month=1; month<=12; month++){
				String monthDir = yearDir+"/"+month+"/";
				make(yearDir, month);
				int sumWeek=0;
				for (int week=1; week<=4; week++){
					String weekDir = monthDir+"/"+week+"/";
					make(monthDir, week);
					int sumDay=0, ran;
					for (int day=1; day<=7; day++){
						make(weekDir, day);
						ran=rand.nextInt(50)*10+50;
						sumDay=sumDay+ran;
						put(weekDir, day, ran);

					}
					put(monthDir, week, sumDay);
					sumWeek=sumWeek+sumDay;
				}
				put(yearDir, month, sumWeek);
				sumMonth=sumMonth+sumWeek;
			}
			put(base, year, sumMonth);
		}
		System.out.println("Finished.");
	}

}

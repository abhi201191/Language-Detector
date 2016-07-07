import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DictionaryCreator {

	public void createDictionary(int noOfDictionary, List<String> languages) throws IOException {
		Scanner sc = new Scanner(System.in);
		long[] wordCount = new long[noOfDictionary];
		long totalWords = 0;
		
		for (int i = 0; i < noOfDictionary; i++) {
			System.out.println("\nEnter File Name corresponding to " + languages.get(i) + ": ");
			String name = sc.next();
			String currentLine;
			String[] tokens;
			HashMap<String, Double> dict = new HashMap<String, Double>();
			File fname = new File(name);

			try {
				BufferedReader br = new BufferedReader(new FileReader(fname));

				while ((currentLine = br.readLine()) != null) {
					tokens = currentLine.split("\t");
					
					if(Double.parseDouble(tokens[2]) != 0)
					{
						dict.put(tokens[1], Double.parseDouble(tokens[2]));
						wordCount[i] += Integer.parseInt(tokens[2]);
					}
				}
				br.close();
			}

			catch (IOException e) {
				System.out.println("Exception: " + e);
			}
			
			PrintWriter w = new PrintWriter("language" + (i + 1));
			for(String s: dict.keySet())
			{
				dict.put(s, (Math.log(dict.get(s) / wordCount[i])));
				w.print(s + "\t");
				w.println(dict.get(s));
			}
			
			totalWords += wordCount[i];
			System.out.println(languages.get(i) + " dictionary successfully created...");
			w.close();
		}
	
		for(int i = 0; i < noOfDictionary ; i++)
		{
			LanguageDetectorSimulator.classProb[i] = (double) wordCount[i] / (double) totalWords;
		}
	}
}

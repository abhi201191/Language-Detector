import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LanguageDetector {

	public double[] detectLanguage(String fileName, int noOfLanguages)
			throws IOException {
		File fname = new File(fileName);
		String currentLine;
		String tokens[];
		String dictTokens[];
		HashMap<String, Double> dict = new HashMap<String, Double>();
		
		double probabilities[] = new double[noOfLanguages];

		try {

			for (int i = 0; i < noOfLanguages; i++) {

				BufferedReader br = new BufferedReader(new FileReader(fname));
				BufferedReader br2 = new BufferedReader(new FileReader(
						"language" + (i + 1)));

				while ((currentLine = br2.readLine()) != null) {
					dictTokens = currentLine.split("\t");
					dict.put(dictTokens[0], Double.parseDouble(dictTokens[1]));
				}

				currentLine = "";
				while ((currentLine = br.readLine()) != null) {
					tokens = currentLine.split(" ");
					for (int j = 0; j < tokens.length; j++) {
						if (dict.containsKey(tokens[j])) {
							probabilities[i] += dict.get(tokens[j]);
						}
					}
					
					probabilities[i] += (Math.log(LanguageDetectorSimulator.classProb[i]));
					probabilities[i] = Math.exp(probabilities[i]);
				}
				br.close();
				br2.close();
			}

		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}
		return probabilities;
	}
}

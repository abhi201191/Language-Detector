import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LanguageDetectorSimulator {

	public static int noOfLanguages;
	public static double classProb[];
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int choice, repeat = 1;
		
		System.out.println("Enter number of languages: ");
		noOfLanguages = sc.nextInt();
		String fileName;
		double probability[] = new double[noOfLanguages];
		List<String> languages = new ArrayList<String>();	
		classProb = new double[noOfLanguages];
		
		System.out.println("\nEnter Languages Name: ");
		for(int i = 0; i < noOfLanguages ; i++)
		{
			System.out.println("Language " + (i + 1) + ": " );
			languages.add(sc.next());
		}

		while (repeat == 1) {
			System.out.println("\nMenu: ");
			System.out.println("1. Read Corpus and Create/Update Dictionary");
			System.out.println("2. Detect Language");
			System.out.println("3. Exit");
			System.out.println("Enter choice:");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				DictionaryCreator dc = new DictionaryCreator();
				dc.createDictionary(noOfLanguages, languages);
				break;
				
			case 2:
				System.out.println("\nEnter Test File Name: ");
				fileName = sc.next();
				LanguageDetector ld = new LanguageDetector();
				probability = ld.detectLanguage(fileName, noOfLanguages);
				double totalProb = 0;
				
				for(int i = 0; i < noOfLanguages ; i++)
				{
					totalProb += probability[i];
				}
				
				for(int i = 0 ; i < noOfLanguages ; i++)
				{
					System.out.println("\nProbability of being language " + languages.get(i) + ": " + (probability[i] / totalProb));
				}
				break;
				
			case 3:		
				repeat = 0 ;
				System.exit(0);
				break;

			default:
				System.out.println("Enter Correct choice");
				break;
			}
		}
	}
}
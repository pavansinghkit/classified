package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import service.ClassifiedService;
import service.impl.ClassifiedServiceImpl;
import until.enumeration.CategoryType;
import util.Classified;

public class ClassifiedApp {

	private static String userName;

	// ********************create classified***************//

	private static void createClassified(ClassifiedService classifiedService, Scanner scan) {
		String s = "N";
		int cont = 0;
		do {
			Classified classified = new Classified();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// This can read input from
																								// anywhere
				for (CategoryType cattype : CategoryType.values()) {
					System.out.println(String.format("Press %d to select category %s", cattype.ordinal() + 1,
							cattype.getCategoryType()));
				}
				System.out.println("Please select Category from below:");
				int selCat = Integer.parseInt(reader.readLine()); // to convert string into int
				classified.setCategory(CategoryType.getCategoryType(selCat));
				// System.out.println("You have selected category: ");
				System.out.println("Please enter price for your product");
				classified.setPrice(Double.parseDouble(reader.readLine()));
				System.out.println("Please enter Title");
				classified.setTitle(reader.readLine());
				
				if (classified == null || classified.getTitle().isEmpty()) {
					System.out.println("You can't skip title");
					return;
				}
				
				System.out.println("Please Add some discription of your product");
				classified.setDescription(reader.readLine());
				System.out.println("Please enter your user_name");
				classified.setCreatedBy(reader.readLine());
				System.out.println("Please wait for a moment, we are publishing your classified....");
				classified.setCreatedAt(Calendar.getInstance().getTime());
				Classified savedClassified = classifiedService.createClassified(classified);
				System.out.println(savedClassified);
				System.out.println("Wow! Your classified added successfully");
				System.out.println("Do you want to add one more classified: y/n");
				s = scan.next();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Please enter valid details otherwise we will block you");
			}
			cont++;
		} while ("Y".equalsIgnoreCase(s));
		System.out.println("Thanks! you have added " + cont + " Classifieds");
	}

	// ***************print all classified for admin**************//
	
	private static void printClassifiedList(ClassifiedService classifiedService) {
		System.out.println(classifiedService.getClassifiedList());
	}

	// *****************update classified***********************//
	
	private static void updateClassified(ClassifiedService classifiedService, Scanner scan) {
		String s = "N";
		int contc = 0;
		do {
			Classified classified = new Classified();
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));// This can read input from
																								// anywhere
				System.out.println("Please enter your user_name : ");
				classified.setCreatedBy(reader.readLine());
				List<Classified> classifiedList = classifiedService
						.getClassifiedListByUserName(classified.getCreatedBy());
				if (classifiedList == null || classifiedList.isEmpty()) {
					System.out.println("No classified found for user name : " + classified.getCreatedBy());
					return;
				}
				System.out.println(classifiedList);
				System.out.println("Above are available classifieds posted by user : " + classified.getCreatedBy());
				System.out.println("Please select classifiedId you want to update: ");
				int sc = scan.nextInt();
				classified.setClassifiedId(sc);
				System.out.println("Please updateCategory from below:");
				for (CategoryType cattype : CategoryType.values()) {
					System.out.println(String.format("Press %d to select category %s", cattype.ordinal() + 1,
							cattype.getCategoryType()));
				}
				int selCat = Integer.parseInt(reader.readLine()); // to convert string into int
				classified.setCategory(CategoryType.getCategoryType(selCat));
				System.out.println("Please update price for your product");
				classified.setPrice(Double.parseDouble(reader.readLine()));
				System.out.println("Please update Title");
				classified.setTitle(reader.readLine());
				System.out.println("Please update discription of your product");
				classified.setDescription(reader.readLine());
				classified.setCreatedAt(Calendar.getInstance().getTime());

				classified.setModifiedBy(classified.getCreatedBy());
				List<Classified> classifiedListModifiedBy = classifiedService
						.getClassifiedListByUserName(classified.getModifiedBy());
				
				
				// classified.setStatus(ClassifiedStatus.ACTIVE);
				Classified savedClassified = classifiedService.updateClassified(classified);
				System.out.println(savedClassified);
				System.out.println("Do you want to update one more classified: y/n");
				s = scan.next();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Please enter valid details otherwise we will block you");
			}
			contc++;
		} while ("Y".equalsIgnoreCase(s));
		System.out.println("Thanks! ");
	}

	//********************Main method*********************//
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Pattern oldDelimiter = scan.delimiter();
		scan.useDelimiter("\\r\\n|[\\n\\x0B\\x0C\\r\\u0085\\u2028\\u2029]");
		scan.useDelimiter(oldDelimiter);
		System.out.println("***** Welcome to classified service *****");
		System.out.println();
		System.out.println("Press 1 to see classified list!");
		System.out.println("Press 2 to create new classified!");
		System.out.println("Press 3 to update classified!");
		int actionNo = scan.nextInt();
		ClassifiedService classifiedService = new ClassifiedServiceImpl();
		switch (actionNo) {
		case 1:
			printClassifiedList(classifiedService);
			break;
		case 2:
			createClassified(classifiedService, scan);
			break;
		case 3:
			updateClassified(classifiedService, scan);
			break;
		default:
			System.out.println("You have selected Invalid option !");
		}
		scan.close();
	}
}

package app;

import java.util.*;

import service.ClassifiedService;
import service.impl.ClassifiedServiceImpl;
import util.Classified;

public class ClassifiedApp {
	
	private static void printClassifiedList(ClassifiedService classifiedService){
		System.out.println(classifiedService.getClassifiedList());
	}
	private static void createClassified(ClassifiedService classifiedService, Scanner scan){
		System.out.println("how many products you want to register");
		int n = scan.nextInt();
//
		Classified classified = new Classified();
		for (int i = 0; i < n; i++) {
			try {
				System.out.println("Please enter ID in integer");
				classified.setClassifiedId(scan.nextInt());
				System.out.println("Please enter Category");
				classified.setCategory(scan.next());
				System.out.println("Please enter Title");
				classified.setTitle(scan.next());
				System.out.println("Please enter price in double");
				classified.setPrice(Double.parseDouble(scan.next()));
				System.out.println("Please enter discription of yr product");
				classified.setDescription(scan.next());
				System.out.println("Please enter your name");
				classified.setCreatedBy(scan.next());

				classified.setCreatedAt(Calendar.getInstance().getTime());
				
				// classified.setStatus(ClassifiedStatus.ACTIVE);
				Classified savedClassified = classifiedService.saveOrUpdate(classified);
				System.out.println(savedClassified);

			} catch (Exception e) {
				// TODO: handle exception\
				//e.printStackTrace();
				System.out.println("Please enter valid details otherwise we will block you");
			}
		}
	}

	public static void main(String[] args) {
		
	
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to classified service");
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
			System.out.println("Invalid option selected!");
		}
		 scan.close();

	}
	private static void updateClassified(ClassifiedService classifiedService, Scanner scan) {
		// TODO Auto-generated method stub
		
	}

}

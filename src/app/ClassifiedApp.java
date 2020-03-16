package app;

import java.util.*;

import service.ClassifiedService;
import service.impl.ClassifiedServiceImpl;
import util.Classified;

public class ClassifiedApp {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to classified service");

		ClassifiedService classifiedService = new ClassifiedServiceImpl();

		System.out.println("how many products you want to register");
		int n = scan.nextInt();

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
				e.printStackTrace();
				System.out.println("Please enter valid details otherwise we will block you");
			}
		} scan.close();

	}

}

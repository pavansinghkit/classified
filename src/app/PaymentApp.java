package app;
import java.util.*;

import service.PaymentService;
import service.impl.PaymentServiceImpl;
import until.enumeration.CategoryType;
import until.enumeration.PaymentType;
import util.Classified;

public class PaymentApp {

	public static void main(String[] args) {
		Classified classified = new Classified();//new obj
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (System.in);
		System.out.println("Welcome to payment Service");
		PaymentService paymentService= new PaymentServiceImpl();
		//get classifieds from intrest table if classifiedId available proceed
		System.out.println("Please enter classifiedID");
		int cID = scan.nextInt();
		classified.setClassifiedId(cID); //get cID
		System.out.println("Welcome payment gatway...");
		System.out.println();
		System.out.println("Please select payment options for classifiedID =  "+cID);
		for (PaymentType type : PaymentType.values()) {
			System.out.println(String.format("Press %d for payement using %s", type.ordinal()+1, type.getPaymentMode()));
			}
		int pID = scan.nextInt();
		String msg= paymentService.doPayment(cID, pID);
		System.out.println(msg);
		scan.close();
	}

}

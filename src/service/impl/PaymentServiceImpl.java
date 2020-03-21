package service.impl;

import repository.ClassifiedRepository;
import repository.impl.ClassifiedRepositoryImpl;
import service.PaymentService;
import until.enumeration.ClassifiedStatus;
import until.enumeration.PaymentType;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public String doPayment(int classifiedId, int paymentTypeId) {
		String response = null;
		// *check if entered classified is present in DB and unsold
		String paymentMode = PaymentType.getPaymentMode(paymentTypeId);
		if (paymentMode == null) {
			response = "Please enter valid payment option";
		} else {
			ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
			classifiedRepository.updateStatus(classifiedId, ClassifiedStatus.SOLD.toString());
			response = String.format("Payment successfull with %s", paymentMode);
		}
		return response;
		
		
		
	}
}

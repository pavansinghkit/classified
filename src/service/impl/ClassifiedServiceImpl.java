package service.impl;

import java.util.List;

import service.ClassifiedService;
import util.Classified;

public class ClassifiedServiceImpl implements ClassifiedService {

	@Override
	public Classified saveOrUpdate(Classified classified) {
		System.out.println(classified);
		System.out.println("saveOrUpdate Classified ********");
		return classified;
	}

	@Override
	public List<Classified> getClassifiedList() {
		// TODO Auto-generated method stub
		return null;
	}

}

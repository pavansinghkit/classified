package service.impl;

import java.util.List;

import repository.ClassifiedRepository;
import repository.impl.ClassifiedRepositoryImpl;
import service.ClassifiedService;
import util.Classified;

public class ClassifiedServiceImpl implements ClassifiedService {
	//save and update
	@Override
	public Classified saveOrUpdate(Classified classified) {
		System.out.println(classified);
		System.out.println("saveOrUpdate Classified ********");
		return classified;
	}
	//print
	@Override
	public List<Classified> getClassifiedList() {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.getClassifiedList();
	}
	// create
	@Override
	public Classified createClassified(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.createClassified(classified);
	}
}

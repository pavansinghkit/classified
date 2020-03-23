package service.impl;

import java.sql.SQLException;
import java.util.List;

import repository.ClassifiedRepository;
import repository.impl.ClassifiedRepositoryImpl;
import service.ClassifiedService;
import util.Classified;

public class ClassifiedServiceImpl implements ClassifiedService {

	// create
	@Override
	public Classified createClassified(Classified classified) throws SQLException{
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.createClassified(classified);
	}

	// print
	@Override
	public List<Classified> getClassifiedList() throws SQLException {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.getClassifiedList();
	}
	// print for specific User
		@Override
		public List<Classified> getClassifiedListByUserName(String userName) {
			ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
			return classifiedRepository.getClassifiedListByUserName(userName);
		}

	// update
	@Override
	public Classified updateClassified(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.updateClassified(classified);
	}

	@Override
	public Classified updateClassifiedCategory(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.updateClassifiedCategory(classified);
	}

	@Override
	public Classified updateClassifiedPrice(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.updateClassifiedPrice(classified);
	}

	@Override
	public Classified updateClassifiedTitle(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.updateClassifiedTitle(classified);
	}

	@Override
	public Classified updateClassifiedDescription(Classified classified) {
		ClassifiedRepository classifiedRepository = new ClassifiedRepositoryImpl();
		return classifiedRepository.updateClassifiedDescription(classified);
	}
}

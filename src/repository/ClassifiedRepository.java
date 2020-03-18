package repository;

import java.util.List;

import util.Classified;

public interface ClassifiedRepository {
	
	List<Classified> getClassifiedList(); //print

	Classified createClassified(Classified classified); //create

}

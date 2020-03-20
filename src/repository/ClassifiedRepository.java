package repository;

import java.util.List;

import util.Classified;

public interface ClassifiedRepository {

	Classified createClassified(Classified classified); //create
	Classified updateClassified(Classified classified); //update
	List<Classified> getClassifiedList(); //print
	List<Classified> getClassifiedListByUserName(String userName); //print for specific user
	void updateStatus(Integer classifiedId, String status);
}

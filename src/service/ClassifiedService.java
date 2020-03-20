package service;

import java.util.List;

import util.Classified;

public interface ClassifiedService {
	Classified updateClassified(Classified classified);  //update
	List<Classified> getClassifiedList();      //print
	List<Classified> getClassifiedListByUserName(String userName);     //print for user
	Classified createClassified (Classified classified);  //create
}


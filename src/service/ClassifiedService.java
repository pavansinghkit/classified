package service;

import java.util.List;

import util.Classified;

public interface ClassifiedService {
	Classified saveOrUpdate(Classified classified);
	List<Classified> getClassifiedList();
	
}
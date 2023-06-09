package QLNV.services;

import java.util.List;
import java.util.Optional;

import QLNV.models.Depart;

public interface DepartService {

	void deleteAll();

	void deleteAll(List<Depart> entities);

	void delete(Depart entity);

	void deleteById(Integer id);

	long count();

	List<Depart> findAllById(List<Integer> ids);
	List<Depart> findByNameLikeOrderByName(String name);

	Iterable<Depart> findAll();

	boolean existsById(Integer id);

	Optional<Depart> findById(Integer id);

	List<Depart> saveAll(List<Depart> entities);

	Depart save(Depart entity);

	
}

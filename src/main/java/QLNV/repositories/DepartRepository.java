package QLNV.repositories;

import org.springframework.stereotype.Repository;

import QLNV.models.Depart;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface DepartRepository extends CrudRepository< Depart, Integer> {
	List<Depart> findByNameLikeOrderByName( String name);
}

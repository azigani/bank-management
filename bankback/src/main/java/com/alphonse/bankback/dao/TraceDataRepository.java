package com.alphonse.bankback.dao;

import java.sql.Date;
import java.util.List;

import com.alphonse.bankback.entities.TraceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceDataRepository extends CrudRepository<TraceData, Long> {
	/**
	 * Liste des traces avant la date que nous aurons précisé
	 * @param date
	 * @return
	 */
	List<TraceData> findByDateLessThan(Date date);

	/**
	 * Suppression des traces avant la date que nous aurons précisé
	 * 	 * @param date
	 * @param date
	 */
	void deleteByDateLessThan(Date date);
}

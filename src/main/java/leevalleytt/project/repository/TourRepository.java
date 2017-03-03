package leevalleytt.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import leevalleytt.project.entity.Tour;

public interface TourRepository extends CrudRepository<Tour, Integer> {

	public Tour findByName(String string);
	public List<Tour> findAll();
	public List<Tour> findByCompleted(int completed);
	
	@Modifying
	@Query(value="UPDATE Tour SET COMPLETE = :complete WHERE TOUR_ID = :id",nativeQuery=true)
	public void updateCreditById(@Param("complete") double amount, @Param("id") int id);
	
	@Modifying
	@Query(value="UPDATE Tour SET AMOUNT_BOOKED =:amountBooked WHERE TOUR_ID = :id",nativeQuery=true)
	public void updateAmountBooked(@Param("amountBooked") int amountBooked,@Param("id") int id);
	
	


}

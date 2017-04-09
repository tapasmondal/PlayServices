package com.play.app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.play.app.entity.Game;
import com.play.app.entity.PersonGame;
import com.play.app.entity.PersoneGameId;

public interface PersonGameRepository extends CrudRepository<PersonGame, PersoneGameId> {

	List<PersonGame> findByPersonIdAndTournamentIdAndGameNo(Integer personId, Integer tournament, Integer gameNo);

	List<PersonGame> findByTournamentIdAndGameNo(Integer tournamentId, Integer gameNo);

	List<PersonGame> findByTournamentIdAndGameNoOrderByPersonIdAsc(Integer tournamentId, Integer gameNo);
	
	List<PersonGame> findByGameIn(List<Game> gameList);

	List<PersonGame> findByPersonIdAndTournamentIdOrderByGameNoAsc(Integer personId, Integer tournamentId);
	
	
	//@Query(value="select pg from  PersonGame pg where pg.personId = ?1 and tournamentId=?2 and game_No in ( ?3)")
	List<PersonGame> findByPersonIdAndTournamentIdAndGameNoIn(Integer personId, Integer tournamentId, List<Integer> gameNos);
	
	@Modifying
	@Query(value="insert into persongame (createdby, createddate, game_no, lastmodifiedby, lastmodifieddate, modifiedcount, participated, personid, points, result, teamaId, teambid, totalpoints, tournamentid) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, ?13, ?14)", nativeQuery=true)
	void inserPersonGame(String createdBy,Date createdDate, Integer game_No, String lastModifiedBy,Date lastModifiedDate,Integer modifiedCount,boolean participated,Integer personId, double points,String result, Double teamAId, Double teamBId,Double totalPoints, Integer tournamentId);
}

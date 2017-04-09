package com.play.app.service;

import com.play.app.model.AdminUpdateModel;
import com.play.app.model.PersonModel;
import com.play.app.model.VoteInfoModel;

public interface PlayUpdateService {


	
	VoteInfoModel updatePersonsGameByPerson(VoteInfoModel voteInfoModel);
	
	//for unlocking
	AdminUpdateModel updateGameByAdmin(AdminUpdateModel adminUpdateModel);
	
	//calculation score and update next game counter
	AdminUpdateModel updateGameStatusByAdmin(AdminUpdateModel adminUpdateModel);
	
	void autoLockingGame(); 
	
    void updatedGamePersonforNonParticipation(AdminUpdateModel adminUpdateModel);
    
    public PersonModel updatePersonDataByAdmin(PersonModel personModel);
	
}

package com.play.app.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.play.app.service.PlayUpdateService;

@Component
public class LockScheduler {
	
	
	@Autowired
	PlayUpdateService playUpdateService;
	
	@Scheduled(fixedRate=20000)
    public void printMessage() {
        //System.out.println("Begin:........................Auto Locking Game  by Spring schedule...................");
        playUpdateService.autoLockingGame();
        //System.out.println("End:........................Auto Locking Game  by Spring schedule.....................");
        
    }

}
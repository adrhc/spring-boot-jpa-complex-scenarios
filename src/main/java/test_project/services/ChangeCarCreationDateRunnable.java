package test_project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by adriana on 30-Nov-15.
 */
@Component
public class ChangeCarCreationDateRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ChangeCarCreationDateRunnable.class);

    @Autowired
    private AppService8 appService8;

    @Override
    public void run() {
        try {
            appService8.changeCarCreationDate();
        } catch (ConcurrencyFailureException e) {
            logger.error(e.getMessage());
        }
    }

    public void runChangeCarCreationDate() {
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(this);
        es.shutdown();
        try {
            es.awaitTermination(1L, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
//        Thread carCreationDateThread = new Thread(carCreationDateRunnable);
//        carCreationDateThread.start();
//        try {
//            carCreationDateThread.join();
//        } catch (InterruptedException e) {
//            logger.error(e.getMessage(), e);
//        }
    }
}

package test_project.util;

import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test_project.services.AppService;

/**
 * Created by adr on 11/15/15.
 */
@Component
public class PrintUtil {
    private static final Logger logger = LoggerFactory.getLogger(PrintUtil.class);

    @Autowired
    private AppService appService;

    public void printPersonByIdThenFirstName(Long id, String firstName) {
        try {
            logger.debug("getGeekById {}", id);
            logger.debug(appService.getGeekById(id).toString());
        } catch (LazyInitializationException e) {
            logger.error(e.getMessage());
        }
        try {
            logger.debug("getGeekByFirstNameWithCriteria {}", firstName);
            logger.debug(appService.getGeekByFirstNameWithCriteria(firstName).toString());
        } catch (LazyInitializationException e) {
            logger.error(e.getMessage());
        }
    }
}

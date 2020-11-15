package test_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import test_project.services.*;
import test_project.util.PrintUtil;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableTransactionManagement
public class App implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    @Autowired
    private PrintUtil printUtil;
    @Autowired
    private AppService appService;
    @Autowired
    private AppService2 appService2;
    @Autowired
    private AppService3 appService3;
    @Autowired
    private AppService4 appService4;
    @Autowired
    private AppService6 appService6;
    @Autowired
    private AppService7 appService7;
    @Autowired
    private AppService8 appService8;
    @Autowired
    private AppService9 appService9;
    @Autowired
    private AppService10 appService10;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }

    public void run(String... args) {
        logger.debug("****************************************************");
        logger.debug("works only with @SpringBootApplication on App.class!");
        logger.debug("****************************************************");
        logger.debug("begin setup 1");
        appService.persistPerson();
        appService.persistGeeks();
//        appService.showGeeks();
        appService.persistGeekWithIdCardAndPhones();
//        appService.showGeekAdr();
//        printUtil.printPersonByIdThenFirstName(5L, "Adr");// Adr geek too
//        appService.showPersons();
        appService.saveProjects();
//        appService.showPersons12();
        appService.showGeekByIdUsingHQL(4L);
//        logger.debug("****************************************************");
//        logger.debug("begin setup 2");
//        appService.saveEmployeeWithPhone();
//        appService.saveDepartmentAndSeniorities();
//        appService.saveEmployeeWithPhoneTypeEnum();
//        appService.saveEmployeeWithVacationEntry();
//        appService.saveProfessor();
//        appService.saveDepartmentAndEmployeesByName();
//        appService.saveEmployeeWithEmbeddedName();
//        appService.saveDepartmentAndEmployeesHavingEmbeddedName();
//        logger.debug("****************************************************");
//        logger.debug("begin appService2");
//        appService2.savePersonWithDepartmentJoinedOnPK();
//        appService2.showPerson3(1L);
//        appService2.savePersonWithDepartmentJoinedOkFK();
//        appService2.showPerson(1L);
//        appService2.saveDepartmentZ();
//        appService2.showPersonZ(1L);
//        logger.debug("****************************************************");
//        logger.debug("begin appService3");
//        appService3.doOneToManyWithJoinTable();
//        appService3.showDepartment3();
//        appService3.doOneToManyWithOrderColumn();
//        appService3.showPrintQueue();
//        logger.debug("****************************************************");
//        logger.debug("begin appService4");
//        appService4.doOneToManyMapKey();
//        logger.debug("****************************************************");
//        logger.debug("begin appService6");
//        appService6.createPersonWithMapKey();
//        appService6.showPerson6();
//        appService6.showPerson6WithEntityGraph();
//        appService6.showPerson6WithProgramaticEntityGraph();
//        appService6.checkPersonAddress1();
//        appService6.checkPersonAddress2();
//        appService6.checkPersonAddresses();
//        logger.debug("****************************************************");
//        logger.debug("begin appService7");
//        appService7.createPerson1();
//        appService7.createPerson2();
//        appService7.showPersons();
//        appService7.showPersonsName();
//        appService7.showPersonsNameAndCountry();
//        appService7.showPersonsNameAndCountryWithTuple();
//        appService7.showPersonsNameAndCountryWithSelectNewClass();
//        appService7.showPersonsNameAndPseudonims();
//        appService7.showOrderedAddressesLikePattern();
//        appService7.showPersonGroupedByGender();
//        appService7.createPets();
//        appService7.showPetGroupedByColor();
//        appService7.showPetWithStringBasedCriteriaQuery();
//        logger.debug("****************************************************");
//        logger.debug("begin appService8");
//        appService8.createCars();
//        appService8.findCarWithOptimisticLock();
//        appService8.findOwnerWithOptimisticLock();
//        try {
//            appService8.findWithOptimisticLockWhileChanged();
//        } catch (ConcurrencyFailureException e) {
//            logger.error(e.getMessage());
//        }
//        appService8.nonVersionedPessimisticReadLock();
//        appService8.versionedPessimisticReadLock();
//        appService8.versionedPessimisticReadLockWhileChanged();
//        appService8.versionedPessimisticForceIncrementLock();
//        logger.debug("****************************************************");
//        logger.debug("begin appService9");
//        appService9.createEmailMessage();
//        appService9.persistWithExistingPersonWrapper();
//        appService9.showEmailMessages();
//        appService9.emailMessageSenderSubjectAttachments();
//        appService9.emailMessageBodyAttachments();
//        appService9.person9MessagesAttachments();
//        appService9.person9Secrets();
//        appService9.person9Translations();
//        logger.debug("****************************************************");
//        logger.debug("begin appService10");
//        appService10.queryPerson9WithTuple();
//        appService10.searchPerson9WithFetch();
//        appService10.searchPerson9WithFetchMapKey();
//        appService10.searchPerson9WWhereExistsAttachTextLengthLess10();
        logger.debug("END run");
    }
}

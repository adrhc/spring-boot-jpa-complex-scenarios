# tutorial 1
http://www.javacodegeeks.com/2015/02/jpa-tutorial.html
mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=com.javacodegeeks.ultimate -DartifactId=jpa

### h2
JDBC URL jdbc:h2:./jpa-db tells H2 to
create the database file ./jpa-db.h2.db

check usage of the correct h2 jar:
mvn dependency:tree | grep h2
then use h2 console:
java -cp ~/.m2/repository/com/h2database/h2/1.3.176/h2-1.3.176.jar org.h2.tools.Shell -url jdbc:h2:./jpa-db
select * from T_PERSON;

CREATE DATABASE jpa;
GRANT ALL ON jpa.* TO 'jpa'@'%' IDENTIFIED BY 'jpa' WITH GRANT OPTION;
FLUSH PRIVILEGES;

# tutorial 2
http://www.java2s.com/Tutorials/Java/JPA/0650__JPA_Embeddable_KeyMapping.htm
https://wiki.eclipse.org/EclipseLink/Examples/JPA#JPA_2.0
https://wiki.eclipse.org/EclipseLink/Examples/JPA/2.0/Criteria

Enable hibernate log to file:
use jvm option: -Dorg.jboss.logging.provider=slf4j

Enable spring boot debug:
use program argument: --debug

Generate staticmetamodel:
http://docs.jboss.org/hibernate/orm/5.0/topical/html/metamodelgen/MetamodelGenerator.html

# J2EE 7 tutorial
https://docs.oracle.com/javaee/7/tutorial/

# thoughts-on-java
http://www.thoughts-on-java.org/jpa-21-overview/
http://www.thoughts-on-java.org/jpa-21-entity-graph-part-1-named-entity/
http://www.thoughts-on-java.org/jpa-21-entity-graph-part-2-define/

# radcortez: jpa-entity-graphs
http://www.radcortez.com/jpa-entity-graphs/

https://en.wikibooks.org/wiki/Java_Persistence

# Query DSL 
see https://www.baeldung.com/intro-to-querydsl
```
EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.querydsl.intro");
EntityManager em = entityManagerFactory.createEntityManager();
JPAQueryFactory queryFactory = new JPAQueryFactory(em);
QUser user = QUser.user;
User c = queryFactory.selectFrom(user).where(user.login.eq("David")).fetchOne();
```

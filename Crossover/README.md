Issue:One of the Unit Tests had minor errors in the condition for assertion:
@Test
public void getCategories() throws Exception {
	mockMvc.perform(get("/public/rest/category")).andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
			.andExpect(jsonPath("$[0].id", is(1)))
			.andExpect(jsonPath("$[0].name", is("immunology")))
			.andExpect(jsonPath("$[1].id", is(2)))
			.andExpect(jsonPath("$[1].name", is("pathology")))
			.andExpect(jsonPath("$[2].id", is(3)))
			.andExpect(jsonPath("$[2].name", is("endocrinology")))
			.andExpect(jsonPath("$[3].id", is(4)))
			.andExpect(jsonPath("$[3].name", is("microbiology'")))
			.andExpect(jsonPath("$[4].id", is(5)))
			.andExpect(jsonPath("$[4].name", is("neurology")));
}

SELECT * FROM journals.category;
'1', 'immunology'
'2', 'pathology'
'3', 'endocrinology'
'4', 'microbiology'
'5', 'neurology'
-------------------------------------
Design flaw: 
Too Many Responsibilities for Controller:
-Controller should only co-ordinate between the View and Model
-Controller should delegate the responsibility of invoking the Spring Data layer of JPARepository in our case to a Service. Chances are that when the application grows in size it may have to invoke multiple services to form a response entity.
-Services should also be cohesive, should have clearly defined boundry and responsibility so that it will remain a closed layer (as against open layer see 80/20 rule - see: https://technicalyorker.wordpress.com/2015/05/27/n-tier-architectural-pattern/). In a typical small sized application, it could be split either on the bases of activities on the domain model (i.e one service per repository - doing all the jobs for the repository) or One Service per Business Use case. 
-In Medium sized to very large applications, it would be wise to maintain two tiers of services; first based business use case and second exclusive to each domain model. Spring Data's JPARepository does allow to maintain custom implementation 
http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.custom-implementations
however its best to restrict it jobs to only CRUD Operations and let the Service take care of any business logic, else with the application growing in size - overtime a clear cut demarcation between a repository and service might cease to exist.
-------------------------------------
Static Methods in Controller: 
Snipper: public static String getFileName(long publisherId, String uuid) {...}
-In a Spring application, or atleast with Spring managed Beans - its better to delegate the responsibilities of manage instances (& life cycle) to the spring container (or Bean Factory). Operations on a classes whose instances are intented to be Spring managed must be done on instances, handled by the Container itself (by Autowiring or from a BeanFactory/Spring Appln. Context).
-There is also a tendency to create non managed instance (using new keyword), thus any autowiring will not work and application starts seeing NPException.
-------------------------------------
Controllers invoking Controllers: 
Snippet: File file = new File(PublisherController.getFileName(journal.getPublisher().getId(), journal.getUuid()));
This is another bad practise. A Controllers responsibility must be fine grained and must delegate the responsibility to a Service or Service facade(Orchestration - in Complex Systems).
-------------------------------------
InputStream is never closed:
Snippet: JournalController.java
-This is a very basic issue :). Typically it should be closed in the finally block (guaranteed) to handle both try's success & exception cases. tip: In order to avoid boilerplate make use of 'The try-with-resources Statement' feature of Java 1.7 see:https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html 
-------------------------------------
Entity Id Generation Strategies are not consistent:
Snippet: User: @GeneratedValue(strategy = GenerationType.IDENTITY)
- Advantage of a JPA is that is it almost RDBMS agnostic.
- Generation Type defaults to Generationtype.Auto which lets JPA Implementation (hibernate in our case) Select the best fit based on the underlying RDBMS.
- Identity strategy is supported by most, if not all RDBMSs. Hence if its better to keep it consistent even if the RDBMS is not expected to change in the future. 
-------------------------------------
Design Improvement:
-------------------------------------
Service Reuse/Application Rearchitecturing:
probably done for a person who wont have to work on angular js to upload it...........
Application Exposes Services to a client application and as RESTful Services.
For a medium to large sized application would be:
-The Application could be split into multiple self contained (micro) services. One service could expose the Model & Data as RESTful webservice (let's just call it the 'Service' module). The 'Client' Application could communicate with these 'Service's using JSON  on call of URIs the service exposes via RESTful WebService EndPoint Interfaces.
-Keep the 'Service' Module Stateless for scalability and load balancing (and also becuase its a Guidline for REST Services). 
-Spring provides the @RepositoryRestResource which will automatically expose the JPA as Hypermedia As an Engine Of Application State (HATEOS), one of the Guiding principles governing RESTful WebServices.
-These concepts form building blocks of a microservice architecture and if desired the whole application can go on for a refactor or modularization.
Further Info (Warning! a little off topic):
The applications('Service'(s) & 'Client' could scale independently and a change in the 'Service' wouldn't require any 'Client' code change or 'Client' deployment as far as the mutual contract isn't unaffected. With Spring boot Service can been deployed as another standalone Web Container instance. No Big-Bang Deployment required. Spring Pivotal provides Services such as Configuration Service, Discovery (Eureka) Service, Hysterix (Circuitbreaker and Dashboard), MicroProxy/API Gateway (zuul u-proxy). Load balancing can easily be introduces into this architecture using Ribbon. Security and other Aspects can be centralized with only the MicroProxy requiring to deal with it and the underlying services are free from these responsibility.
-------------------------------------
DDD:
-There is an not-so-new Industry trend called 'DDD' or 'Domain Driven Design' where the Domain Model (or Entity in case of JPA) plays more than just accessor and mutators, typically giving it the name 'Rich Model' (as against the Anemic Model). In Anemic Model data transfer is done by a Dummy DTO (now considered an anti pattern due to the very fact that it was invented to solve a problem which was never there in the first place). In rich model the Domain Model Entities can contain some business logic (or operations) which completely belongs to the busines model. It could even maintain member variables through the use @Transient (JPA ORM or Spring Data equallent annotations in case of ODMs - NoSQLs etc) to maintain state.

Design Principle:
Few Design Principles are being violated:

Program to an Interface not to an Implementation: 
It is typically better to write code targetting an interface (contract) rather than writing code otherwise. Technology is ever changing, spring could be gone someday or if we decide to move to something like EJB (JEE Compliant), only thing we might need to do is to write a new implmentation to our interface contracts.     
Single responsibility principle(SRP):
Controller has more than one reponsibility.(already mentioned)   
Common Closure Principle:             
e.g. ServiceException should be in the exception package. 

see https://technicalyorker.wordpress.com/2015/04/27/common-software-design-principles/
-------------------------------------
Too much hardcoding:
@RequestMapping(method = RequestMethod.GET, value = "/publisher/publish")
If the URI has to be changed it has to be changed in multiple places across the code. Its better to write a Constants class to define values like this.
-------------------------------------
Alternate Transfer Object:
@JsonIgnore is used in Entities and I would have done the same..............
-------------------------------------
Potential use tools like Lombok to avoid boiler plate code.
refer: https://projectlombok.org/





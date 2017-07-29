# Deal

## 10,000 feet overview: of Deal Application:

Deal is an application which helps its customers quote the best deals from various registered stores. e.g. Let's say we have two stores registered with the system.

1) Jacob's store (email id: deal.jacobs@mail.com)

2) Thomas's Store (email id: deal.thomas@mail.com)

We could have different items sold by these stores - few only by Jacobs, few by Thomas' and others sold by both Jacob and Thomas. Deal Application lists exhaustive list of the products registered with the system to a user and lets him choose the item he wants. Once he chooses to submit, system send out emails to respective store (based on whether the stores deal with the item or not.
for eg. following items are entered into our System database:
* Turkey Salad
* Chicken Salad
* Green Salad

let's just say:

* Only Jacobs Store sells Turkey Salad
* Only Thomas Store sells Chicken Salad
* But, both of them sell Green Salad.

Our System shows to its users all salad type and lets the user choose whatever product he wants. Assume, the user selects Turkey Salad (which incidently is sold by only Jacobs) and Green Salad (which is sold by both Jacobs & Thomas'). There will be two emails sent to two stores, one to Jacobs asking the best price quote for Turkey Salad & Green Salad. Thomas on the other hand gets an email asking him the best deal for only Green Salad. From there the customer and the store takes the deal forward.

### IMPORTANT:
There will be emails in CC and BCC.

* Bcc: The system admin (deal.deal@mail.com) will be in the BCC on all emails sent out of the system for tracking purposes.
* Cc: the customer (or the person who requests the quote for the products) will be in the CC list.


## 5,000 feet overview: Technical Overview
The application uses two email providers MailGun and SendGrid, both by means of its REST APIs. Request will be sent to its REST end points that triggers emails. The primary Service provider of choice is MailGun. It is however possible that MailGun email sending might fail for many reasons. e.g. MailGun Server is down for some reason. MailGun mandates its recipients to be registered as an Authorizatied recipient for it to send email under free tier.  

SendGrid, however doesn't have this restriction and is here to cover up email send failures. SendGrid acts as a failover provider to send Quote emails to the store. This pattern is commonly achieved by means of 'Circuit Breaker' Pattern. This in our case is made possible by virtue of Netflix Circuit Breaker called Hystrix.  

The application architecture is a "mini" microservice due to the very nature of services that are going to be added in the near future. Once Production ready it would have a number of services (like in any microservice) belonging to the category of Infrastructure Services (e.g Security Service, Auditing Service, Logging Service) and Functional Services (Quote Service, Product Service, inventory Service, Billing Service).

To provide these extension points to the application, the application has currently started off as three main services which will be extended as the application grows in magniture & functionality.

## 3,000 feet overview: Service Overview
Three services are:  (see Section: Design Motionvation below for considerations to this coding approach)

#### Deal Web
A Internet facing service and probably the only service one currently not hiding behind the protection of a a Private subnet. This service is very simple in nature. It has its presentation layer done with an SPA - Angular JS (a light weight MV* javascript framework; client browser does a decent job offloading heavy weight lifiting by Servers unlike in traditional MVCs, thus helping overall performance) which sends HTTP request to an API Gateway. API Gateway, a fall-through service (plain fall thru at present) is achieved by virtue of Spring Cloud 'Zuul' Microproxy. But as the application matures it does important tasks like talking to other Infrastructure Services such as Security Service for Authentication, Auditing Service, Catering to SSL needs etc before letting its request hit the Functional Services. The Zuul configuration which can be found in the \<service\>-\<environment\>.yml e.g dealweb-prod.yml.    

#### Deal Service

Deal Service will reside in a Private subnet. It uses a NAT Gateway to access internet but this Services like a lot of other services are hidden from any access from outside the VPC. DealService exposes REST API 'Service Contracts' as Service endpoints to the DealWeb for access. Deal Service has access to persistent store which is our case is MySQL Server or a Cloud Database such as any AWS RDS like Aurora for scalibility. It uses Spring abstraction over JPA using Spring Data JPARepository to access the underlying datastore. The ORM ensures the application is Database agnostic.

#### Email Service
This is a third Service that does the job of sending email. For demonstration purpose this service is exposed using "Swagger" as its API Documentation. API Documentation is an important aspect of the Microservice ecosystem in that it helps autogenerate stub code to minimise boilder plate code. We have used Spring's Feign Clients to avoid boilder plate code.

## 1,000 feet overview: Reality of Deployment

Application uses Spring Boot. Spring Boot introduced a new approach of deployable jar uses tomcat server embedded within it, an ideal approach for microservice which does one thing and that thing well. Typically one could re-write a service in a matter of two weeks if need be. Hence this deployable version doesn't really need a App/Web Server/Container and can by itself expose HTTP REST Endpoints quite naturally making it the ideal candidate for a Service in a microservice ecosystem. This approach makes Hetrogeneous Interoperability (Services can be written in multiple languages - like Scala or frameworks like Node.JS) a possibility.
Cloud platforms like Pivotal Cloud Foundry(PCF) offer these services to go multiple instance (scalable) in just matter of few clicks.

## 500 feet overview: Implementation
Any good Application follows design principles. (see https://technicalyorker.wordpress.com/2015/04/27/common-software-design-principles/)

and one such design principle: Common Closure Principle prescribes that all applications be contained in common packages. In our case we have:

* endpoints - which contains the endpoint interfaces and its implementation.
* service - which consists of the application services and implementation
* repository - Repository implementation of ORM e.g. JPA Repository
* domain model - application model. This is particularly important in application that follow Domain Driven Design (DDD) where the domain objects are not Anemic in nature. They not only drive the Domain Model, a lot of them work together to even make up the Business Model Objects of the System a reality. Smaller application go one futher level by exposing the domain model as full request lifecycle state objects.

Deal Common is one such Service that contains the domain model which is going to be reused. Larger Microservice ecosystems might not really need such common services. Consumers could be design their version of Model to only use the attributes relevant to them from Service contracts.

## 100 feet: Source
* Developer Guide: https://github.com/technicalyorker/deal/blob/master/DeveloperGuide.pdf
* code: https://github.com/technicalyorker/deal

## 0 feet: Hitting Ground

#### Setting up on local box:
$ git clone https://github.com/technicalyorker/deal.git

#### Understanding build jargons before performing one:
For Building/Installing one must understand a few simple keywords:
\<env\> can take the following values:
* local - target environment is local box
* prod - target environment is prod (In our case AWS EC2 instances)

\<service\> can be replaced with the following values:
* ds - denotes dealservice
* dw - denotes dealweb
* es - denotes emailservice
* all - denotes all services

\<deal root folder\>
* folder where the application has been cloned

#### Step 0: Setting up MySQL db (One time process)
Install mysql and set the root password as admin123 for easy testing (See Section: for more details on installation of MySQL db). If you want to use a different password it might require you to change the dealservice-\<env\>.yml for the application to use.

$ cd \<deal root folder\>/resources

$ mysql --user="root" --password="admin123" \< schema.sql

#### Step 1: Installing app:
Before running the build you must unzip the properties.zip. This file is password protected since it contains few credentials related to sending email. Though the mailing servers (MailGun & SendGrid) is only using the free tier its best to password protect it. Password will be emailed seperately.

$ unzip properties.zip

$ cd \<deal root folder\>/resources/scripts

$ ./build \<env\>

#### Step 2: Starting/Stopping Services:

After running Step 1 a folder named deal inside resources will be created

$ cd \<deal root folder\>/resources/deal

$ ./start \<service\>

$ ./stop \<services\>

## Testing Approach:
I had a bad day yesterday when my laptop crashed and I had to reinstall the whole OS. Hence I am only describing the testing approach here. Typically I would have written the test cases well before Implementing the Service Contracts. But due to time constraints it could probably be implemented on a need basis. Approach would have followed:

#### Repository Layer Testing:
Spring boot exposes its @Conditional Interface for conditional ApplicationContext Bean Loading. One such an interface is the @Profile which lets expose different profiles for different working environments. e.g test, dev, prod. In case of Deal Service, the EntityManager would be using an in-memory H2 DB which would be populated based on the scenarios to test and assertion would validate the actual values against expected value, for the build to go through to produce deployable artifacts.

#### EndPoint Layer Interfaces:
REST endpoints would be tested by Springs MockMVCBuilders Mocking the result of the Service EndPoints.

#### Service Layer Testing:
Service testing could be done by injecting appropriate beans created by mocking or profile based scenarios, asserting them from there on.

#### SPA Testing:
Testing tools such as Jasmine lets BDD to test functions. I wouldn't call myself an expert in this area right now. I have tried few samples on the same.

## Installing java on Amazon Linux AMI
$ wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u131-b11/d54c1d3a095b4ff2b6607d096fa80163/jdk-8u131-linux-x64.rpm

$ sudo yum localinstall jdk-8u131-linux-x64.rpm

## Appendix:
#### Installing MySQL db
I used the following scripts to install MySQL on Amazon Linux AMI.

$ sudo yum install mysql56-server

$ sudo service mysqld start

$ sudo mysql_secure_installation

$ sudo service mysqld stop

#### To start MySQL on start:
$ sudo chkconfig mysqld on

#### Design Motivation: (Completely Optional Section)
This application is going to grow into a mid to large size application. Hence we need a design foresight to accomodate the scale of requirements/usage. The question in hand thus is what would make up a ideal service? In any microservice ecosystem there are a lot of services that perform Service Choreography or "control (work) flow" in layman terms; On one hand: typically a service is so fine grained as to just do one thing and that one thing particularly well. One easy way to decide the modularity of a service is that one should be able to re-write it in a matter of about two weeks if need be. This introduces Hetrogeneous Interoperability and an opportunity to adopt bleeding edge technologies. However it cannot be simply be the smallest possible unit. On the other hand fine grained services do come with a great deal of practical difficulties. e.g. A lot of times spanning transactions over fine grained services are a hard problem too solve. XA isn't particularly impressive solution. Most developers would have encountered atleast a case or two of HeuristicMixedException or HeuristicRollbackException in their career which would have required manual intervention to restore data consistency. Hence eventual consistency is a possible solution to such problems particularly in systems with idempotent message-transactions. More on it on the article:
https://technicalyorker.wordpress.com/2016/11/20/all-you-wanted-to-know-about-transactions/
Another way is take transaction scope into consideration while designing the service boundry.

Fine grained services always introduces the need for more remote invocations that would require (re)design considerations to tackle problems of scalability. Hence functional services must have a well defined boundry of operation.

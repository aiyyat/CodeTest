One would probably have used docker container to host the microservices on AWS, but the company itself was on its baby steps to adopt docker when the problem was given to me and it wouldn't be the best of approach to use it. After solving the problem I did host the application on AWS with the view on a public subnet and the services/database on private subnet, provided the apps dns address for them to test.
 
The question was as follows:

Software Engineer challenge
Create a service (backend + frontend) that accepts the necessary information and sends emails.
 
The application should provide an abstraction between two different email service providers. If one of the services goes down, your service can quickly failover to a different provider without affecting your customers.
 
Email Providers:
 
Mailgun - Simple Send Documentation
SendGrid - Simple Send Documentation
 
All listed services are free to try and are pretty painless to sign up for, so please register your own test accounts on each. In case of issues signing up with either, alternative similar services can be used.
 
Your solution should cater for multiple email recipients, CCs and BCCs but there is no need to support HTML email body types (plain text is OK)
BACKEND
The backend should be implemented as one or more RESTful API calls (see technology constraints below).
- No authentication is required for the scope of this exercise
- No client library should be used to integrate with Mailgun or Sendgrid. A simple HTTP client of choice can be used to handcraft HTTP requests to the email gateway services.
FRONTEND
The frontend should ideally be a Single page Web App (see technology constraints below).
- The Sendgrid / Mailgun API credentials should not be leaked to the frontend
- There is no need to minify/uglify the JS code.
- Most modern browsers (Chrome latest / FF latest / IE10+) should be supported
- User-friendly feedback in case of errors and/or success must be provided
HINTS
- Please upload your solution on Github and include a README.md file with info on how to build and deploy both the backend and the frontend
- Please deploy your solution somewhere for us to play with it
- Please make sure the UI looks pleasant enough to be usable, without spending too much time (off the shelf CSS solutions like bootstrap/foundation etc. can work in your favor)
- We respect your time and don't want you spending more than a few hours on this challenge. It’s totally up to you to provide a more or less complete solution but be ready to discuss the production readiness of your solution in your job interview (what’s missing / why etc)
TECHNOLOGY CONSTRAINTS
Frontend and/or backend technologies should be as agreed upon with the recruiter.
HOW WILL WE REVIEW
Input Validation & Error Handling: how resilient the application is to wrong/misspelled input and/or to IO errors?
Technical choices: do choices of libraries, databases, architecture etc. seem appropriate for the chosen application?
Clarity: does the README clearly and concisely explains the problem and solution? Are technical tradeoffs explained? Are install/setup instructions provided?
Correctness: does the application do what was asked? If there is anything missing, does the README explain why it is missing?
Code quality: is the code simple, easy to understand, and maintainable? Is there any code smells or other red flags? Does object-oriented code follow principles such as the single responsibility principle? Is the coding style consistent with the language's guidelines? Is it consistent throughout the codebase?
Testing: Your code must be testable. If you run out of time and cannot add the necessary testing be prepared to answer questions so that we get a feel for your testing skills.
UX: is the web interface understandable and pleasing to use? Is the API intuitive?
 
QUESTIONS?
 
In case of doubts or if you get lost please ask as many questions as required: stefano.fratini@siteminder.com

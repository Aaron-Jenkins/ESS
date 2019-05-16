# ESS Project User Guide
This software was developed as a software system for solent cleaning services. It includes functionality to allow clients to register an account in order to book a cleaning service and add payment details in order to eventually pay once the service has been completed. Managers are allowed to view any unallocated services that have been booked by customers, and assign a cleaning staff member to a specific job. They are also able to charge a customer once the booked cleaning service has been marked as complete. Cleaners are able to use the system to view their upcoming allocated jobs, as well as mark the service as being completed, allowing the managers to charge the customer.

### Mobile Application
The mobile application is used by customers to book their cleaning services from the company. It is simultaneously used by cleaners to mview their upcoming booked services and to mark any finished jobs as being completed. The functionality which is available to the user is based on what login details are entered in the login process.

### Desktop Application
The desktop application is only used by the administrative user group after a client has booked a job. The manager can then allocate any specific staff member to the booked job, which then shows on the cleaner's mobile application after login. Following the completion of the cleaning service, and the mark given by the cleaner on the mobile application, the manager is free to send a payment request to the client.

### REST Interface
The rest interface is created using PHP and is used by all platforms to communicate with the database. This database includes the user login details, their position, and other information such as scheduled services and assigned staff.

### Installation Process 
To install this software, download the service files by cloning the github repository onto your own system, the following steps needed to run the system depends on the user who is using the system.

#### -Mobile Application Process
To use the system from either the cleaner or client's point of view, the ESS cleaning application must be opened in android studio, and the main method must be run. This should allow full access to the software and its functionality.

#### -Desktop Application Process
To use the system on a desktop from the manager's point of view, the ESS desktop application must be opened in the java IDE, and again, the main method must be run. Again, this should allow the user full access to the system and its functionality.

#### -REST Client Process
The web service can be found at the following URL: 

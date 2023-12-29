### Description
Study spring boot bank app project.
#### Tech details
The application is *stateless* and stores sessions using a JWT token (storage it in a cookie).
All endpoints related to the application's business logic require user authorization.
#### Api endpoints
Api has following endpoints:
|Name of endpoint|Description|GET|POST|
|-----------------|----------------------|--|--|
|`/api/v1/auth/register`|Register new user||:white_check_mark:|
|`/api/v1/auth/login`|Login to account||:white_check_mark:|
|`/api/v1/user/bill`|Create and check user bills|:white_check_mark:|:white_check_mark:|
|`/api/v1/user/bill/replenishment`|Refill user bill||:white_check_mark:|
|`/api/v1/user/bill/transfer`|Do a cash transfer between bills||:white_check_mark:|
|`/api/v1/user/bill/transactions`|Show user transactions|:white_check_mark:||
### Requirements
App requires postgresql database (dont forget change `application.properties` file)

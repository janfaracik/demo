# Persistence API Integration Demo

![image (1).png](image%20%281%29.png)

This project demonstrates the use of Spring Cloud Contract to create a mock server that can be used in testing and development. The mock server is designed to simulate the Persistence API, allowing us to validate interactions between our Lambda function and the Persistence API using predefined contracts.

## Getting Started

To run the mock server, use the following Maven command:

```shell
mvn spring-cloud-contract:run
```

For more detailed information about the Spring Cloud Contract Maven Plugin, refer to the [official documentation](https://docs.spring.io/spring-cloud-contract/docs/current/spring-cloud-contract-maven-plugin/plugin-info.html).

### Requirements

This project fulfills the following requirements:

1.	Mock Server for Lambda:
    •	A mock server is provided to emulate the Persistence API. This allows the Lambda function to be tested without needing access to the actual Persistence API.
2.	Contract Specifications:
    •	The project includes a series of contracts/specifications that define expected behaviors when the Persistence API is called with various payloads. These contracts ensure that the mock server responds appropriately according to the predefined scenarios.
3.	Pre-Generated Firm Data:
    •	Firm data is generated before any API calls are made, allowing the server to return consistent, predictable responses during testing.

## Questions

### How are we expecting the service stub to work?
The payload from our API will/could be different every request (e.g. we don't always send all fields), are we expecting the stub to accept any body, and just return a 204?

#### Follow up to this, does our lambda request any firm data after the Persistence API has been called?
If we stub the Persistence API the requested changes to firm data will not happen

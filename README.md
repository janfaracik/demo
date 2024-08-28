# contract-demo
 
Run with `mvn spring-cloud-contract:run`

Helpful doc:

https://docs.spring.io/spring-cloud-contract/docs/current/spring-cloud-contract-maven-plugin/plugin-info.html

## Our requirements

* I want a mock server that I can call from our lambda
* I want a series of contracts/specs that define what will happen when I call the Persistence API with a payload
* I want a mock firm generated ahead of the call to the API

![image (1).png](image%20%281%29.png)

---

**Questions**

How are we expecting the service stub to work?
- The payload from our API will/could be different every request (e.g. we don't always send all fields), are we expecting the stub to accept any body, and just return a 204?
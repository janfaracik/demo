import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a 204 for a valid request"
    request {
        method GET()
        url("/firms/${consumer(regex('\\d+'))}")
    }
    response {
        // This won't show anything in a browser
        status 204
    }
}
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a 204 for when a PATCH is sent"
    request {
        method PATCH()
        url("/firms/${consumer(regex('\\d+'))}")
    }
    response {
        // This won't show anything in a browser
        status 204
    }
}
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return a 204 for when a website is updated"
    request {
        method PATCH()
        url("/firms/${consumer(regex('\\d+'))}")
        body([
            website: "https://www.americanmary.com"
        ])
    }
    response {
        // This won't show anything in a browser
        status 204
    }
}
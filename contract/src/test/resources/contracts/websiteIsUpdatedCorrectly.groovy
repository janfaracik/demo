package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description ("""
        Should return a 204 for when a website is updated
        
        * Check that the 'Website' field for the firm has the new website
    """)
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
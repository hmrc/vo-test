
Valuation Office test utils, base specs, mocks, stubs, WireMock utilization.

Each VO test must inherit from either [BaseSpec](vo-unit-test/src/main/scala/uk/gov/hmrc/vo/unit/test/BaseSpec.scala), 
[BaseAppSpec](vo-unit-test/src/main/scala/uk/gov/hmrc/vo/unit/test/BaseAppSpec.scala), or [BaseServerSpec](vo-integration-test/src/main/scala/uk/gov/hmrc/vo/integration/test/BaseServerSpec.scala).

## vo-unit-test
Provides utilities for unit testing within the Valuation Office services, including base specifications, mocks, and stubs to support isolated and efficient test development.

## vo-integration-test
Provides utilities for integration testing within the Valuation Office services, including base specifications and the use of WireMock to simulate external service interactions.


## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
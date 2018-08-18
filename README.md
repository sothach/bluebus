# Azure ServiceBus Client
[![Coverage Status](https://coveralls.io/repos/github/sothach/bluebus/badge.svg?branch=master)](https://coveralls.io/github/sothach/inspiral?branch=master)

## Scala client for Azure ServiceBus

These classes are an alternative to using the official Microsoft Azure ServiceBus library.
Uses databinder.  Dispatch to implement REST calls against the ServiceBus API.
Generates a SAS key from configured credentials.

### Prerequisites 
The target language is Scala version 2.12, and uses the build tool sbt 1.2.1.
Clone this repository in a fresh directory:
```
% git clone git@bitbucket.org:royp/bluebus.git
```
Compile the example with the following command:
```
% sbt clean compile
[info] Done compiling.
[success] Total time: 6 s, completed 12-Aug-2018 11:38:12
```
The only explicit library dependency outside of the Scala language environment is Databinder dispatch version 0.13.4

## Library design

### Assumptions
It seems reasonable to limit the grid dimension to values of N to a maximum of 999, as 
processing c. 1 million data points should be enough for the purposes of this exercise.

### Confiouration
The implementation takes a 'design by contract' approach to correctness: class invariants and method
preconditions (Scala's ```require``` clause) are used to specify correctness and the client is expected to guard against
incorrect inputs - the client in this case is the console program, which rejects user input that would violate these
conditions

### Sample usage
```scala
import java.net.URI
import java.util.concurrent.Executors

import bluebus.client.ServiceBusClient
import bluebus.configuration.SBusConfig

import scala.concurrent.ExecutionContext

object SampleReceiver extends App {
  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(10))

  val busConfig = SBusConfig(
    rootUri=URI.create("http://samplens.servicebus.windows.net"),
    queueName="queueName",
    sasKeyName="RootManageSharedAccessKey",
    sasKey="yourKey")

  val incomingService = new ServiceBusClient(busConfig)

  /** continually receive & handle messages from endpoint, until no more available */
  def receiveMessages(f: String => Unit): Unit =
    incomingService.receive map { message =>
      f(message)
      receiveMessages(f)
    }

  receiveMessages((p: String) => println(p))
}
```

## Testing
### Running the tests
Run the test suite to verify correct behaviour.  From the command line:
```
% sbt test
```
### Test Coverage Report
To measure test coverage, this app uses the 'scoverage' SBT plugin.
To create the report, rom the command line:
```
% sbt coverage test coverageReport
```

## Author
* [Roy Phillips](mailto:phillips.roy@gmail.com)

## License
(c) 2018 This project is licensed under Creative Commons License

Attribution 4.0 International (CC BY 4.0)

Layer with a responsibility to access the data. Include get, save, update, or delete data from a network, local, cache, or mock.
It contains the implementations of the repositories declared in the domain layer.

<img width="467" alt="Screenshot 2022-08-23 at 12 23 40 AM" src="https://user-images.githubusercontent.com/30109439/186031533-2127e518-e8fb-4adc-acef-5ca5a073775e.png">

<img width="701" alt="Screenshot 2022-08-23 at 12 23 25 AM" src="https://user-images.githubusercontent.com/30109439/186031543-72d89e71-00eb-41f3-9c95-9d30a4a787fa.png">

## Coroutines

Kotlin coroutines introduce a new style of concurrency that can be used on Android to simplify async code. In addition to invoke (or call) and return, coroutines add suspend and resume.

- suspend — pause the execution of the current coroutine, saving all local variables

- resume — continue a suspended coroutine from the place it was paused

On Android, coroutines are a great solution to two problems:

- Long running tasks are tasks that take too long to block the main thread.

- Main-safety allows you to ensure that any suspend function can be called from the main thread.

To specify where the coroutines should run, Kotlin provides three Dispatchers you can use for thread dispatch.

### Dispatchers.Main
Main thread on Android, interact with the UI and perform light work
- Calling suspend functions
- Call UI functions
- Updating LiveData

### Dispatchers.IO
Optimized for disk and network IO off the main thread
- Database
- Reading/writing files
- Networking

### Dispatchers.Default
Optimized for CPU intensive work off the main thread
- Sorting a list
- Parsing JSON
- DiffUtils



## DI
Dependency injection is a programming technique that makes a class independent of its dependencies. It achieves that by decoupling the usage of an object from its creation. This helps you to follow SOLID's dependency inversion and single responsibility principles.

#### Single responsibility principles
- A class should have only one reason to change

#### Dependency inversion principles
- High level modules should not depend on low level modules, but both should depends on abstraction.

Implementing dependency injection provides you with the following advantages:
- Reusability of code
- Ease of refactoring
- Ease of testing

### KOIN
Koin a pragmatic lightweight dependency injection framework for Kotlin developers and has builtin ViewModel support and doesnt need anything "extra" for Workers.

Setting up dependency injection is a kind of manual process, we essential plug our service into the module graphs, and those dependency will later get resolved at runtime.

<img width="467" alt="Screenshot_koin" src="https://user-images.githubusercontent.com/30109439/185768621-37ce49f3-4704-4985-9569-cd0b1acd987b.png">

<img width="552" alt="Screenshot_koin_module" src="https://user-images.githubusercontent.com/30109439/185768623-c1e5e049-dff5-47be-8ea3-b66f0f7df5ff.png">



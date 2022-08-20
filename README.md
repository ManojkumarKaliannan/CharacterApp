# Character List Application with Android Architecture components and Clean Architecture
![Screenshot_20220820_131334](https://user-images.githubusercontent.com/30109439/185747521-f0430a96-49e8-42a3-a9da-3ef853ed61e4.png)

Simple app that shows how to architect an android app in a clean architecture with kotlin coroutines. It simply shows a list of photo and details that is fetched from rickandmorty api(https://rickandmortyapi.com/api/character)

<img width="190" alt="Screenshot_arch" src="https://user-images.githubusercontent.com/30109439/185768396-ad3b82af-5acf-4d4c-9f64-0db380fcb824.png">


## NOTABLE FEATURES
## CleanArchitecture

![69536839-9f4c8e80-0fa0-11ea-85ee-d7823e5a46b0](https://user-images.githubusercontent.com/30109439/185747729-b6e00ae5-fd11-4758-a219-9452907b7e61.png)

MVVM is a part of clean architecture. MVVM includes Model, View and ViewModel and in addition to this, Clean architecture has one more layer which is USE CASES.

In MVVM, we use to put all our business logic into our ViewModels but the problem with that is, the bigger your project gets, the more number of ViewModels you have and all your business logic is divided into various viewModels. That’s where clean architecture comes into the picture, we use USE CASES instead of ViewModels for all our Business logic.

### Advantages of Clean architecture:

Separation of Concerns —Separation of code in different modules or sections with specific responsibilities makes it easier for maintenance and further modification.

Loose coupling — flexible codes can be easily changed without changing the whole system.

Easily Testable

### The Layers of MVVM with Clean Architecture

The code is divided into three separate layers:

- Presentation Layer/UI Layer

- Domain Layer

- Data Layer

### Presentation Layer

The presentation layer is a layer that focuses to handle the UI. Including UI design and also UI logic, that’s it (don’t mix with another logic).

There are two main parts on Presentation Layer: View and ViewModel

#### View

A view can be an Activity, Fragment, or Custom View.
The Responsibility is displayed from the layout itself or updating UI by the value passed from ViewModel.
The view on MVVM has Unidirectional Data Flow, and it doesn’t have logic. If you need the UI logic, please put it on ViewModel, instead of View.

<img width="766" alt="Screenshot_view" src="https://user-images.githubusercontent.com/30109439/185768465-6423db37-b8e0-49ac-812a-fefed23ac280.png">

#### ViewModel

The responsibility is to handle UI logic and also as a connector between View and UseCase (domain layer).

<img width="884" alt="Screenshot_viewmodel" src="https://user-images.githubusercontent.com/30109439/185768479-37634a4d-b7a4-4271-93f5-e07681f9ae05.png">

### Domain Layer
The domain layer is business logic. There’s no knowledge about the view and data. Focus on connecting them. Don’t mix the business logic with UI logic or data logic.

#### Entities
Simple classes that represent the objects in which the business is based.

#### Repositories
Interfaces used by the use cases. Implemented in the data layer.

<img width="428" alt="Screenshot_domain_repo" src="https://user-images.githubusercontent.com/30109439/185768516-9c2f570a-8078-44ed-bb85-6623d6c118ee.png">

#### Use cases
The purpose of the UseCases is to be a mediator between your ViewModels and Repositorys.

<img width="705" alt="Screenshot_usecase" src="https://user-images.githubusercontent.com/30109439/185768527-f09b85e1-56e2-45d7-b5d8-8d86a9969575.png">

#### Advantages of Use Cases :

Helps removing code Duplication

Screaming Architecture

### Data Layer
Layer with a responsibility to access the data. Include get, save, update, or delete data from a network, local, cache, or mock.
It contains the implementations of the repositories declared in the domain layer.

<img width="445" alt="Screenshot_api" src="https://user-images.githubusercontent.com/30109439/185768589-a1314244-a7e4-4207-b9eb-30ccdee8876b.png">

<img width="818" alt="Screenshot_repo_data" src="https://user-images.githubusercontent.com/30109439/185768592-6d252087-438a-4c79-a71b-d55c9848bd8d.png">

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

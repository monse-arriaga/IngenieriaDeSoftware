# [Model-View-ViewModel (MVVM)](https://github.com/ahmedeltaher/Android-MVVM-architecture)


 [![kotlin](https://img.shields.io/badge/Kotlin-1.9.xxx-brightgreen.svg)](https://kotlinlang.org/)  [![Retrofit](https://img.shields.io/badge/Retrofit-2.9.xxx-yellow.svg)](https://square.github.io/retrofit/) [![Lottie Animation](https://img.shields.io/badge/Lottie-3.4.xxx-purple.svg)](https://lottiefiles.com/es/)
  [![SplashScreen](https://img.shields.io/badge/Splashscreen-1.0.xxx-red.svg)](https://developer.android.com/develop/ui/views/launch/splash-screen) [![Coroutines](https://img.shields.io/badge/Coroutines-1.7.xxx-blue.svg)](https://developer.android.com/kotlin/coroutines) [![Hilt](https://img.shields.io/badge/Hilt.2.5-orange.svg)](https://developer.android.com/training/dependency-injection/hilt-android)  [![Kotlin-Android-Extensions ](https://img.shields.io/badge/Kotlin--Android--Extensions-plugin-red.svg)](https://kotlinlang.org/docs/tutorials/android-plugin.html) [![MVVM ](https://img.shields.io/badge/Clean--Code-MVVM-brightgreen.svg)](https://medium.com/@dheerubhadoria/android-mvvm-how-to-use-mvvm-in-android-example-7dec84a1fb73) 
![MVVM3](https://miro.medium.com/v2/resize:fit:4800/format:webp/1*j9-O4DcaYTBTlSjckaFqXA.png)

### Model-View-ViewModel (MVVM)

MVVM is a client application architecture template introduced by John Gossman. It serves as an alternative to the MVC and MVP patterns, especially when utilizing Data Binding technology. The key idea behind MVVM is to separate data presentation logic from business logic by delegating the former to a specific class, ensuring a clear separation of concerns.

For more details on MVP, you can check out [**MVP**](https://github.com/ahmedeltaher/Android-MVP-Architecture).

### Why Promote MVVM Over MVP?

1. **Lifecycle Awareness**: 
   - ViewModel: Has built-in lifecycle awareness, reducing the need for manual lifecycle management.
   - Presenter: Lacks lifecycle awareness, requiring developers to manage this aspect.

2. **View References**:
   - ViewModel: Does not hold references to the View, minimizing the risk of memory leaks.
   - Presenter: Holds a reference to the View, even if it is a weak reference, which can lead to potential memory leaks.

3. **Configuration Changes**:
   - ViewModel: Automatically survives configuration changes, preserving the UI state.
   - Presenter: Requires manual handling to survive configuration changes and manage UI state restoration.

### MVVM Best Practices

1. **Avoid View References in ViewModels**: 
   - Ensures that ViewModels remain decoupled from the UI layer, promoting testability and maintainability.

2. **Use Observables for Data Changes**: 
   - Instead of pushing data to the UI, allow the UI to observe changes, leading to a more reactive and flexible architecture.

3. **Distribute Responsibilities**:
   - Add a domain layer if necessary to handle business logic, ensuring a clear separation of concerns.

4. **Data Repository**:
   - Implement a data repository as a single-point entry to your data, centralizing data access and management.

5. **Expose Data State**:
   - Use a wrapper or another LiveData to expose information about the state of your data, enhancing transparency and control over data flow.

6. **Consider Edge Cases and Long-Running Operations**:
   - Account for potential edge cases and how long-running operations can impact your architecture, ensuring robust and resilient design.

7. **Keep Critical Logic Out of ViewModels**:
   - Avoid placing critical logic related to saving clean state or data in the ViewModel, as any call made from a ViewModel can be its last due to lifecycle constraints.

By following these best practices, you can leverage the full potential of the MVVM architecture, leading to a more organized, maintainable, and scalable application.



**What are Coroutines?**
------------------------

**Coroutines:**
Coroutines are lightweight threads used for asynchronous programming. They not only facilitate asynchronous operations but also offer various capabilities such as concurrency and actor-based programming.

----------

**How Do Coroutines Work?**
------------
Kotlin coroutines allow you to perform asynchronous tasks in a sequential manner. Creating a coroutine is significantly more resource-efficient than creating a traditional thread.

**Benefits of Coroutines**
-----------------------------

- **Sequential Asynchronous Code**: Write asynchronous code that reads like sequential code, making it easier to understand and maintain.
- **Cost Efficiency**: Coroutines are much cheaper to create and manage compared to threads.
- **Simplicity**: Avoid unnecessary complexity by using coroutines instead of patterns like observables when they are not needed.
- **Lifecycle Management**: Parent coroutines can automatically manage the lifecycle of their child coroutines, simplifying resource management.



**Keep your code clean according to MVVM**
-----------------------------
 

  ![mvvm2](https://user-images.githubusercontent.com/1812129/68319008-e9d39d00-00bd-11ea-9245-ebedd2a2c067.png)

**Tournament App Screenshots**
-----------------------------

![home](images/home.jpg)
![profile_success](images/profile_success.jpg)
![profile_date](images/profile_date.jpg)
![success_create_tournament](images/success_create_tournament.jpg)
![tournament_time](images/tournament_time.jpg)
![tournament_selectedimage](images/tournament_selectedimage.jpg)
![tournament_webview](images/tournament_webview.jpg)
![create_tournament](images/create_tournament.jpg)
![tournament_subscribe](images/tournament_subscribe.jpg)
![tournament_details](images/tournament_details.jpg)
![tournaments](images/tournaments.jpg)
![register](images/register.jpg)


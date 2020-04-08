## Lambda Tutorial [![Build Status](https://travis-ci.org/AdoptOpenJDK/lambda-tutorial.png?branch=master)](https://travis-ci.org/AdoptOpenJDK/lambda-tutorial)

A set of exercises to teach use of Java 8 lambda syntax, and the Streams API.

To follow the exercises:

 - fork and clone the repository
 - ensure you have a correctly configured, JDK8+ build
   - Maven can help generate configuration for your favourite IDE
   - ensure your cloned project, particularly the class `ConfigureYourLambdaBuildOfJdk` compiles and runs correctly
 - navigate to the first exercise, `Exercise_1_Test` (tests are in `src/test/java`, in the package `org.adoptopenjdk.lambda.tutorial`)
 - read background information in the JavaDoc, and follow instructions, making the test pass
 - to find solutions check out different branches of the project. Two flavours of solutions are available: what it might have looked like before JDK 8, and what it might look like after JDK 8. Those are in branches `solutions-prejava8` and `solutions-postjava8` respectively.


### Exercises

 1. Internal vs External Iteration (the forEach method)
 2. Filtering and Collecting
 3. Mapping
 4. Method References
 5. Default methods on interfaces
 
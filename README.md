## Lambda Tutorial [![Build Status](https://travis-ci.org/AdoptOpenJDK/lambda-tutorial.png?branch=master)](https://travis-ci.org/AdoptOpenJDK/lambda-tutorial)

A set of exercises to teach use of Java 8 lambda syntax, and the new Streams API.

To follow the exercises:

 - fork and clone the repository
 - ensure you have a correctly configured, [lambda-enabled Java build](#getting-lambda-jdk)
   - Maven can help generate configuration for your favourite IDE, though you will likely have to set the JDK manually
   - ensure your cloned project, particularly the class `ConfigureYourLambdaBuildOfJdk` compiles and runs correctly
 - navigate to the first exercise, `Exercise_1_Test` (tests are in `src/test/java`, in the package `org.adoptopenjdk.lambda.tutorial`)
 - read background information in the JavaDoc, and follow instructions, making the test pass
 - to find solutions check out different branches of the project. Two flavours of solutions are available: what it might have looked like before JDK 8, and what it might look like after JDK 8. Those are in branches `solutions-prejava8` and `solutions-postjava8` respectively.


### Exercises

 1. Internal vs External Iteration (the forEach method)
 2. Filtering and Collecting
 3. Mapping
 4. Method References

[More to come]


### Getting Lambda JDK
Early access builds of JDK 8 are available [here](https://jdk8.java.net/download.html). 
 

#### Lamba JDK Build Compatibility
The current tutorial is known to work with the following JDK build:

|JDK Build Number|Released On |
|:---------------|:---------- |
|ea b109         |Sep 26, 2013|

lambda-tutorial will try to track against the newest version available. If you find that you are working with a newer version of the Lambda JDK and the tutorial does not compile or run, please file an issue.

### IDE Setup
- [IntelliJ IDEA on Ubuntu](https://github.com/AdoptOpenJDK/lambda-tutorial/wiki/IntelliJ-IDEA-on-Ubuntu-%5BLinux%5D)
- [IntelliJ IDEA on MacOS](https://github.com/AdoptOpenJDK/lambda-tutorial/wiki/IntelliJ-IDEA-on-MacOS)
- [IntelliJ IDEA deutsche Anleitung (u.a. Windows)](https://github.com/AdoptOpenJDK/lambda-tutorial/wiki/IntelliJ-IDEA-Einrichtung)
- [Eclipse Kepler 4.3 on Windows](https://github.com/AdoptOpenJDK/lambda-tutorial/wiki/Eclipse-Lambda-EA-Setup)

<b>Note: we are hoping the instructions are not too sensitive to the OSes on which they have been performed.</b>

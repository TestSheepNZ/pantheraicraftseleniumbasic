Welcome to the code for the Panther ODM automation suite

The code inside is part of workshop material created by Mike Talks 2019.
It is designed to exercise a website which calculates fuel usage for a (fictional) aircraft.

You are welcome to use this code for your own learning, or within your own company. But please attribute.

Please do not use this material for a paid workshop or conference proposal without consulting with me.

Find me on Twitter as @TestSheepNZ

The useful_resource.html page contains a lot of the resources explaining items which this code was designed with

The test.properties sets up and configures this test suite including,
* Where the item under test lives
* Which browser is in use
* Which kind of computer it's being run on
* Max wait

In case the conference wi-fi becomes a problem, you can find the webpages under src/main/resources/website, and can define the path to them using the form,

file:///C:/Users/OEM/IdeaProjects/pantheraircraftseleniumautomation/src/main/resources/website/panther.html

This will be specific to your machine.

A list of useful resources
==========================

The following are all useful documents used in the design of this suite.

Setting up Maven project - https://www.youtube.com/watch?v=pt3uB0sd5kY">Creating Maven project in Intellij

Implementing a page object model
Using the page factory - https://github.com/SeleniumHQ/selenium/wiki/PageFactory
Page Object Model and Page Factory - https://www.guru99.com/page-object-model-pom-page-factory-in-selenium-ultimate-guide.html
Building a selenium framework - https://www.logigear.com/blog/test-automation/building-a-selenium-framework-from-a-to-z/
Page object model in Java - https://www.swtestacademy.com/page-object-model-java/
POM Framework with Java and webdriver - https://www.testingexcellence.com/page-object-framework-java-webdriver/
Page Object Model - Make it simple, use abstraction - https://blog.testproject.io/2017/07/16/page-object-model/
Creating a page class - https://www.pluralsight.com/guides/getting-started-with-page-object-pattern-for-your-selenium-tests

Javascript support
==================
Writing text on a web page using JavaScript - https://www.oreilly.com/library/view/javascript-jquery/9781491948583/ch01.html

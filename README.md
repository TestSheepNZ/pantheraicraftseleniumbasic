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
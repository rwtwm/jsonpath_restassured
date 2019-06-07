### Rest Assured API testing with JSONpath mapping

The 'main' code area introduces json path syntax to expose the [world bank climate data API](https://datahelpdesk.worldbank.org/knowledgebase/articles/902061-climate-data-api). The json path based deserialisation is in the 'main' area of the code, with tests written in the 'codetesting' package.

The 'apitesting' package tests the API directly using [rest-assured](http://rest-assured.io/). A few of the [Hamcrest matchers](http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html) are explored here. Available matchers and rest assured elements are imported statically. The selected API does not support post requests, so only get requests are explored in the sample. 

Dependencies in the POM.xml file. Using Rest-assured 3.3.0 and JUnit 4.12.
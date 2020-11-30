# webcrawler

# Requirement:
Given a starting URL – say http://wiprodigital.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.
The output should be a simple structured site map (this does not need to be a traditional XML sitemap - just some sort of output to reflect what your crawler has discovered), showing links to other pages under the same domain, links to external URLs and links to static content such as images for each respective page.

# How to build and run the solution: 
1. Clone the repository - https://github.com/vkhand-fs/webcrawler.git
2. Import the project into the IDE. I used STS.
3. Right click on the project and select Gradle->Gradle refresh to download all required dependencies
4. Open WebCrawlerApplication.java and right click and run as java application. (Please provide the domain url as an argument in run configurations)

# Brief explanation of the solution:
1. java.util.concurrent.CompletableFuture has been used to process each url asyncronounsly.
2. Using Jsoup, we connect to every url and get the document object.
3. Retrieve the link from document object and iterate through each link/url recursively and process them asynchronously.

# What I could have done more
1. Could have included more testcases with some mock html pages. 
2. Could have done better exception handling. Currently the core response object contains the exception message.
3. Could have given a depth to limit the crawling for each url.
4. Could have used a layered pattern to design as a microservice.

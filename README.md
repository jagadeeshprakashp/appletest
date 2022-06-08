# Apple URL Shortener Task

URL Shortener
We'd like you to build a website that functions as a URL Shortener:
1. A user should be able to load the index page of your site and be presented with an input field where they can enter a URL.
2. Upon entering the URL, a "shortened" version of that url is created and shown to the user as a URL to the site you are building.
3. When visiting that "shortened" version of the URL, the user is redirected to the original URL.
4. Additionally, if a URL has already been shortened by the system, and it is entered a second time, the first shortened URL should be given back to the user.
   For example, if I enter http://www.apple.com/iphone/ into the input field, and I'm running the app locally on port 9000, I'd expect to be given back a URL that looked something like http://localhost:9000/1. Then when I visit http://localhost:9000/1, I am redirected to http://www.apple.com/iphone/.


Pre requirements
1. Java 8
2. Eclipse / Intellij
3. Docker

# Commands to Install REDIS from docker
Once after installing the Docker. Please run below commands
## Step1 - Pulling redis image from docker hub
docker pull redis
## Step2 - Running the container
docker run -d -p 6379:6379 --name my-redis redis


# Compile / Launch

From the project clone location please run below command to compile the project

mvn clean install

To launch the project, Please run URLShortenerController as java application


Once after the application is up and running please launch http://localhost:9000/

Please provide the URL for shortening in the input field and click on 'Post URL'

The result URL will be displayed, up on clicking the url it will redirect to the full URL





#!/bin/bash
#This is a comment
 
#defining a variable
GREETINGS="Hello! How are you"
echo $GREETINGS
echo Running the URL Shortner application


if java -version && java -version 2>&1 >/dev/null | grep -q "java version" ; then
  echo "Java installed :: Checked !!!"
else
  echo "Java NOT installed!"
  $SHELL
fi

if mvn -v; then
  echo "Maven installed :: Checked !!!"
else
  echo "Maven NOT installed!"
  $SHELL
fi

if [[ $(which docker) && $(docker --version) ]]; then
    echo "Docker is installed in your system, make sure your docker is uptodate :: Checked !!!"
    # command
	if ! docker info > /dev/null 2>&1; then
	  echo "This script uses docker, and it isn't running - please start docker and try again!"
	  $SHELL
	fi
  else
    echo "!!! Seems like docker is not installed in the system please download !!! 
	For mac https://docs.docker.com/desktop/mac/install/ and for Windows https://runnable.com/docker/install-docker-on-windows-10 "
	$SHELL
    # command
fi

if [ ! "$(docker ps -q -f name=my-redis)" ]; then
    if [ "$(docker ps -aq -f status=exited -f name=my-redis)" ]; then
        # cleanup
        docker rm my-redis		
    fi
    # run your container
    docker run -d -p 6379:6379 --name my-redis redis
fi
mvn spring-boot:run


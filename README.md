# spring-webflux

## Docker mongodb-alpine

### Install

As a prerequisite, you need [Docker](https://docker.com) to be installed.

To download this image from the public docker hub:

        $ docker pull mvertes/alpine-mongo

To re-build this image from the dockerfile:

        $ docker build -t mvertes/alpine-mongo .

### Usage

To run `mongod`:

        $ docker run -d --name mongo -p 27017:27017 mvertes/alpine-mongo

You can also specify the database repository where to store the data
with the volume -v option:

    $ docker run -d --name mongo -p 27017:27017 \
          -v /somewhere/onmyhost/mydatabase:/data/db \
          mvertes/alpine-mongo

To run a shell session:

    $ docker exec -ti mongo sh

To use the mongo shell client:

        $ docker exec -ti mongo mongo
        
        
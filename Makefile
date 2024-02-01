.PHONY: all build-docker run

all: build-docker run

build-docker:
    ./mvnw clean package

run:
    docker-compose up --build

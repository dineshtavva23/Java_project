FROM openjdk:17-slim

RUN apt-get update && apt-get install -y python3 python3-pip ant && apt-get clean

WORKDIR /java_project

COPY . .

RUN ant

CMD ["echo", "docker container started"]
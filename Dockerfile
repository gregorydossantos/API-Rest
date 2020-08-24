FROM openjdk:11

ARG PROFILE
ARG ADDTIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDTIONAL_OPTS}

WORKDIR /opt/apirest

COPY . /opt/apirest.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar apirest.jar --spring-profiles.active=${PROFILE}
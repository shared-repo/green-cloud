FROM alpine as builder

RUN apk update \
&& apk add openjdk17 \
&& apk add maven \
&& apk add git \
&& git config --global user.name "greencloud" \
&& git config --global user.email "greencloud@example.com"

ENV JAVA_HOME="/usr/lib/jvm/java-17-openjdk"

WORKDIR /work

RUN git clone https://github.com/shared-repo/green-cloud.git \
&& mvn -f green-cloud/workspace/spring-basic/spring-mvc-demoweb-a3 clean package

FROM alpine

CMD ["/opt/tomcat/bin/catalina.sh", "run"]

RUN apk update  \
&& apk add openjdk17-jre

ENV JAVA_HOME="/usr/lib/jvm/java-17-openjdk"

WORKDIR /opt
COPY tomcat ./tomcat
COPY --from=builder /work/green-cloud/workspace/spring-basic/spring-mvc-demoweb-a3/target/spring-demoweb-0.0.1-SNAPSHOT.war ./tomcat/webapps/spring-demoweb.war
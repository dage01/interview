FROM openjdk:11
ARG JAR_FILE=2/target/interview-0.0.1-SNAPSHOT.jar
COPY /target/interview-0.0.1-SNAPSHOT.jar interview.jar
ENTRYPOINT ["java","-jar","/interview.jar"]
#EXPOSE 8090
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

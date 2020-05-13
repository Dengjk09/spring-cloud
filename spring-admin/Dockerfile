FROM java:8-jre

# Set the timezone.
        RUN echo "Asia/Chongqing" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

# Make logs dir
RUN mkdir -p /opt/spring-admin
RUN mkdir /apm-agent

# Add the jar and run
ADD target/spring-admin-0.1.0-SNAPSHOT.jar /opt/spring-admin/app.jar

#RUN mv /opt/tms/app/BOOT-INF/classes/SIMYOU.TTF  /opt/tms/
#RUN mv /opt/tms/app/BOOT-INF/classes/11.png  /opt/tms/
WORKDIR /opt/spring-admin
#ENTRYPOINT ["java","-XX:+UseG1GC","-XX:MaxGCPauseMillis=100","-Xmx4096m","-Xms2048m","-XX:+PrintGCDetails","-XX:+PrintGCTimeStamps","-XX:+PrintGCDateStamps","-XX:+PrintHeapAtGC","-Xloggc:/opt/tms/logs/gc.log","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
#ENTRYPOINT ["java","-XX:+UseG1GC","-XX:MaxGCPauseMillis=100","-Xmx4096m","-Xms2048m","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
ENTRYPOINT ["java","-XX:+UseG1GC","-XX:+HeapDumpOnOutOfMemoryError","-XX:HeapDumpPath=/opt/spring-admin/logs","-XX:+PrintGCDetails","-XX:+PrintGCTimeStamps","-XX:+PrintGCDateStamps","-XX:+PrintHeapAtGC","-Xloggc:/opt/spring-admin/logs/gc.log", "-XX:MaxGCPauseMillis=100","-Xmx512m","-Xms256m","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
EXPOSE 9098
FROM java:8-jre
# Set the timezone.
        RUN echo "Asia/Chongqing" > /etc/timezone
RUN dpkg-reconfigure -f noninteractive tzdata

# Make logs dir
RUN mkdir -p /opt/eureka-discovery
RUN mkdir -p /djk/tmep
# Add the jar and run
ADD target/eureka-discovery-0.1.0-SNAPSHOT.jar /opt/eureka-discovery/app.jar

WORKDIR /opt/eureka-discovery
ENTRYPOINT ["java","-Xmx2048m","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
EXPOSE 9090
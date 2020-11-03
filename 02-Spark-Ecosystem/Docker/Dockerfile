# This base image comes shipped with java 8 (needed for scala)
FROM openjdk:12-jdk-alpine

# Set env variables
ENV DAEMON_RUN=true
ENV SPARK_VERSION=3.0.1
ENV HADOOP_VERSION=2.7
ENV SCALA_VERSION=2.12.12
ENV SCALA_HOME=/usr/share/scala
ENV SPARK_HOME=/spark
ENV SBT_HOME=/sbt
ENV SPARK_OPTS --driver-java-options=-Xms1024M --driver-java-options=-Xmx4096M --driver-java-options=-Dlog4j.logLevel=info


# Add additional repo's for apk to use

RUN echo http://dl-cdn.alpinelinux.org/alpine/v3.3/main > /etc/apk/repositories; \
    echo http://dl-cdn.alpinelinux.org/alpine/v3.3/community  >> /etc/apk/repositories

# Update commands
RUN apk --update add wget tar bash coreutils procps openssl git

# Install Scala
RUN apk add --no-cache --virtual=.build-dependencies wget ca-certificates && \
    apk add --no-cache bash && \
    cd "/tmp" && \
    wget "https://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz" && \
    tar xzf "scala-${SCALA_VERSION}.tgz" && \
    mkdir "${SCALA_HOME}" && \
    rm "/tmp/scala-${SCALA_VERSION}/bin/"*.bat && \
    mv "/tmp/scala-${SCALA_VERSION}/bin" "/tmp/scala-${SCALA_VERSION}/lib" "${SCALA_HOME}" && \
    ln -s "${SCALA_HOME}/bin/"* "/usr/bin/" && \
    apk del .build-dependencies && \
    rm -rf "/tmp/"*
    

RUN export PATH="/usr/local/sbt/bin:$PATH" &&  apk update && apk add ca-certificates wget tar && mkdir -p "/usr/local/sbt"

RUN cd "/tmp" && wget "https://github.com/sbt/sbt/releases/download/v1.3.4/sbt-1.3.4.tgz" && \
	tar xzf "sbt-1.3.4.tgz" && \
	mkdir "${SBT_HOME}" && \
	mv "/tmp/sbt/"* "${SBT_HOME}" && \
	ln -s "${SBT_HOME}/bin/"* "/usr/bin" && \
        rm -rf "/tmp/"*

# Get Apache Spark
RUN wget https://downloads.apache.org/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz

# Install Spark and move it to the folder "/spark" and then add this location to the PATH env variable
RUN tar -xzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} /spark && \
    rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz && \
    ln -s "${SPARK_HOME}/bin/"* "/usr/bin"


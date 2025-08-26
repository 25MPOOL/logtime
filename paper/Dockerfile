FROM eclipse-temurin:21-jre-jammy

ENV MC_VERSION="1.21" \
    BUILD_NUMBER="130"

RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*

WORKDIR /server

RUN wget -O paper.jar "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}/builds/${BUILD_NUMBER}/downloads/paper-${MC_VERSION}-${BUILD_NUMBER}.jar"

COPY paper/eula.txt .

CMD ["java", "-Xms1G", "-Xmx1G", "-jar", "paper.jar", "--nogui"]

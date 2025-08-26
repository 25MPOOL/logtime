# === ビルド用ステージ ===
FROM debian:stable-slim AS builder

ARG MC_VERSION="1.21"
ARG BUILD_NUMBER="130"

RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O paper.jar "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}/builds/${BUILD_NUMBER}/downloads/paper-${MC_VERSION}-${BUILD_NUMBER}.jar"

# === 起動用ステージ ===
FROM eclipse-temurin:21-jre-jammy

WORKDIR /server

COPY --from=builder /paper.jar .
COPY paper/eula.txt .

EXPOSE 25565

CMD ["java", "-Xms1G", "-Xmx1G", "-jar", "paper.jar", "--nogui"]

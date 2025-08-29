# === Paperサーバーダウンロード用ステージ ===
FROM debian:stable-slim AS server-builder
ARG MC_VERSION="1.21.8"
ARG BUILD_NUMBER="53"
RUN apt-get update && apt-get install -y wget && rm -rf /var/lib/apt/lists/*
RUN wget -O paper.jar "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}/builds/${BUILD_NUMBER}/downloads/paper-${MC_VERSION}-${BUILD_NUMBER}.jar"

# === 実行用ステージ ===
FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /server

# Paperサーバーをコピー
COPY --from=server-builder /paper.jar .
COPY paper/eula.txt .

EXPOSE 25565
CMD ["java", "-Xms1G", "-Xmx1G", "-jar", "paper.jar", "--nogui"]

# -----------------------------------------------------------------
# docker/paper/Dockerfile
# -----------------------------------------------------------------
# プラグインをテストするためのPaperサーバー環境を定義します。(1.21.0対応版)
# -----------------------------------------------------------------

# Minecraft 1.21 は Java 21 が必須のため、ベースイメージを更新
FROM eclipse-temurin:21-jre-jammy

# 必要なツールをインストール
RUN apt-get update && apt-get install -y wget curl jq && rm -rf /var/lib/apt/lists/*

# サーバーの作業ディレクトリを作成
WORKDIR /server

# Paperサーバーの最新ビルドを自動で取得するように修正
ARG MC_VERSION=1.21
RUN LATEST_BUILD=$(curl -s "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}" | jq -r '.builds[-1]') && \
    wget -O paper.jar "https://api.papermc.io/v2/projects/paper/versions/${MC_VERSION}/builds/${LATEST_BUILD}/downloads/paper-${MC_VERSION}-${LATEST_BUILD}.jar"

# EULAファイルを事前に作成
COPY docker/paper/eula.txt .

# サーバーを起動するコマンド
# メモリ割り当てはPCのスペックに合わせて調整してください (例: -Xms2G -Xmx2G)
CMD ["java", "-Xms1G", "-Xmx1G", "-jar", "paper.jar", "--nogui"]

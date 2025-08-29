FROM mcr.microsoft.com/devcontainers/java:21

RUN apt-get update && apt-get install -y \
    maven \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# .m2ディレクトリの権限を vscode に設定
RUN mkdir -p /home/vscode/.m2/repository \
    && chown -R vscode /home/vscode/.m2

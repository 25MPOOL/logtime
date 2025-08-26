# Minecraft LogTime Plugin - 開発用Makefile

.PHONY: help build start stop clean logs test reload

# デフォルトターゲット
help:
	@echo "利用可能なコマンド:"
	@echo "  make build   - プラグインをビルド"
	@echo "  make start   - Paper サーバーを起動"
	@echo "  make stop    - すべてのコンテナを停止"
	@echo "  make clean   - ビルド成果物とコンテナを削除"
	@echo "  make logs    - Paper サーバーのログを表示"
	@echo "  make test    - テストを実行"
	@echo "  make reload  - プラグインをリビルドしてサーバー再起動"

# プラグインをビルド
build:
	@echo "プラグインをビルド中..."
	mvn clean package

# Paper サーバーを起動
start:
	@echo "Paper サーバーを起動中..."
	docker-compose up -d paper

# すべてのコンテナを停止
stop:
	@echo "コンテナを停止中..."
	docker-compose down

# ビルド成果物とコンテナを削除
clean:
	@echo "クリーンアップ中..."
	docker-compose down -v
	docker-compose build --no-cache
	rm -rf target/

# Paper サーバーのログを表示
logs:
	docker-compose logs -f paper

# テストを実行
test:
	mvn test

# プラグインをリビルドしてサーバー再起動（開発時に便利）
reload:
	@echo "プラグインをリビルドしてサーバー再起動中..."
	make build
	docker-compose restart paper

# 開発環境を初期化（初回セットアップ用）
init:
	@echo "開発環境を初期化中..."
	docker-compose build
	make build
	@echo "初期化完了！'make start' でサーバーを起動できます。"

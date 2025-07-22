FROM openjdk:17-jdk-slim as build
WORKDIR /app

# Gradle Wrapper 用ファイルをコピー
COPY gradlew .
COPY gradle gradle

# ソースコードをコピー
COPY . .

# 実行権限付与（忘れがち）
RUN chmod +x gradlew

# 依存関係ダウンロードとビルド
RUN ./gradlew build -x test

# Java 17ベースの軽量イメージ
FROM eclipse-temurin:17-jdk-alpine

# 作業ディレクトリ作成
WORKDIR /app

# Gradleに必要なファイルを先にコピー
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

# Gradle実行許可（Linux環境では必要）
RUN chmod +x gradlew

# 依存解決（キャッシュ効率アップ）
RUN ./gradlew dependencies || true

# プロジェクト全体をコピー
COPY . .

# アプリケーションのビルド（テスト除外）
RUN ./gradlew build -x test

# 起動コマンド（.jarの名前に注意）
CMD ["java", "-jar", "build/libs/homepage-0.0.1-SNAPSHOT.jar"]

# JDK 21 ベースイメージを使う
FROM eclipse-temurin:21-jdk

# 作業ディレクトリ作成
WORKDIR /app

# ホスト側のファイルをコンテナにコピー
COPY . .

# Gradle Wrapper の実行権限を与える
RUN chmod +x ./gradlew

# ビルド（テストをスキップ）
RUN ./gradlew build -x test

# JARファイルの名前に合わせて変更
CMD ["java", "-jar", "build/libs/homepage-0.0.1-SNAPSHOT.jar"]

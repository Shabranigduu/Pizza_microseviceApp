# Определение базового образа для Java
FROM adoptopenjdk:11-jre-hotspot

# Установка рабочего каталога внутри контейнера
WORKDIR /app

# Копирование собранного JAR-файла
COPY kitchen/target/kitchen-1.0-SNAPSHOT.jar app.jar

# Определение переменных среды для подключения к базе данных PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://my-postgres-host:5432/my-pizza-db
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Определение порта, на котором будет работать приложение
EXPOSE 8888

# Команда запуска приложения
CMD ["java", "-jar", "app.jar"]
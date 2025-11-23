# 1. Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml first
COPY pom.xml .

# Download dependencies
RUN mvn -B dependency:go-offline

# Now copy source
COPY src ./src

# Build the project
RUN mvn -B -DskipTests clean package


# 2. Test execution stage
FROM maven:3.9.6-eclipse-temurin-21
WORKDIR /app

# Copy source again for test execution
COPY . .

# Copy compiled code
COPY --from=build /app/target ./target

CMD ["mvn", "test"]

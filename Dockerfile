FROM mcr.microsoft.com/playwright/java:v1.52.0-noble

WORKDIR /app

COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B

COPY src ./src

ENV BROWSER=chromium
ENV UI_Headless=True

CMD ["mvn", "test", "-DsuiteXmlFile=src/test/resources/suite/ui-suite.xml"]
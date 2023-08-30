FROM public.ecr.aws/docker/library/amazoncorretto:17.0.8-al2023-headless
EXPOSE 8000
ADD target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
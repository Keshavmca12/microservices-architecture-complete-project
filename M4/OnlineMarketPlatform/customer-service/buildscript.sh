mvn clean package -o
docker build . -t sauravdas90/customer-service:1.0.0
docker push sauravdas90/customer-service:1.0.0

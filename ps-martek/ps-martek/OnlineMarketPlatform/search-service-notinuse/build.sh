
docker build -t nikhil/search-service:${image_version} .
docker push nikhil/search-service:${image_version}

helm package helm/search-service  --version 1.0 --app-version ${image_version}
helm chart save helm/search-service search-service:${image_version}
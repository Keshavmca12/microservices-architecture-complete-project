
helm uninstall search-service
helm upgrade --install search-service \
  --set image.tag=1.0 \
  ./helm/search-service
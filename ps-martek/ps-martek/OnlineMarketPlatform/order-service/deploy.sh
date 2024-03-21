
helm uninstall order-service
helm upgrade --install order-service \
  --set image.tag=1.0 \
  ./helm/order-service
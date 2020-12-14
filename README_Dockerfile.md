## Description
- Product Catalogue Service

## Build&Push docker image
docker build -t docker.asseco-ce.com/jtdigi/services/docker/product-catalogue-service:0.0.1 .
docker tag docker.asseco-ce.com/jtdigi/services/docker/product-catalogue-service:0.0.1 docker.asseco-ce.com/jtdigi/services/docker/product-catalogue-service:latest
docker push docker.asseco-ce.com/jtdigi/services/docker/product-catalogue-service

## OpenShift deploy
oc process -f product-catalogue-service-template.yaml --param-file=.env | oc create --save-config -f -
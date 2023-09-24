FROM localstack/localstack:2.0.2

ENV DEBUG 0
ENV DEFAULT_REGION "sa-east-1"
ENV LOCALSTACK_HOST "localstack.railway.internal"
ENV DOCKER_HOST "unix:///var/run/docker.sock"

COPY aws-scripts/init.sh /docker-entrypoint-initaws.d/init.sh

EXPOSE 4566
EXPOSE 4510-4559

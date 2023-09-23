FROM localstack/localstack:2.1.0

ENV DEBUG 0
ENV DEFAULT_REGION "sa-east-1"
ENV LOCALSTACK_HOST "localstack.railway.internal"
ENV DOCKER_HOST "unix:///var/run/docker.sock"

COPY aws-scripts/ /etc/localstack/init/ready.d/

EXPOSE 53
EXPOSE 443
EXPOSE 4566
EXPOSE 4510-4559

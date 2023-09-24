FROM localstack/localstack:2.0.2

ENV DEBUG 0
#ENV LOCALSTACK_HOST "localstack"
ENV DOCKER_HOST "unix:///var/run/docker.sock"

COPY ./aws-scripts/init.sh /etc/localstack/init/ready.d/init.sh

EXPOSE 4566 4510-4559 5678


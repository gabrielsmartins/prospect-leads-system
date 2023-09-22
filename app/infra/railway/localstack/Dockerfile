FROM localstack/localstack:2.1.0

COPY ./aws-scripts/ /etc/localstack/init/ready.d

ENV DEFAULT_REGION=sa-east-1
ENV HOSTNAME=localhost
ENV DOCKER_HOST=unix:///var/run/docker.sock

EXPOSE 53
EXPOSE 443
EXPOSE 4566
EXPOSE 4510-4559
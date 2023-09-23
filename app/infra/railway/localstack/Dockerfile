FROM localstack/localstack:1.4.0

ENV DEBUG=0
ENV DEFAULT_REGION=sa-east-1
ENV HOSTNAME_EXTERNAL=localstack
ENV DOCKER_HOST=unix:///var/run/docker.sock

COPY aws-scripts/ /docker-entrypoint-initaws.d/

EXPOSE 53
EXPOSE 443
EXPOSE 4566
EXPOSE 4510-4559

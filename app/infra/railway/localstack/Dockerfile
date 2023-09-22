FROM localstack/localstack:2.1.0

RUN pip install awscli

ENV DEFAULT_REGION=sa-east-1
ENV HOSTNAME=localstack
ENV DOCKER_HOST=unix:///var/run/docker.sock

EXPOSE 53
EXPOSE 443
EXPOSE 4566
EXPOSE 4510-4559

RUN aws configure set aws_access_key_id default_access_key --profile=localstack
RUN aws configure set aws_secret_access_key default_secret_key --profile=localstack 
RUN aws configure set region sa-east-1 --profile=localstack 
RUN aws dynamodb create-table --table-name Leads \
        --attribute-definitions AttributeName=Id,AttributeType=S \
        --key-schema AttributeName=Id,KeyType=HASH \
        --billing-mode PAY_PER_REQUEST \
        --endpoint localstack:4566 \
        --region sa-east-1 \
        --profile=localstack


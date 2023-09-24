#!/bin/bash
echo "########### Creating profile ###########"
table="Leads"
echo "########### Creating DynamoDB ${table} Table ###########"

awslocal dynamodb \
    create-table --table-name $table \
    --attribute-definitions \
        AttributeName=Id,AttributeType=S \
    --key-schema \
        AttributeName=Id,KeyType=HASH \
    --billing-mode PAY_PER_REQUEST \
    --region sa-east-1 \
    --endpoint-url="http://0.0.0.0:4566"

echo "########### DynamoDB ${table} Table was created sucessfully ###########"
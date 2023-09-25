#!/bin/bash
echo "########### Registering e-mail ###########"
awslocal ses verify-email-identity --email-address no-reply@localstack.cloud
echo "########### E-mail was registered successfully ###########"
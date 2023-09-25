#!/bin/bash
email="no-reply@localstack.cloud"
echo "########### Registering e-mail ${email} ###########"
awslocal ses verify-email-identity --email-address $email
echo "########### E-mail ${email} was registered successfully ###########"
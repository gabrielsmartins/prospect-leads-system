#!/bin/bash
email="tccpucminas2023@yahoo.com"
echo "########### Registering e-mail ${email} ###########"
awslocal ses verify-email-identity --email-address $email --region sa-east-1
echo "########### E-mail ${email} was registered successfully ###########"
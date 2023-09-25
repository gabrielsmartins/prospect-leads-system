#!/bin/bash
email="tccleadspucminas.2023@gmail.com"
echo "########### Registering e-mail ${email} ###########"
awslocal ses verify-email-identity --email-address $email
echo "########### E-mail ${email} was registered successfully ###########"
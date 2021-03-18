#!/bin/bash
BUCKET_ID=lambdaxper
BUCKET_NAME=lambda-artifacts-$BUCKET_ID
echo $BUCKET_NAME > bucket-name.txt
aws s3 mb s3://$BUCKET_NAME

#!/bin/bash

scp -i ./deploy_rsa target/lwsapp.jar lws@www.learnwebservices.com:/opt/lwsapp

ssh -i ./deploy_rsa lws@www.learnwebservices.com 'sudo systemctl restart lwsapp.service'

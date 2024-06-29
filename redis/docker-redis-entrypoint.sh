#!/bin/sh
set -e

redis-server --tls-port 6379 --port 0 \
    --tls-cert-file /app/tls/redis.crt \
    --tls-key-file /app/tls/redis.key \
    --tls-ca-cert-file /app/tls/ca.crt \
    --tls-auth-clients yes \
    --requirepass hanuma \
    --loglevel debug \
    --save 60 1

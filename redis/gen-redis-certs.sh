
#!/bin/bash

# COPIED/MODIFIED from the redis server gen-certs util
# https://github.com/redis/redis/blob/cc0091f0f9fe321948c544911b3ea71837cf86e3/utils/gen-test-certs.sh

# Generate some test certificates which are used by the regression test suite:
#
#   tls/ca.{crt,key}          Self signed CA certificate.
#   tls/redis.{crt,key}       A certificate with no key usage/policy restrictions.
#   tls/client.{crt,key}      A certificate restricted for SSL client usage.
#   tls/server.{crt,key}      A certificate restricted for SSL server usage.
#   tls/redis.dh              DH Params file.

generate_cert() {
    local name=$1
    local cn="$2"
    local opts="$3"

    local keyfile=tls/${name}.key
    local certfile=tls/${name}.crt

    [ -f $keyfile ] || openssl genrsa -out $keyfile 2048
    openssl req \
        -new -sha256 \
        -subj "/O=Redis Test/CN=$cn" \
        -key $keyfile | \
        openssl x509 \
            -req -sha256 \
            -CA tls/ca.crt \
            -CAkey tls/ca.key \
            -CAserial tls/ca.txt \
            -CAcreateserial \
            -days 365 \
            $opts \
            -out $certfile
}

mkdir -p tls
[ -f tls/ca.key ] || openssl genrsa -out tls/ca.key 4096
openssl req \
    -x509 -new -nodes -sha256 \
    -key tls/ca.key \
    -days 3650 \
    -subj '/O=Redis Test/CN=Certificate Authority' \
    -out tls/ca.crt

cat > tls/openssl.cnf <<_END_
[alt_names]
DNS.1 = localhost
IP.1 = 127.0.0.1
[ server_cert ]
keyUsage = digitalSignature, keyEncipherment
nsCertType = server
subjectAltName = @alt_names
[ client_cert ]
keyUsage = digitalSignature, keyEncipherment
nsCertType = client
_END_

generate_cert server "*" "-extfile tls/openssl.cnf -extensions server_cert"
generate_cert client "*" "-extfile tls/openssl.cnf -extensions client_cert"
#generate_cert redis "*"

[ -f tls/redis.dh ] || openssl dhparam -out tls/redis.dh 2048
[ -f tls/redis-client.p12 ] || openssl pkcs12 -export -out tls/redis-client.p12 -inkey tls/client.key -in tls/client.crt -passout pass:ramana
[ -f tls/ca.p12 ] || keytool -importcert -keystore tls/ca.p12 -storetype PKCS12 -alias redisca -file tls/ca.crt -storepass ramana -noprompt
keytool -importcert -keystore tls/ca.p12 -storetype PKCS12 -alias redisserver -file tls/server.crt -storepass ramana -noprompt

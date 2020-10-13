protoc -I=./src/protos fintech.proto \
  --js_out=import_style=commonjs:./src/grpc \
  --grpc-web_out=import_style=commonjs,mode=grpcwebtext:./src/grpc

~/Pro/vng/module-10/BenchmarkMonitor/ghz --insecure \
  --proto ../src/main/proto/fintech.proto \
  --call fintech.FintechService.getBalance \
  -n 1000 -c 50 \
  -m '{"Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiZXhwIjo0NjIyNDcwNDIyfQ.V893skuYl-2x28mLVK7VZgNunsJjZQrxHBXHMCkWYIs"}' \
  0.0.0.0:8080

~/Pro/vng/module-10/BenchmarkMonitor/ghz --insecure \
  --proto ../src/main/proto/fintech.proto \
  --call fintech.FintechService.transfer \
  -n 1000 -c 50 \
  -d '{"receiver_id": 10, "amount": 10, "message":"hello", "password":"tan" }' \
  -m '{"Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiZXhwIjo0NjIyNDcwNDIyfQ.V893skuYl-2x28mLVK7VZgNunsJjZQrxHBXHMCkWYIs"}' \
  0.0.0.0:8080

~/Pro/vng/module-10/BenchmarkMonitor/ghz --insecure \
  --proto ../src/main/proto/fintech.proto \
  --call fintech.FintechService.getHistory \
  -n 2000 -c 50 \
  -d '{"offset": 0}' \
  -m '{"Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI5IiwiZXhwIjo0NjIyNDcwNDIyfQ.V893skuYl-2x28mLVK7VZgNunsJjZQrxHBXHMCkWYIs"}' \
  0.0.0.0:8080
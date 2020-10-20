~/MinhDuc/module-10/SnailProject/src/ServerSide/Note/ghz --insecure \
  --proto ../src/main/proto/fintech.proto \
  --call fintech.FintechService.transfer \
  -n 100000 -c 20 \
  -d '{"sender_id": 8, "receiver_id": 4, "amount": 10, "message":"hello", "password":"suneo" }' \
  -m '{"Authorization":"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiZXhwIjo0NjIyNDcwNDIyfQ.ZXtvktZytH4bK2aaokJTWyxKofpyu8hzyjL7OhljG54"}' \
  0.0.0.0:8000
# bookStore

### Команда для создания бд в докере

```shell

docker run --rm --name postgres_books -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=books_db -d -p 5432:5432 postgres:16.3 -c max_locks_per_transaction=128

```

### Адрес swagger
- [Адрес 1](http://localhost:8082/book-service/swagger-ui/index.html)
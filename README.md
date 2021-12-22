# Cinema Room REST Service
### <a href="https://hyperskill.org/projects/189" target="_blank">(a JetBrains Academy Project)</a>
### A simple Spring REST service that helps manage a small movie theatre. Handle HTTP requests in controllers, create services and respond with JSON objects.

__________________________________________________________

### Description

#### Virtual movie theater with the help of a REST service.

There are many fun activities on the planet Earth, and one of them is going to the movies. It is arguably the most accessible, inclusive, and fulfilling entertainment. Bring your friends or loved ones — each movie is a whole new adventure waiting to be experienced.

#### The movie theater has 9 rows with 9 seats each and there is a endpoint that will return the information about the cinema in JSON format:
`/seats` endpoint handles `GET` requests and returns the information about the movie theatre.

The response contains information about the rows, columns, available seats, and price. The response is a JSON object and has the following format:

```json
{
   "total_rows":5,
   "total_columns":6,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },

      ........

      {
         "row":5,
         "column":5,
         "price":8
      },
      {
         "row":5,
         "column":6,
         "price":8
      }
   ]
}
```

#### Movie-goers are able to check the availability of seats before purchasing a ticket! There is an endpoint to check and purchase an available ticket. If the ticket has been purchased or the request contains wrong information about the ticket, an error message is returned:

The `/purchase` endpoint handles `POST` requests and marks a booked ticket as purchased.

A request contain the following data:

* `row` — the row number;
* `column` — the column number.

These variables check if the specified ticket is available. If the ticket is booked, the seat will be marked as purchased and will not be shown it in the list.

The ticket price is determined by a row number. If the row number is less or equal to 4, the price will be set at 10. All other rows cost 8 per seat.

If the seat is taken, the response will be with a `400 (Bad Request)` status code. The response body contains the following:

```json
{
    "error": "The ticket has been already purchased!"
}
````

If users pass a wrong row/column number, the response will be with a `400 (Bad Request)` status code and the following line:

```json
{
    "error": "The number of a row or a column is out of bounds!"
}
```

#### We live in a fast world, and our plans may change very quickly! Customers have ability to refund a ticket if they can't come and watch a movie. Tokens are used to secure the ticket refund process:

The `randomUUID()` method of the UUID class is used to generate tokens.

If the purchase is successful, the response body will be as follows:

```json
{
    "token": "00ae15f2-1ab6-4a02-a01f-07810b42c0ee",
    "ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
```

The `/return` endpoint, which will handle `POST` requests and allow customers to refund their tickets.

The request should have the token feature that identifies the ticket in the request body. Once you have the token, the ticket it relates to is identified and marked as available. The response will be as follows:

```json
{
    "returned_ticket": {
        "row": 1,
        "column": 1,
        "price": 10
    }
}
``` 

The `returned_ticket` contains the information about the returned ticket.

If the ticket is not identified by the token, the program respond with a `400 (Bad Request)` status code and the following response body:

```json
{
    "error": "Wrong token!"
}
```

#### Theatre managers may need some statitics:

The `/stats` endpoint handles `POST` requests with URL parameters. If the URL parameters contain a password key with a `super_secret` value, the movie theatre statistics is returned in the following format:

```json
{
    "current_income": 0,
    "number_of_available_seats": 81,
    "number_of_purchased_tickets": 0
}
```

Take a look at the description of keys:

* current_income — shows the total income of sold tickets.
* number_of_available_seats — shows how many seats are available.
* number_of_purchased_tickets — shows how many tickets were purchased.

If the parameters don't contain a password key or a wrong value has been passed, the program respond with a `401 (Unauthorized)` status code. The response body should contain the following:

```json
{
    "error": "The password is wrong!"
}
```

__________________________________________________________

### Examples

**Example 1:** a `GET /seats` request:

Response body:

```json
{
   "total_rows":9,
   "total_columns":9,
   "available_seats":[
      {
         "row":1,
         "column":1,
         "price":10
      },
      {
         "row":1,
         "column":2,
         "price":10
      },
      {
         "row":1,
         "column":3,
         "price":10
      },

      ........

      {
         "row":9,
         "column":8,
         "price":8
      },
      {
         "row":9,
         "column":9,
         "price":8
      }
   ]
}
```

**Example 2:** a correct `POST /purchase` request:

Request body:

```json
{
    "row": 3,
    "column": 4
}
```

Response body:

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556",
    "ticket": {
        "row": 3,
        "column": 4,
        "price": 10
    }
}
```

**Example 3:** `POST /return` with the correct token:

Request body:

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```

Response body:

```json
{
    "returned_ticket": {
        "row": 1,
        "column": 2,
        "price": 10
    }
}
```

**Example 4:** `POST /return` with an expired token:

Request body:

```json
{
    "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
}
```

Response body:

```json
{
    "error": "Wrong token!"
}
```

 **Example 5:** a `POST /stats` request with no parameters:

Response body:

```json
{
    "error": "The password is wrong!"
}
```

**Example 6:** a `POST /stats` request with the correct password:

Response body:

```json
{
    "current_income": 30,
    "number_of_available_seats": 78,
    "number_of_purchased_tickets": 3
}
```

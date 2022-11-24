# API SPEC

## Create Group (Admin)

Request :

- Method : POST
- Endpoint : '/api/groups'
- Header :
    - Content-Type : application/json
    - Accept : applcation/json

- Body :

```json
{
  "id_group": "String, unique",
  "name": "String",
  "detail": "String",
  "avatar": "File",
  "qrcode": "File"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id_group": "String, unique",
    "name": "String",
    "detail": "String",
    "avatar": "File",
    "qrcode": "File",
    "chats": [
      {
        "id_chat": "String, unique",
        "message": "File",
        "date": "Date"
      }
    ],
    "users": [
      {
        "id_user": "String, Unique",
        "joinAt": "Date"
      }
    ]
  }
}
```

## Get Group

Request :

- Method : GET
- Endpoint : '/api/groups/{id_group}'
- Header :
    - Accept : applcation/json

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id_group": "String, unique",
    "name": "String",
    "detail": "String",
    "avatar": "File",
    "qrcode": "File"
  }
}
```

## Update Group

Request :

- Method : PUT
- Endpoint : '/api/groups/{id_group}'
- Header :
    - Content-Type : application/json
    - Accept : applcation/json

- Body :

```json
{
  "name": "String",
  "detail": "String",
  "avatar": "File"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id_group": "String, unique",
    "name": "String",
    "detail": "String",
    "avatar": "File",
    "qrcode": "File",
    "chats": [
      {
        "id_chat": "String, unique",
        "message": "File",
        "date": "Date"
      }
    ],
    "users": [
      {
        "id_user": "String, Unique",
        "joinAt": "Date"
      }
    ]
  }
}
```

## Update (User)

Request :

- Method : PUT
- Endpoint : '/api/groups/{id_group}'
- Header :
    - Content-Type : application/json
    - Accept : applcation/json

- Body :

```json
{
  "idMember": "String, Unique",
  "joinAt": "Date"
}
```

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "id_group": "String, unique",
    "name": "String",
    "detail": "String",
    "avatar": "File",
    "qrcode": "File",
    "chats": [
      {
        "id_chat": "String, unique",
        "message": "File",
        "date": "Date"
      }
    ],
    "users": [
      {
        "id_user": "String, Unique",
        "joinAt": "Date"
      }
    ]
  }
}
```

## Update (Chat)

Request :

- Method : PUT
- Endpoint : '/api/groups/{id_group}/chats'
- Header :
  - Content-Type : application/json
  - Accept : applcation/json

- Body :

```json
{
  "id_chat": "String, Unique",
  "message": "File",
  "detail": "String",
  "date": "String"
}
```

## List Group

Request :

- Method : GET
- Endpoint : '/api/groups'
- Header :
    - Accept : applcation/json
- Query param :
    - size : number,
    - page : number

- Response :

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "code": "number",
      "status": "string",
      "data": {
        "id_group": "String, unique",
        "name": "String",
        "detail": "String",
        "avatar": "File",
        "chats": [
          {
            "id_chat": "String, unique",
            "message": "File",
            "date": "Date"
          }
        ],
        "users": [
          {
            "id_user": "String, Unique"
          }
        ]
      }
    }
  ]
}
```

## Delete Group

Request :

- Method : DELETE
- Endpoint : '/api/groups/{id_group}'
- Header :
    - Accept : applcation/json

- Response :

```json
{
  "code": "number",
  "status": "string"
}
```
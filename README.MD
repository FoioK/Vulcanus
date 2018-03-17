# Vulcanus

Vulcanus is REST API for image to base64 conversion.

## API
All API requests should be prefixed with api/base64. API requests do not require authentication.

### CRUD routes

#### `GET /get`
    parameters: url={#} (required)
    
    example: http://localhost:8080/api/base64/get/?url=https://goo.gl/AfHd5t

Return base64 string.

#### `POST /convert` consume only json type
    body: url link list (required)

Return base64 string list.

## License

Vault is licensed under the [MIT License](http://opensource.org/licenses/MIT).

## Author

Wojciech Kozioł
# Aplicación de Precios E-commerce

Esta aplicación es un servicio de consulta de precios para una plataforma de comercio electrónico. Permite consultar el precio final de un producto para una cadena específica en función de la fecha de aplicación.

## Características

Consulta de precios basada en la fecha de aplicación, el identificador del producto y el identificador de la cadena.
Utiliza una base de datos en memoria (H2) para almacenar los datos de precios.
Desarrollada en Java con Spring Boot.

## Requisitos
Java 17
Maven
Docker (para la construcción y ejecución del contenedor Docker)

## Instalación y Ejecución
```bash
git clone https://github.com/alemr92/precios-ecommerce
```

## Usage
Navega al directorio del proyecto:
```bash
cd precios-ecommerce
```
Construye el proyecto con Maven:
```bash
mvn clean package -DskipTests
```
Construye la imagen Docker:
```bash
docker build -t precios-ecommerce .
```
Ejecuta la imagen Docker:
```bash
docker run -p 8081:8080 precios-ecommerce
```
La aplicación estará disponible en http://localhost:8080/api.

## Uso
Puedes realizar consultas de precios utilizando el endpoint REST proporcionado por la aplicación. Aquí tienes un ejemplo de cómo hacer una consulta utilizando cURL:
```bash
curl -X GET 'http://localhost:8080/api/getPrice?date=2020-06-15 10:00&productId=35455&brandId=1'
```
Reemplaza la fecha, el identificador del producto y el identificador de la cadena según sea necesario.

## Tests
Para hacer los tests ejecutar el siguiente comando
```bash
docker build -t precios-ecommerce .
docker run precios-ecommerce test
```
## License

[MIT](https://choosealicense.com/licenses/mit/)

## OpenAPI
```bash
openapi: 3.0.0
info:
  title: API de Precios
  description: API para consultar precios de productos de una cadena
  version: 1.0.0

servers:
  - url: http://localhost:8080
    description: Servidor local

paths:
  /api/:
    getPrice:
      summary: Consultar precios
      description: Permite consultar el precio de un producto para una cadena en una fecha específica.
      parameters:
        - in: query
          name: date
          required: true
          schema:
            type: string
            format: date-time
            example: "2020-06-14T10:00:00"
          description: Fecha de aplicación del precio
        - in: query
          name: productId
          required: true
          schema:
            type: integer
            example: 35455
          description: ID del producto
        - in: query
          name: brandId
          required: true
          schema:
            type: integer
            example: 1
          description: ID de la cadena (brand)
      responses:
        '200':
          description: Precios encontrados satisfactoriamente
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/Price'
        '404':
          description: No se encontraron precios para los parámetros especificados

components:
  schemas:
    Price:
      type: object
      properties:
        productId:
          type: integer
          example: 35455
          description: ID del producto
        brandId:
          type: integer
          example: 1
          description: ID de la cadena (brand)
        priceList:
          type: integer
          example: 1
          description: Identificador de la tarifa de precios
        startDate:
          type: string
          format: date-time
          example: "2020-06-14T00:00:00"
          description: Fecha de inicio de aplicación del precio
        endDate:
          type: string
          format: date-time
          example: "2020-12-31T23:59:59"
          description: Fecha de fin de aplicación del precio
        price:
          type: number
          format: float
          example: 35.50
          description: Precio final
        currency:
          type: string
          example: "EUR"
          description: ISO de la moneda
```

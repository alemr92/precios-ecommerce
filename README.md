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
docker run -p 8080:8080 precios-ecommerce
```
La aplicación estará disponible en http://localhost:8080/api.

## Uso
Puedes realizar consultas de precios utilizando el endpoint REST proporcionado por la aplicación. Aquí tienes un ejemplo de cómo hacer una consulta utilizando cURL:
```bash
curl -X GET 'http://localhost:8080/api/getPrice?date=2020-06-14T10:00:00&productId=35455&brandId=1'
```
Reemplaza la fecha, el identificador del producto y el identificador de la cadena según sea necesario.

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)
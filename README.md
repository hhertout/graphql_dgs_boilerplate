# Graphql Boilerplate

- DGS framework
- Hibernate

# Features

Boilerplate template with :

- Graphql Schema & code generation
- Query & mutation data fetcher examples implemented
- Database integration with Hibernate
- Guard utility class to protect the data fetched
- Error handling integration for better readability
- Micrometer integration

## Generate DTOs from Graphql schemas

GraphQL schemas are located on the `/src/resources/schema` directory.

```bash
./gradlew generateJava
```

## Building Locally

```bash
export $(grep -v '^#' .env.test | xargs)
./gradlew build
```

## Docker

Docker files built for docker integration are located in the `.docker` directory.

### Dev

If you would like to use docker during the development, a docker file is specifically provide for this purpose and set
as default in the `docker-compose.yml` file.

To run it, simply run the docker compose command:

```bash
docker compose up -d
```

### Prod

For a production ready docker image, simply use the docker file `prod.dockerfile`, located in the `.docker` directory.
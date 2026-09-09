# Pensionat Kademina — Rating Service

The **rating service** is a Spring Boot REST API that owns guest reviews for the Pensionat Kademina guesthouse system. It lets guests rate and comment on the room they stayed in — but only if they can prove they actually booked it, by checking with the booking service before accepting a review.

## Part of a microservices system

| Service | Repo | Responsibility |
|---|---|---|
| Booking service | [`pensionat-kademina-booking`](https://github.com/gabbson42/pensionat-kademina-booking) | Rooms, bookings, UI — called by this service to validate reviews |
| Customer service | [`pensionat-kademina-customer-service`](https://github.com/gabbson42/pensionat-kademina-customer-service) | Customer records |
| **Rating service** (this repo) | `pensionat-kademina-rating-service` | Guest reviews/ratings (this service) |

When a review is submitted, this service calls the booking service's internal endpoint (`GET /api/bookings/check?customerId={id}&roomId={id}`) to confirm the guest actually booked that room. If not, the review is rejected with `409 Conflict`. In the shared `docker-compose.yml` (in the booking service repo) it runs as `rating-service`, backed by its own `rating_db` MySQL database, and is reachable on host port `8083` (container port `8080`).

```
booking-service ◀── GET /api/bookings/check?customerId=..&roomId=.. ── rating-service
                                                                        (before saving a review)
```

## Tech stack

- Java 25, Spring Boot (`spring-boot-starter-parent` 4.1.1)
- Spring MVC (REST, no UI)
- Spring Data JPA + MySQL
- Bean Validation
- Lombok, Spring DevTools
- Spring's `RestClient` to call the booking service
- Maven (wrapper included), Docker, Kubernetes manifests (`k8s/`)

## Domain model

`Review` — `id`, `customerId`, `roomId`, `rating` (1–5), `comment`, `date`.

## API

| Method | Path | Description |
|---|---|---|
| `POST` | `/api/reviews` | Submit a review. Validated against the booking service; rejected with `409 Conflict` if the customer never booked the room. |
| `GET` | `/api/reviews` | List all reviews. |

Request body (`ReviewRequestDto`):
```json
{
  "customerId": 7,
  "roomId": 3,
  "rating": 5,
  "comment": "Lovely stay, very quiet room."
}
```
`rating` must be between 1 and 5.

Response body (`ReviewResponseDto`):
```json
{
  "customerId": 7,
  "customerName": null,
  "roomId": 3,
  "rating": 5,
  "comment": "Lovely stay, very quiet room.",
  "date": "2026-09-09"
}
```
> `customerName` is currently a placeholder (not yet wired up to the customer service) — see [Known limitations](#known-limitations).

A sample request is included in [`request.http`](request.http).

## Getting started

### Prerequisites
- Java 25
- MySQL 8 (or run via Docker Compose from the booking service repo — see below)
- The booking service running and reachable (used to validate bookings)
- Maven (or use the included `./mvnw`)

### Run locally with Maven
```bash
./mvnw spring-boot:run
```
Default configuration (override via env vars or `application.properties`):
```properties
booking-service.url=${BOOKING_SERVICE_URL:http://booking-service:8080}
spring.datasource.url=${SPRING_DATASOURCE_URL:jdbc:mysql://localhost:3306/rating_db}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME:rating_user}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD:ratingDB}
```

### Run as part of the full system
This service has no docker-compose file of its own — it's one of three services wired up by the `docker-compose.yml` in the [booking service repo](https://github.com/gabbson42/pensionat-kademina-booking). Clone all three repos as siblings and run `docker compose up --build` from there. This service will be available at **http://localhost:8083**.

### Build & run standalone
```bash
./mvnw clean package
docker build -t pensionat-kademina-rating-service .
docker run -p 8083:8080 -e BOOKING_SERVICE_URL=http://<booking-host>:8080 pensionat-kademina-rating-service
```

### Kubernetes
Manifests for this service and its database are in [`k8s/`](k8s):
```bash
kubectl apply -f k8s/rating-db.yaml
kubectl apply -f k8s/rating-service.yaml
```

## Tests

```bash
./mvnw test
```

## Known limitations

- `customerName` on a review is currently hardcoded/left null rather than resolved from the customer service — a future enhancement would be to call the customer service to enrich review responses with the guest's actual name.

## Notes
- The dependency on the booking service is a hard runtime dependency for writes (`POST /api/reviews`) but not for reads (`GET /api/reviews`).

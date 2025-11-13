# Tech Knowledge Pills Backend

ASP.NET Core Web API backend with PostgreSQL database, containerized with Docker Compose.

## Prerequisites

- Docker Desktop (or Docker Engine + Docker Compose)
- .NET 8 SDK (optional, for local development without Docker)

## Quick Start

### Using Docker Compose (Recommended)

1. **Start the services:**
   ```bash
   docker-compose up -d
   ```

2. **View logs:**
   ```bash
   docker-compose logs -f api
   ```

3. **Access the API:**
- API: http://localhost:5001
- Swagger UI: http://localhost:5001/swagger
- Database: localhost:5432

**Note**: Port 5000 is used by macOS AirPlay Receiver, so the API uses port 5001 by default.

4. **Stop the services:**
   ```bash
   docker-compose down
   ```

### Using Make Commands

```bash
# Build and start services
make build
make up

# View logs
make logs-api

# Run database migrations
make migrate

# Stop services
make down

# Clean everything (including volumes)
make clean
```

## Configuration

### Environment Variables

Create a `.env` file in the backend directory (or use the provided `.env.example`):

```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=techknowledgepills
JWT_KEY=YourSuperSecretKeyThatShouldBeAtLeast32CharactersLong!
JWT_ISSUER=TechKnowledgePills
JWT_AUDIENCE=TechKnowledgePillsUsers
ASPNETCORE_ENVIRONMENT=Development
API_PORT=5000
DB_PORT=5432
```

### Connection Strings

- **Docker Compose**: Uses `Host=db` (service name)
- **Local Development**: Uses `Host=localhost` (see `appsettings.Development.json`)

## Database Migrations

The application automatically runs migrations on startup. To manually run migrations:

```bash
# Using Docker
docker-compose exec api dotnet ef database update --project TechKnowledgePills.Infrastructure --startup-project TechKnowledgePills.API

# Or using Make
make migrate
```

To create a new migration:

```bash
make migration name=YourMigrationName
```

## Development

### Local Development (Without Docker)

1. Ensure PostgreSQL is running locally
2. Update `appsettings.Development.json` with your local connection string
3. Run:
   ```bash
   cd TechKnowledgePills.API
   dotnet run
   ```

### Development with Docker

1. Use `docker-compose.override.yml` for local overrides
2. Mount volumes for hot reload (uncomment in override file)
3. Access logs: `docker-compose logs -f api`

## Project Structure

```
backend/
├── TechKnowledgePills.API/          # Main API project
├── TechKnowledgePills.Core/         # Domain models and interfaces
├── TechKnowledgePills.Infrastructure/  # Data access and services
├── Dockerfile                        # API container definition
├── docker-compose.yml               # Service orchestration
├── docker-compose.override.yml      # Local development overrides
├── .dockerignore                    # Docker ignore patterns
└── Makefile                         # Convenience commands
```

## API Endpoints

Once running, access Swagger UI at: http://localhost:5000/swagger

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Login
- `GET /api/auth/me` - Get current user (requires auth)

### Content
- `GET /api/content` - Get all content
- `GET /api/content/{id}` - Get content by ID
- `POST /api/content` - Create content
- `PUT /api/content/{id}` - Update content
- `DELETE /api/content/{id}` - Delete content

### Stress Indicators
- `GET /api/stressindicator` - Get all stress indicators
- `GET /api/stressindicator/latest` - Get latest stress indicator
- `POST /api/stressindicator/generate-mock` - Generate mock data
- `POST /api/stressindicator` - Create stress indicator

### Recommendations
- `GET /api/recommendation` - Get personalized recommendations

## Troubleshooting

### Database Connection Issues

1. Check if the database container is healthy:
   ```bash
   docker-compose ps
   ```

2. Check database logs:
   ```bash
   docker-compose logs db
   ```

3. Verify connection string in environment variables

### Port Conflicts

If ports 5000 or 5432 are already in use, update them in `docker-compose.yml` or `.env`:

```yaml
ports:
  - "5001:8080"  # Change 5000 to 5001
```

### Reset Database

To completely reset the database:

```bash
docker-compose down -v
docker-compose up -d
```

## Production Considerations

For production deployment:

1. **Change default passwords** in environment variables
2. **Use strong JWT keys** (at least 32 characters)
3. **Set `ASPNETCORE_ENVIRONMENT=Production`**
4. **Enable HTTPS** (update Dockerfile and configuration)
5. **Use secrets management** (Docker secrets, Azure Key Vault, etc.)
6. **Configure proper CORS** origins
7. **Set up database backups**
8. **Use managed PostgreSQL** service if possible

## License

This project is created for educational purposes.


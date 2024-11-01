# OptiLab
laboratory management system
# Optilab backend part
more details will be added soon
# Optilab Frontend

This is the frontend part of the Optilab project, built using Angular. This guide explains how to clone the repository, run the application, and execute unit tests using Docker and Docker Compose.

## Prerequisites

- Ensure you have [Docker](https://www.docker.com/) installed and running on your machine.

## Cloning the Repository

To get started with the Optilab frontend, clone the repository using the following command:

```bash
git clone https://github.com/yourusername/optilab-frontend.git
```

Replace `yourusername` with your GitHub username or the appropriate repository path.

After cloning, navigate into the project directory:

```bash
cd optilab-frontend
```

## Running the Application

Follow these steps to build and run the Angular application using Docker Compose:

1. **Build and start the Docker containers:**
   ```bash
   docker-compose up --build
   ```

   This command builds the images and starts the Angular development server. The application will be accessible at [http://localhost:4200](http://localhost:4200).

## Running Unit Tests

To run the unit tests using Docker Compose, follow these steps:

1. **Run the tests in a Docker container:**
   ```bash
   docker-compose run test
   ```

   This command will run the unit tests using Jest (or your configured test framework) and display the results in the terminal.

### Explanation of Services

- **`angular-app`**: This service builds the Angular application and serves it on port `4200`.
- **`test`**: This service runs the unit tests defined in the project.

## Project Structure

- **`src/`**: The source code for the Angular application.
- **`Dockerfile`**: The Docker build configuration.
- **`docker-compose.yml`**: Docker Compose configuration for easy setup.
- **`package.json`**: Contains project dependencies and scripts.




## Troubleshooting

- If `localhost:4200` is not accessible, ensure the Angular app is configured to bind to `0.0.0.0` by modifying the `start` script in `package.json`:
  ```json
  "start": "ng serve --host 0.0.0.0"
  ```

- Ensure no firewall or security software is blocking Docker from using the exposed port.

# Optilab Devops part
more details will be added soon


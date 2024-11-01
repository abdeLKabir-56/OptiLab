# Use Node.js LTS version
FROM node:18-alpine

WORKDIR /app

# Copy package files
COPY package*.json ./

# Remove existing node_modules and package-lock.json if they exist
RUN rm -rf node_modules package-lock.json

# Install dependencies with platform-specific binaries
RUN npm install

# Copy the rest of the application
COPY . .

# Expose port 4200 for Angular dev server
EXPOSE 4200

# Command to run tests
CMD ["npm", "test"]
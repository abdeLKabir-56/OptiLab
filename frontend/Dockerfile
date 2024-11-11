# Use Node.js LTS version
FROM node:18-alpine

WORKDIR /app

# Copy package files
COPY package*.json ./

# Install dependencies with platform-specific binaries
RUN npm install
RUN npm i @angular/cli
COPY . /app/
EXPOSE 4200
CMD npm start



# Build stage
FROM node:18-alpine AS build-stage
WORKDIR /app
COPY package*.json ./
RUN npm i
COPY ./ .
RUN npm run build
# Final stage
FROM nginx:alpine
# Copy the built app from the previous stage
COPY --from=build-stage /app/dist/frontend/browser /usr/share/nginx/html
# Copy the custom Nginx configuration file
COPY ngnix.conf /etc/nginx/conf.d/default.conf
# Expose port 80
EXPOSE 80

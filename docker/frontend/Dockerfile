FROM node:14  

WORKDIR /app

COPY package*.json ./

RUN npm install -g npm@latest

COPY . .

EXPOSE 4200

CMD ["npm", "start"]

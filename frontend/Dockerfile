FROM node:lts
WORKDIR /usr/src/app
RUN npm install -g vite --only=development
COPY package*.json ./
COPY . .
RUN npm install --only=development

EXPOSE 5173
CMD ["npm", "run", "dev"]

FROM node:lts
WORKDIR /usr/src/app
RUN npm install -g vite --only=development
COPY package*.json ./
COPY . .
RUN yarn install --only=development

VOLUME ["/usr/src/app"]
VOLUME ["/usr/src/app/node_modules"]
EXPOSE 5173
CMD ["yarn", "run", "dev"]

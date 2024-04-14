FROM node:21
WORKDIR /app

COPY ./package.json .
COPY ./package-lock.json .
RUN npm install

COPY . ./
RUN npm run build

EXPOSE 8000
CMD [ "npm", "run", "dev" ]
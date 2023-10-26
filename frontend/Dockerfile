FROM node:18.17.1-bullseye as builder

WORKDIR /usr/src/app

COPY . .

RUN npm i

EXPOSE 3000

CMD ["npm", "start"]
FROM postgres:16

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234
ENV POSTGRES_DB=db_projeto_isi

COPY struct.sql /docker-entrypoint-initdb.d/

# Expondo a porta padrão do PostgreSQL
EXPOSE 5432
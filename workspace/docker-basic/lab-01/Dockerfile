FROM mysql:lts

ENV MYSQL_ROOT_PASSWORD=mysql

COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

# -----------------------------------

# docker image build -t gc-mysql:v1 .

# docker volume create mysql-data
# docker container run --name demoweb-db -d -p 3307:3306 -v mysql-data:/var/lib/mysql gc-mysql:v1

FROM mysql:lts

ENV MYSQL_ROOT_PASSWORD=mysql

COPY ./init.sql /docker-entrypoint-initdb.d/

EXPOSE 3306

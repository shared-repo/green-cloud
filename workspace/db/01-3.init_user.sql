CREATE USER green_cloud@localhost IDENTIFIED BY "mysql";
CREATE USER green_cloud@"%" IDENTIFIED BY "mysql";

GRANT ALL PRIVILEGES ON market_db.* TO green_cloud@localhost;
GRANT ALL PRIVILEGES ON market_db.* TO green_cloud@"%";
GRANT ALL PRIVILEGES ON sakila.* TO green_cloud@"%";
GRANT ALL PRIVILEGES ON world.* TO green_cloud@"%";
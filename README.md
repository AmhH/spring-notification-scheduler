
       docker run --name mysql57 -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_USER=quartz \
    -e MYSQL_PASSWORD=quartz \
    -e MYSQL_DATABASE=scheduler \
    -d mysql/mysql-server:5.7
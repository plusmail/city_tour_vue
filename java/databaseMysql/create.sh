#!/bin/bash
MYSQL_USER="root"
MYSQL_PASS="1333"
DATABASE="final_capstone"
BASEDIR=$(dirname "$0")

# Drop DB and users
mysql -u$MYSQL_USER -p$MYSQL_PASS < "$BASEDIR/dropdb.sql"

# Create DB
mysql -u$MYSQL_USER -p$MYSQL_PASS -e "CREATE DATABASE $DATABASE CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"

# Create schema, data, users
mysql -u$MYSQL_USER -p$MYSQL_PASS $DATABASE < "$BASEDIR/schema.sql"
mysql -u$MYSQL_USER -p$MYSQL_PASS $DATABASE < "$BASEDIR/data.sql"
mysql -u$MYSQL_USER -p$MYSQL_PASS < "$BASEDIR/user.sql"

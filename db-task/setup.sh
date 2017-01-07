#!/bin/bash

dbname=tasks

if [[ -n `psql -lqt | cut -d \| -f 1 | grep -w "$dbname"` ]]; then
    dropdb $dbname
fi
createdb $dbname --no-password

psql -af create.sql $dbname
psql -af loadsample.sql $dbname


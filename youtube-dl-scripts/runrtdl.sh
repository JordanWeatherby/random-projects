#!/bin/bash

LOC=${1:-''}
FILE=${2:-'rt.txt'}

youtube-dl -a $FILE -o "/mnt/RoosterTeeth/$LOC%(title)s.%(ext)s" --username 'username' --password 'password'

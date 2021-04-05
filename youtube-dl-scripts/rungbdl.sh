#!/bin/bash

LOC=${1:-''}
FILE=${2:-'gb.txt'}

SLEEP=$((30 + RANDOM % 300))

youtube-dl -a $FILE -o "/mnt/Media/GiantBomb/$LOC%(title)s.%(ext)s" --cookies gbcookies.txt --sleep-interval $SLEEP

#!/bin/bash

date

PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/home/pi
youtube-dl --download-archive downloadedvids.txt 'INSERT PLAYLIST URL HERE' -o 'tempvid/%(title)s.%(ext)s' --recode-video mp4


if cp -r tempvid/. /mnt/Media/YouTube ; then
    rm tempvid/*
else
    echo "copy failed"
fi

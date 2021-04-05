#!/bin/bash

date

PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/home/pi
youtube-dl --download-archive musicdownloaded.txt 'INSERT PLAYLIST URL HERE' -o 'Music/%(title)s.%(ext)s' -x --audio-format mp3 --audio-quality 320k


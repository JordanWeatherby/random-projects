# random-projects

Repo to hold all my random projects that aren't big enough to have their own repos

## poker-elo (2021)

Calculates elo of any number of players by simulating 1v1 match-ups between all players. Can be used for any game with well defined 1st, 2nd, 3rd etc placements.

Input is a txt of names of players separated by spaces in order from first place to last place. Each new line in the txt will be treated as a separate game session.

Uses k=32 and d=400 by default for elo calculations

## dns-updater (2021)

Updates a defined dns record in cloudflare to point to the current computer's IP address

Originally built for my friend to easily update the dns record for my subdomain to point to his server

I have done something similar in the past to run a cron job on my web server so it automatically updates the dns if it detects the server ip has changed

## pc-playtime

I store my various "hours played" statistics across various video game platforms in a SQL database. These scripts I wrote to run on a cron job in order to automatically update the PC statistics in the database

Required api keys and credentials for the scripts are retrieved from config.php

Currently only have scripts for Steam (the largest PC game marketplace) and osu! (a Japanese rhythm game)

Unfortunately a majority of game stores (Epic Games, Origin and Uplay to name a few) do not offer APIs to retrieve play time so I have to update these manually

Given the way these function they can be run at any time interval, the data will be accurate if they are run once a minute, once a day or even once a year

### steam.php

Queries the Steam API which returns a full list the name and minutes played of every game in the given user's library

The script compares these to the given database and adds any games that are missing (if they have non zero minutes played) or updates the games that are there (if their play time has changed)

### osu.php

Queries the osu! API four times to retrieve the given user's playtime for osu!, osu!taiko, osu!catch and osu!mania

These are summed together and stored in the given database

## youtube-dl-scripts (2020)

I have a few small scripts I have used to interface with [youtube-dl](https://github.com/ytdl-org/youtube-dl) to download media from a number of sites

### `runytpldl.sh`

This is hooked up to a daily cron job that detects if any new videos have been added to a unlisted 'audio download' youtube playlist and downloads them in 320kbps mp3 format.

### `runytviddlpl.sh`

Very similar to previous except this downloads videos and converts to mp4 format. After conversion the videos are transferred to a mounted drive that is connected to a plex server. Allows a seamless process from adding a video to a playlist from within youtube to watching it locally on plex.

### `runrtdl.sh`

Designed to download videos from comedy site RoosterTeeth, this accepts a txt of links to videos to download as well as the location to store them within the /RoosterTeeth/ folder on the local drive.

Requires username/password for premium content

### `rungbdl.sh`

Similar to previous but for video game site Giant Bomb.

Requires valid login cookies for premium content. Includes random delay to avoid being bot detected.

## rtlinkscraper (2020)

Chrome browser extension to extract links in plaintext from the site roosterteeth.com for use with [runrtdl.sh](#runrtdl.sh)

## scrabble-solver (2018)

Given a list of letters, this calculates the highest scoring words in the scrabble dictionary to play

## ozone-gif (2017)

In university we were given images of the ozone layer to look at on a website that were hard to compare so I wrote a script to scrape them and collate them into a gif

## hangman (2016)

Command line program to play hangman

Entering 'random' as the word to guess will allow you to play against a randomly selected word from words.txt

Command to clear console to hide entered word only works on some OS, sorry

## tictactoe (2016)

Allows you to play noughts and crosses against another human opponent on the command line (one player mode was never developed)

Moves are inputted as their coordinates

`1 1 | 1 2 | 1 3`  
`2 1 | 2 2 | 2 3`  
`3 1 | 3 2 | 3 3`

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

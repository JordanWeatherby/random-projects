$hostname = "www.domain.com"
$zoneid = "XXXXXX"
$token = "XXXXXX"
$dnsrecordid = "XXXXXX"

Write-Output "Getting IP Address"
$ip = (Invoke-WebRequest -uri "http://ifconfig.me/ip" -UseBasicParsing).Content

Write-Output "IP Address is ${ip}"

$body = ConvertTo-Json @{
    'type' = 'A'
    'name' = $hostname
    'content' = $ip
    'ttl' = 1
    }
    
Write-Output "Attempting to update $hostname in zone ID $zoneid and DNS Record ID $dnsrecordid with IP $ip"

Invoke-RestMethod https://api.cloudflare.com/client/v4/zones/$zoneID/dns_records/$dnsrecordid -Method Put -Authentication Bearer -Token (ConvertTo-SecureString $token -AsPlainText -Force) -Body $body
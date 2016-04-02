# Picture Voting Application

##### Configuration

Set ```dropbox-folder``` and ```dropbox-token``` in ```src/main/resources/config.yaml```.

##### Example Requests

###### picture event
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{"type":"inboundMedia","payload":"http://i.giphy.com/Llhp3CviKCKWc.gif","fromNumber":"+12131234567","toNumber":"+16261234567"}' \
 'http://localhost:9999/event'
```

###### vote event
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{"type":"inboundText","payload":"huge-rain-9531.jpeg","fromNumber":"+12131234567","toNumber":"+16261234567"}' \
 'http://localhost:9999/event'
```
###### report
```
curl -i -X GET \
 'http://localhost:9999/report'
```

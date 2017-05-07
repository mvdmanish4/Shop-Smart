'use strict'

const express = require('express')
const bodyParser = require('body-parser')
const request = require('request')
const app = express()
const {Wit, log} = require('node-wit');

app.set('port', (process.env.PORT || 5000))

app.use(bodyParser.urlencoded({extended: false}))

app.use(bodyParser.json())

app.get('/', function (req, res) {
	res.send('Hello world, I am a chat bot')
})

app.get('/webhook/', function (req, res) {
	if (req.query['hub.verify_token'] === 'my_voice_is_my_password_verify_me') {
		res.send(req.query['hub.challenge'])
	}
	res.send('Error, wrong token')
})

app.post('/webhook/', function (req, res) {
     let messaging_events = req.body.entry[0].messaging
    for (let i = 0; i < messaging_events.length; i++) {
      let event = req.body.entry[0].messaging[i]
      let sender = event.sender.id
      if (event.message && event.message.text) {
  	    let text = event.message.text
  	    if (text === 'Generic') {
  		    sendGenericMessage(sender)
  		    continue
  	    }
  	    /*const client = new Wit({accessToken: 'RDRRC37D34PA6ZRUAWNENFUZZEU5SOYT'});
		client.message(text, {})
		.then((data) => {
		  //console.log('Yay, got Wit.ai response: ' + JSON.stringify(data));		  
		})
		.catch(console.error); */ 
		sendTextMessage(sender, "Text received, echo: " + text.substring(0,200))    
      }
      if (event.postback) {
  	    let text = JSON.stringify(event.postback)
  	    sendTextMessage(sender, "Postback received: "+text.substring(0, 200), token)
  	    continues
      }
    }
    res.sendStatus(200)
})

const token = "EAADZA9wQndqoBAFTvgx6LtLIe1GN5D7XCI4alfw3XW9nVzyRq23ftBfmNJzL5YOxSKFd1ojFb2Dhc9ZB8CwT7pIoBUZBeXW3wiZB8KbMuaviWSzoiX6bMayDLAx9qDF8ufGoPltPpexCRfZCT4ssnCZC5a5pgnZBYxUrGDiQuGJxwZDZD"

function sendGenericMessage(sender) {
    let messageData = {
	    "attachment": {
		    "type": "template",
		    "payload": {
				"template_type": "generic",
			    "elements": [{
					"title": "First card",
				    "subtitle": "Element #1 of an hscroll",
				    "image_url": "https://www.google.co.in/search?q=shirt+for+men&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjiveLq7dfTAhWIo48KHTN0CCIQ_AUICigB&biw=1536&bih=759#imgrc=kO94vo0ByE-sHM:",
				    "buttons": [{
					    "type": "web_url",
					    "url": "https://www.google.co.in/search?q=shirt+for+men&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjiveLq7dfTAhWIo48KHTN0CCIQ_AUICigB&biw=1536&bih=759#imgdii=K_R7wIOzOJrqHM:&imgrc=kO94vo0ByE-sHM:",
					    "title": "web url"
				    }, {
					    "type": "postback",
					    "title": "Postback",
					    "payload": "Payload for first element in a generic bubble",
				    }],
			    }, {
				    "title": "Second card",
				    "subtitle": "Element #2 of an hscroll",
				    "image_url": "http://messengerdemo.parseapp.com/img/gearvr.png",
				    "buttons": [{
					    "type": "postback",
					    "title": "Postback",
					    "payload": "Payload for second element in a generic bubble",
				    }],
			    }]
		    }
	    }
    }
    request({
	    url: 'https://graph.facebook.com/v2.6/me/messages',
	    qs: {access_token:token},
	    method: 'POST',
	    json: {
		    recipient: {id:sender},
		    message: messageData,
	    }
    }, function(error, response, body) {
	    if (error) {
		    console.log('Error sending messages: ', error)
	    } else if (response.body.error) {
		    console.log('Error: ', response.body.error)
	    }
    })
}

function sendTextMessage(sender, text) {
    let messageData = { text:text }
    request({
	    url: 'https://graph.facebook.com/v2.6/me/messages',
	    qs: {access_token:token},
	    method: 'POST',
		json: {
		    recipient: {id:sender},
			message: messageData,
		}
	}, function(error, response, body) {
		if (error) {
		    console.log('Error sending messages: ', error)
		} else if (response.body.error) {
		    console.log('Error: ', response.body.error)
	    }
    })
}

app.listen(app.get('port'), function() {
	console.log('running on port', app.get('port'))
})
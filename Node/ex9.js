var http = require('http')
var bl = require('bl')

var counter = 0
var responses = []

for (var i = 2; i < 5; i++) {
	(function (url, pos) {
		http.get(url, function (response) {
			response.pipe(bl(function (err, data) {
				var str = data.toString()
				responses[pos] = str
				if (++counter == 3)
					printResponses()
			}))
		})
	})(process.argv[i], i - 2)
}
	
function printResponses() {
	for (var i = 0; i < responses.length; i++) {
		console.log(responses[i])
	}
}
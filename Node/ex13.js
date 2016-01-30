var http = require('http')
var url = require('url')
var port = process.argv[2]

var server = http.createServer(function (req, res) {
	if (req.method != 'GET') {
		return error(res, "only GET is supported")
	}
	var parsed = url.parse(req.url)
	if (parsed.pathname == '/api/parsetime') {
		var date = extractDate(parsed.query)
		if (date == null) {
			return error(res, "invalid query")
		}
		return parsetime(res, date)
	} else if (parsed.pathname == '/api/unixtime') {
		var date = extractDate(parsed.query)
		if (date == null) {
			return error(res, "invalid query")
		}
		return unixtime(res, date)
	} else {
		return error(res, "unknown endpoint")
	}
	
	
	
})
server.listen(port)

function extractDate(query) {
	var tokens = query.split('=')
	if (tokens.length != 2) 
		return null
		
	if (tokens[0] != 'iso')
		return null

	var date = new Date(tokens[1])	
	if (isNaN(date)) {
		return null
	}

	return date
}

function unixtime(res, date) {
	res.writeHead(200, { 'Content-Type': 'application/json' })
	var ms = date.getTime()
	return res.end(JSON.stringify({
		"unixtime" : ms
	}))
}

function parsetime(res, date) {
	res.writeHead(200, { 'Content-Type': 'application/json' })
	var h = date.getHours()
	var m = date.getMinutes()
	var s = date.getSeconds()
	return res.end(JSON.stringify({
		"hour" : h,
		"minute" : m,
	    "second" : s
	}))
}

function error(res, msg) {
	return res.end(msg)
}
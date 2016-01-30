var net = require('net')
var port = process.argv[2]

var server = net.createServer(function (socket) {
	var date = new Date()
	var str = date.getFullYear().toString() + '-'
	str += num2str(date.getMonth() + 1) + '-'
	str += num2str(date.getDate()) + ' '
	str += num2str(date.getHours()) + ':'
	str += num2str(date.getMinutes()) + '\n'
	socket.end(str)
})
server.listen(port)

function num2str(number) {
	return pad(number.toString(), 2)
}

function pad(str, len) {
	if (str.length >= len){
		 return str
	}
	var zeros = len - str.length
	var res = str
	for (var i = 0; i < zeros; i++)
		res = '0' + res
	return res
}

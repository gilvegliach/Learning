var filter = require('./filterModule.js')
filter(process.argv[2], process.argv[3], function (err, data) {
	data.forEach(function(elem, index, arr) {
		console.log(elem)
	})
})
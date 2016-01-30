var fs = require('fs')
var buf = fs.readFileSync(process.argv[2])
var str = buf.toString()
var count = str.split('\n').length - 1
console.log(count)
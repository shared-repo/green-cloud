console.log("Hello, Javascript Programming World !!!!");

var http = require('http');

http.createServer( function(req, resp) {
	resp.writeHead(200, { 'Content-Type' : 'text/html' });
	resp.write("<h1>Hello, Web and Node.js</h1>");
	resp.end();
}).listen(8080);
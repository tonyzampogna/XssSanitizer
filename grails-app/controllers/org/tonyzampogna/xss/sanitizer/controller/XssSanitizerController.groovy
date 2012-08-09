package org.tonyzampogna.xss.sanitizer.controller

class XssSanitizerController {
	def index() {
		render(view: "index")
	}

	def test1() {
		redirect(uri: "/?value=<script>alert(101);</script>")
	}

	def test2() {
		redirect(uri: "/?value=<img%20src%3D%26%23x6a;%26%23x61;%26%23x76;%26%23x61;%26%23x73;%26%23x63;%26%23x72;%26%23x69;%26%23x70;%26%23x74;%26%23x3a;alert%26%23x28;34681%26%23x29;>")
	}

	def test3() {
		redirect(uri: "/?value=<script>alert('test')</script>")
	}

	def test4() {
		redirect(uri: "/?value=%27%22%3E%3Ciframe+src%3Dhttp%3A%2F%2Fdemo.testfire.net%2Fphishing.html%3E")
	}
}

/*
 * 倒计时使用函数，sec为倒计时市场，fn1为停止调用的回调函数，fn2是在倒计时中需要处理的回调函数
 */
function Timer(sec, fn1, fn2) {
	this.sec = sec;
	this.fn1 = fn1;
	this.fn2 = fn2;
}
Timer.prototype.doTime = function(tt, b, fn1, fn2) {
	var secTmp = tt;
	if (b) {
		secTmp = this.sec;
	}
	var $this = this;
	if (secTmp == 0) {
		this.fn1(secTmp);
		secTmp = this.sec;
	} else {
		this.fn2(secTmp);
		secTmp--;
		var x = setTimeout(function() {
			$this.doTime(secTmp, false, $this.fn1, $this.fn2);
		}, 1000);
		return x;
	}
}
/*
 * use eg: var t = new Timer(5, function() { alert("done") }, function() {
 * console.log("s") }) t.doTime(1, true);
 */
function dyncTimer(sec, fn1, fn2) {
	var t = new Timer(sec, fn1, fn2);
	t.doTime(1, true);
}
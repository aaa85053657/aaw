function GetClock() {
	var d = new Date();
	var nmonth = d.getMonth(), ndate = d.getDate(), nyear = d.getYear();
	if (nyear < 1000)
		nyear += 1900;
	var nhour = d.getHours(), nmin = d.getMinutes(), nsec = d.getSeconds();
	if (nmin <= 9)
		nmin = "0" + nmin
	if (nsec <= 9)
		nsec = "0" + nsec;

	document.getElementById('clockbox').innerHTML = nyear + "/" + (nmonth + 1)
			+ "/" + ndate + " " + nhour + ":" + nmin + ":" + nsec + "";
}

window.onload = function() {
	GetClock();
	setInterval(GetClock, 1000);
}
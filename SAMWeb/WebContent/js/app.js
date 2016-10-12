if ($('nav').length) {
	checkMenuNonAjax();
}

function pageSetUp() {

	loadDataTable();
	loadCheckBox();
	loadDatePicker();
	loadSelect2();
}

function loadCheckBox() {
	if ($('.i-checks')) {
		$('.i-checks').iCheck({
			checkboxClass : 'icheckbox_square-green',
			radioClass : 'iradio_square-green',
		});
	}

}

function checkMenuNonAjax() {
	$('nav li.active').removeClass("active");
	// match the url and add the active class
	var url = window.location.pathname;

	$('nav li:has(a[href="' + url + '"])').addClass("active");

	var parent = $('nav li:has(a[href="' + url + '"])');
	// active open
	var contParents = 1;
	while (contParents < 6) {
		parent = parent.parent();
		if (parent.is('li')) {
			parent.addClass("active in");
			break;
		}
		contParents++;
	}

	// $('nav a[href="' + url + '"]').click(function(e){ return false;});
	var title = ($('nav a[href="' + url + '"]').attr('title'));

	// change page title from global var
	document.title = 'SAM | ' + (title || document.title);
}

function loadDataTable() {
	if ($('.tableTools')) {
		$('.tableTools').DataTable({
			pageLength : 10,
			responsive : true,

		});
	}

}

function loadDatePicker() {

	if ($('.datepicker')) {
		$('.datepicker').datepicker({
			todayBtn : "linked",
			language : 'es',
			keyboardNavigation : false,
			forceParse : false,
			calendarWeeks : true,
			autoclose : true,
			format : "dd/mm/yyyy"
		});
	}
}

function loadSelect2() {
	if ($('.select2')) {
		$('.select2').select2();
	}
}
function showModal(modalName) {
	$(modalName).modal('toggle');
}

function hideModal(modalName) {
	$(modalName).modal('hide');
}

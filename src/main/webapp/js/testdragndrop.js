$(document).ready(function() {
	var isAdvancedUpload = function() {
	var div = document.createElement('div');
	return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
	}();

	var $form = $('.box');

	if (isAdvancedUpload) {
		$form.addClass('has-advanced-upload');
		
		var droppedFiles = false;
		
		var $input = $form.find('input[type="file"]'),
			$label = $form.find('label'),
			showFiles = function(files) {
			$label.text(files.length > 1 ? ($input.attr('data-multiple-caption') || '').replace('{count}', files.length) : files[0].name);
			
		};
		
		$form.on('drag dragstart dragend dragover dragenter dragleave dragleave drop', function(e) {
			e.preventDefault();
			e.stopPropagation();
		})
				.on('dragover dragenter', function() {
					$form.addClass('is-dragover');
		})
				.on('dragleave dragend drop', function() {
					$form.removeClass('is-dragover');
		})
				.on('drop', function(e) {
					droppedFiles = e.originalEvent.dataTransfer.files;
					showFiles(droppedFiles);
					
					if($form.hasClass('is-uploading')) return false;
					
					$form.addClass('is-uploading').removeClass('is-error');
					
					if (isAdvancedUpload) {
						e.preventDefault();
						
						var ajaxData = new FormData($form.get(0));
						
						if (droppedFiles) {
							$.each(droppedFiles, function(i, file) {
								ajaxData.append($input.attr('name'), file);
							})
						}
						
						$.ajax({
							url: "testuploadfile",
							type: $form.attr('method'),
							data: ajaxData,
							dataType: 'json',
							cache: false,
							contentType: false,
							processData: false,
							complete: function() {
								$form.removeClass('is-uploading');
							},
							success: function(data) {
								$form.addClass(data.success == "true" ? 'is-success' : 'is-error');
								if (!data.success) 
									alert("ERROR: " + data.error);
								else
									alert("Upload file: " + data.filename);
								
						
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) {
								alert("Connection failed!");
								alert(errorThrown);
							}
						});
					} else {
						var iframeName  = 'uploadiframe' + new Date().getTime();
				    	$iframe   = $('<iframe name="' + iframeName + '" style="display: none;"></iframe>');

				    	$('body').append($iframe);
				    	$form.attr('target', iframeName);

				    	$iframe.one('load', function() {
				    		var data = JSON.parse($iframe.contents().find('body' ).text());
					    	$form
					    		.removeClass('is-uploading')
					    		.addClass(data.success == true ? 'is-success' : 'is-error')
					    		.removeAttr('target');
						    if (!data.success) $errorMsg.text(data.error);
					    	$form.removeAttr('target');
					    	$iframe.remove();
				    	});
					}
		});
		$input.on('change', function(e) {
			showFiles(e.target.files);
		});
	}
});

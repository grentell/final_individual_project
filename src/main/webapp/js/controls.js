$(document).ready(function() {
	/*$(".workbookcontrol").draggable({
		containment: "#workbook_main",
		scroll: false
	});*/
	var workbook = "myWorkbook";
	var worksheet = "helloworksheet";
	var editmode = false;
	var timeout = false;
	var timeoutDrops = false;
	
	$("#worksheet_current").attr("value", "helloworksheet");
	$(".worksheets_edit").on("click", function(e, ui) {
		if($(this).hasClass("edit_mode")) {
			$(this).removeClass("edit_mode");
			editmode = false;
			disableDelete();
		} else {
			$(this).addClass("edit_mode");
			editmode = true;
			enableDelete();
		}
		allowDrag(true);
	});
	function iterateDoubleclicks() {
		$(".filecontrol").dblclick(function() {
			if(!timeout) {
				timeout = true;
				
				// What to do when the user doubleclicks on a file.
				var filename = $(this).find(".workbookcontrol_filename").text();
				// alert("Doubleclicked a file! " + filename);
				window.location="downloadfile?workbook=" + workbook + "&worksheet=" + worksheet + "&filename=" + filename;
				
				// Then timeout since we don't want to perform this action twice.
				window.setTimeout(function() {
					timeout = false;
				}, 1000);
			}
		});
		setDroppableOnFile();
		
	}
	function enableDelete() {
		disableDelete();
		//checkbox.appendTo(".workbookcontrol");
		$(".workbookcontrol_included").each(function(i,e) {
			var checkbox = $("<input type='checkbox' class='workbookcontrol_checkbox'/>");
			console.log("Added checkbox");
			checkbox.appendTo(e);
			checkbox.change(function() {
				var filename = $(this).parent().attr("objectid");
				//alert("Filename to delete is: " + filename);
				$.ajax({url: "deletefile?workbook=" + workbook + "&worksheet=" + worksheet + "&filename=" + filename, dataType: "json", success: function (result) {
					//$(this).parent().remove();
					$(".workbook_panel_fullheight").html("");
					loadWorkbook(workbook, worksheet);
				}});
				window.setTimeout(function() {
					$(".workbook_panel_fullheight").html("");
					loadWorkbook(workbook, worksheet);
				}, 1000)
			});
		});
		
		
	}
	function disableDelete() {
		$(".workbookcontrol_checkbox").each(function(i,e) {
			e.remove();
		});
	}
	function allowDrag(drag) {
		$(".workbookcontrol").each(function(i, e) {
			if(!$(e).data('draggable')) {
				$(e).draggable({
					containment: ".workbook_main",
					cursor: "move",
					zIndex: 350,
					helper: 'clone',
					drag: function(event, ui) {
						//ui.detach();
						ui.helper.detach().appendTo(".workbook_main");
						ui.helper.css("margin-left", "20px");
						ui.helper.css("margin-top", "20px");
					}
				});
			}
		});
		$(".workbookcontrol_included").each(function(i, e) {
			if(!$(e).data('draggable')) {
				$(e).draggable({
					containment: ".workbook_main",
					cursor: "move",
					zIndex: 350,
					helper: 'clone',
					drag: function(event, ui) {
						//ui.detach();
						ui.helper.detach().appendTo(".workbook_main");
						ui.helper.css("margin-left", "20px");
						ui.helper.css("margin-top", "20px");
					}
				});
			}
		});
		if(editmode && drag) {
			
				$(".workbookcontrol").draggable('enable');
				$(".workbookcontrol_included").draggable('enable');
			
		} else {
			
				$(".workbookcontrol").draggable('disable')
				$(".workbookcontrol_included").draggable('disable');
				
				$(".filecontrol").draggable({revert: "invalid"});
				
				$(".filecontrol").draggable('enable');
		}
	}
	
	$.getJSON( "getworksheets?workbook=" + workbook, function(result) {
		console.log("Worksheet list: " + result);
		$("#worksheets_tabs ul").html("");
		$.each(result.worksheets, function(i, item) {
			//$("<div class='worksheet_item'>" + item + "</div>").appendTo(".worksheets_list");
			var tab = $("<li class=\"worksheet_tab\"><a href=\"#\">" + item + "</a></li>");
			tab.appendTo("#worksheets_tabs ul");
			tab.on("click", function() {
				$(".worksheet_tab a").removeClass("worksheet_selected");
				$(this).find("a").addClass("worksheet_selected");
				
				$(".workbook_panel_fullheight").html("");
				worksheet = $(this).find("a").text();
				$("#worksheet_current").attr("value", worksheet);
				loadWorkbook(workbook, worksheet);
			})
		});
		
		
	});
	
	$(".workbook_panel_fullheight").on("dragover dragenter", function(e, ui) {
		if(ui == undefined) {
			e.preventDefault();
			e.stopPropagation();
		}
		
	});
	$(".workbook_panel_fullheight").on("drop", function(e, ui) {
		if(ui == undefined) {
			e.preventDefault();
			e.stopPropagation();
			if(editmode) {
				// First check if a file with the same name exists already.
				var filename = e.originalEvent.dataTransfer.files[0].name;
				var exists = false;
				$(".filecontrol").find(".workbookcontrol_filename").each(function(i,e) {
					if($(this).text() == filename) exists = true;
				});
				if(exists) {
					alert("File with the same name already exists!");
					return;
				}
				// Get mouse position relative to drop target: 
			    var dropPositionX = event.pageX - $(this).offset().left;
			    var dropPositionY = event.pageY - $(this).offset().top;
			    // Get mouse offset relative to dragged item:
			    var dragItemOffsetX = event.offsetX;
			    var dragItemOffsetY = event.offsetY;
			    // Get position of dragged item relative to drop target:
			    var dragItemPositionX = dropPositionX-dragItemOffsetX;
			    var dragItemPositionY = dropPositionY-dragItemOffsetY;
			    
				//console.log("The position is: " + dropPositionX + "x" + dropPositionY + " : " + dragItemOffsetX + "x" + dragItemOffsetY + " : " + dragItemPositionX + "x" + dragItemPositionY);
			    var component = $("<div/>");
			    component.addClass("workbookcontrol_included");
			    component.addClass("draggable");
			    component.addClass("filecontrol");
			    component.appendTo(".workbook_panel_fullheight");
			    
			    var scrollTop = $(".workbook_panel").scrollTop();
			    var scrollLeft = $(".workbook_panel").scrollLeft();
			    
			    var iconTop, iconLeft;
			    
			    //iconTop = scrollTop - 105 + dropPositionY;
			    //iconLeft = scrollLeft - 105 + dropPositionX;
			    iconTop = dropPositionY - 105;
			    iconLeft = dropPositionX - 105;
			    
			    component.css("left", iconLeft + "px");
			    component.css("top", iconTop + "px");
			    component.addClass("addedcontrol");
				$(component).draggable({
					containment: ".workbook_main",
					cursor: "move",
					zIndex: 350
					
				});
				
				
				component.addClass("positioned");
				
				worksheet = $("#worksheet_current").attr("value");
				
				// TODO: Enter code here to extract a filename from the dropped file, then extract
				// the contents of the file, then upload to server.
				
				
				var filename = uploadfile(e);
				var filename = recordObject(workbook, worksheet, component, filename);
				
				
				
				var controlFilename = $('<div class="workbookcontrol_filename"/>');
				controlFilename.text(filename);
				controlFilename.appendTo(component);
			}
		}
		iterateDoubleclicks();
		if(editmode) {
			enableDelete();
		}
	});
	
	var setDroppableOnFile = function() {
		$(".filecontrol").on("drop", function(event, ui) {
			if(ui == undefined) {
				if(!timeoutDrops) {
					timeoutDrops = true;
					event.preventDefault();
					event.stopPropagation();
					
					var filename = $(this).find(".workbookcontrol_filename").text();
					
					uploadfile(event, filename);
					
					window.setTimeout(function() {
						timeoutDrops = false;
					}, 1000);
				}
			}
		});
	}
	
	$(".workbook_panel_fullheight").droppable({
		drop: function(event, ui) {
			if(editmode) {
				$.ui.ddmanager.current.cancelHelperRemoval = true;
				ui.helper.detach().appendTo(".workbook_panel_fullheight");
				
				var scrollTop = $(".workbook_panel").scrollTop();
				var scrollLeft = $(".workbook_panel").scrollLeft();
				
				var iconTop, iconLeft;
				
				if(ui.helper.hasClass("addedcontrol")) {
					iconTop = parseInt(ui.helper.css("top"));
					iconLeft = parseInt(ui.helper.css("left"));
				} else {
					iconTop = scrollTop - 105 + parseInt(ui.helper.css("top"));
					iconLeft = scrollLeft - 105 + parseInt(ui.helper.css("left"));
					
				}
				
				/*if(ui.helper.hasClass("positioned")) {
					iconLeft = 0;
					iconTop = 0;
				}*/
				ui.helper.css("left", iconLeft + "px");
				ui.helper.css("top", iconTop + "px");
				ui.helper.addClass("workbookcontrol_included");
				ui.helper.addClass("addedcontrol");
				$(ui.helper).draggable({
					containment: ".workbook_main",
					cursor: "move",
					zIndex: 350
					
				});
				
				if(!ui.helper.hasClass("positioned")) 
					ui.helper.addClass("positioned");
				
				worksheet = $("#worksheet_current").attr("value");
				if(ui.helper.hasClass("filecontrol")) {
					var filename = recordObject(workbook, worksheet, ui.helper);
					var controlFilename = $('<div class="workbookcontrol_filename"/>');
					controlFilename.text(filename);
					controlFilename.appendTo(ui.helper);
				} else {
					recordObject(workbook, worksheet, ui.helper);
				}
			}
			iterateDoubleclicks();
			if(editmode) {
				enableDelete();
			}
		},
		accept: function(dropElement) {
			return editmode;
		}
	});
	
	function loadWorkbook(workbook, worksheet) {
		$.getJSON( "index?workbook=" + workbook + "&worksheet=" + worksheet, function(result) {
			console.log(result);
			$(".workbook_panel_fullheight").html("");
			$.each(result.worksheets, function(i, item) {
				console.log(item.title);
				
					$.each(item.objects, function(o, object) {
						if(object.objecttype == "file") {
							var newWorkbookControl = $('<div class="workbookcontrol_included filecontrol"/>');
							newWorkbookControl.appendTo(".workbook_panel_fullheight");
							newWorkbookControl.css("left", object.posx + "");
							newWorkbookControl.css("top", object.posy + "");
							
							newWorkbookControl.addClass("workbookcontrol");
							newWorkbookControl.addClass("addedcontrol");
							$(newWorkbookControl).draggable({
								containment: ".workbook_main",
								cursor: "move",
								zIndex: 350

							});
							newWorkbookControl.attr("objectid", object.objectid);
							
							var controlFilename = $('<div class="workbookcontrol_filename"/>');
							controlFilename.text(object.filename);
							controlFilename.appendTo(newWorkbookControl);
						}
						if(object.objecttype == "icon") {
							var objectDesc = object.objectdesc;
							var newWorkbookControl = $('<div class="workbookcontrol_included ' + objectDesc + 'control"/>');
							newWorkbookControl.appendTo(".workbook_panel_fullheight");
							newWorkbookControl.css("left", object.posx + "");
							newWorkbookControl.css("top", object.posy + "");
							
							newWorkbookControl.addClass("workbookcontrol");
							newWorkbookControl.addClass("addedcontrol");
							$(newWorkbookControl).draggable({
								containment: ".workbook_main",
								cursor: "move",
								zIndex: 350
							});
							newWorkbookControl.attr("objectid", object.objectid);
						}
					});
				
			});
			allowDrag(true);
			iterateDoubleclicks();
			if(editmode) {
				enableDelete();
			}
		});
		
	}
	
	function recordObject(workbook, worksheet, obj, filename) {
		objectid = "";
		if(obj.attr("objectid") === undefined) {
			// Change this to actual filename
			if(!filename)
				objectid = "file_" + Math.floor(Math.random() * 1000000 + 1) + ".txt";
			else
				objectid = filename;
			
			obj.attr("objectid", objectid);
			if(obj.hasClass("filecontrol")) {
				$.ajax({url: "createFile?workbook=" + workbook + "&worksheet=" + worksheet, dataType: "json", data: {objectid: obj.attr("objectid"), objecttype: "file", posX: "" + (parseInt(obj.css("left")) + 10)+ "px", posY: "" + (parseInt(obj.css("top")) + 10) + "px"}, success(result) {
					alert(result);
				}});
			} else {
				var controlType = "";
				if(obj.hasClass("leftarrowcontrol"))
					controlType = "leftarrow";
				if(obj.hasClass("rightarrowcontrol"))
					controlType = "rightarrow";
				if(obj.hasClass("uparrowcontrol"))
					controlType = "uparrow";
				if(obj.hasClass("downarrowcontrol"))
					controlType = "downarrow";
				
				$.ajax({url: "createIcon?workbook=" + workbook + "&worksheet=" + worksheet, dataType: "json", data: {objectid: obj.attr("objectid"), objecttype: "icon", objectdesc: controlType, posX: "" + (parseInt(obj.css("left")) + 10)+ "px", posY: "" + (parseInt(obj.css("top")) + 10) + "px"}, success(result) {
					alert(result);
				}});
				
			}
			
			
		} else {
			$.ajax( {url: "objectdata?workbook=" + workbook + "&worksheet=" + worksheet, dataType: "json", data: {objectid: obj.attr("objectid"), param: "posX", value: "" + obj.css("left")}, success(result) {

				$.ajax( {url: "objectdata?workbook=" + workbook + "&worksheet=" + worksheet, dataType: "json", data: {objectid: obj.attr("objectid"), param: "posY", value: "" + obj.css("top")}, success(result) {
						
				}});
			}});
		}
		return objectid
	}
	
	loadWorkbook(workbook, worksheet);
	allowDrag(true);
	disableDelete();
	
	// Below code is for uploading files from drag and drop.
	var isAdvancedUpload = function() {
	var div = document.createElement('div');
	return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
	}();

	var $form = $('.downloadbox');

	if (isAdvancedUpload) {
		$form.addClass('has-advanced-upload');
		
		var droppedFiles = false;
		
		var $input = $form.find('input[type="file"]'),
			$label = $form.find('label'),
			showFiles = function(files) {
			$label.text(files.length > 1 ? ($input.attr('data-multiple-caption') || '').replace('{count}', files.length) : files[0].name);
		}
		};
		
		var uploadfile = function(e, filename) {
					droppedFiles = e.originalEvent.dataTransfer.files;
					showFiles(droppedFiles);
					
					//if($form.hasClass('is-uploading')) return false;
					
					//$form.addClass('is-uploading').removeClass('is-error');
					
					if (isAdvancedUpload) {
						e.preventDefault();
						
						var ajaxData = new FormData($form.get(0));
						var filename2 = "";
						if (droppedFiles) {
							$.each(droppedFiles, function(i, file) {
								ajaxData.append($input.attr('name'), file);
								filename2 = file.name;
							});
						}
						
						if(!filename)
							filename = filename2;
						
						$.ajax({
							url: "uploadfile?workbook=" + workbook + "&worksheet=" + worksheet + "&filename=" + filename,							type: $form.attr('method'),
							data: ajaxData,
							dataType: 'json',
							cache: false,
							contentType: false,
							processData: false,
							complete: function() {
								$form.removeClass('is-uploading');
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
					return droppedFiles[0].name;
		}
		$input.on('change', function(e) {
			showFiles(e.target.files);
		});
	
});
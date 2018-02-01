/* drag and drop uploadfiles */
var rowCount=0;
var fd = new FormData();

function closeModal(a)
{
	$('.'+a).modal('hide');
}

function clearDiv(a)
{
	$(a).html('');
}

function masfiles()
{
	var obj = $(".dragandrophandler");
     $(this).css('border', '2px dotted');
     var files =  document.getElementById('fileqwer[]').files;
     document.getElementById('fileqwer[]').values="";
     handleFileUpload(files,obj);
}

function masfiles1()
{
	var obj = $(".dragandrophandler");
     $(this).css('border', '2px dotted');
     var files =  document.getElementById('fileqwer1[]').files;
     document.getElementById('fileqwer1[]').values="";
     handleFileUpload(files,obj);
}

var allfilestemp = [];

function quitar(b)
{
	delete allfilestemp[b];
	$('.status'+b).addClass('hidden');
}

function handleFileUpload(files,obj)
{
   for (var i = 0; i < files.length; i++) 
   {
	   if( files[i].type == 'image/jpeg' || files[i].type == 'image/png' || files[i].type == 'image/bmp' || files[i].type == 'image/tiff'  )
	   {
		   allfilestemp[rowCount] = files[i];
		   var nombretemp = "";
		   console.log(files[i].name.length);
		   if(files[i].name.length > 26){ nombretemp = files[i].name.substring(1,23)+'...';}
		   else{ nombretemp = files[i].name; }
		   $('#statusId').append('<div class="col-xs-12 col-sm-6 status'+rowCount+'" id="">'+
				   '<b title="'+files[i].name+'"><span class=" fa fa-minus-square click" onclick="quitar('+rowCount+');" aria-hidden="true"></span>&nbsp;'+nombretemp+'</b></div>');
		   $('.statusId').append('<div class="col-xs-12 col-sm-6 status'+rowCount+'" id="">'+
				   '<b title="'+files[i].name+'"><span class=" fa fa-minus-square click" onclick="quitar('+rowCount+');" aria-hidden="true"></span>&nbsp;'+nombretemp+'</b></div>');
		   rowCount++;
	   }
   }
}


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}



$(document).ready(function()
{
var obj = $(".dragandrophandler");
obj.on('dragenter', function (e) 
{
    e.stopPropagation();
    e.preventDefault();
    $(this).css('border', '2px solid ');
});
obj.on('dragover', function (e) 
{
     e.stopPropagation();
     e.preventDefault();
});
obj.on('drop', function (e) 
{
 
     $(this).css('border', '2px dotted ');
     e.preventDefault();
     var files = e.originalEvent.dataTransfer.files;
 
     //We need to send dropped files to Server
     handleFileUpload(files,obj);
});
$(document).on('dragenter', function (e) 
{
    e.stopPropagation();
    e.preventDefault();
});
$(document).on('dragover', function (e) 
{
  e.stopPropagation();
  e.preventDefault();
  obj.css('border', '2px dotted ');
});
$(document).on('drop', function (e) 
{
    e.stopPropagation();
    e.preventDefault();
});
 
});

/**/
/*++Notificaciones++*/
var tallasperronas = {};
var auxauxaux = [];
var mensajesglobal = 0;
function msjerror(error)
	{
	  	mensajesglobal++;
	  	$("#mensajes").append('<div id="mensaje'+mensajesglobal+'" class="alert alert2 alert-danger text-center"><b>'+error+'</b></div>');
	  	setTimeout(function() {
	  	       eliminaThis();
	  	    },4000);
	}
	
function eliminaThis()
	{
	  	$('#mensajes').find('div:first').removeClass("alert");
	  	setTimeout(function() {
	  		$('#mensajes').find('div:first').remove();
	  	    },1000);
	}
	
function msjexito(exito)
	{
	  	mensajesglobal++;
	  	$("#mensajes").append('<div id="mensaje'+mensajesglobal+'" class="alert alert2 alert-success text-center"><b>'+exito+'</b></div>');
	  	setTimeout(function() {
	  	       eliminaThis();
	  	    },4000);
	}
		

/*++++*/


function subirarchivo(d, id)
{
	var token = $('#csrf').val() , name = $('#csrf').attr('name');
	console.log(name);
  $.ajax({
      url: 'http://localhost:8080/ags-admin/rest/product_detail/'+id,
      data: d ,
      processData: false,
      contentType: undefined,
      type: 'POST',
      headers: {
          "X-XSRF-TOKEN": token,
          'Content-Type': 'multipart/form-data'
      },
      success: function ( data ) {
          alert( data );
      }
  });
}

var limitetalla = 1;
function addproductosku()
{
	if(parseInt(limitetalla) < 5)
	{
	}
	else
	{
		msjerror('Solo '+tallasperronas.length+' tallas');
	}
}


/*poner y quitar clases*/
function addremoveclass(clase,target)
	{
		if($("."+target).hasClass(clase))
		{
			$("."+target).removeClass(clase)
		}
		else
		{
			$("."+target).addClass(clase)
		}
		//console.log($("."+target));
	}
/**/

function muestra(ob)
{
	if($("."+ob).hasClass(hidden))
		{
			$("."+ob).removeClass(hidden);
		}
	else
		{
			$("."+ob).addClass(hidden);
		}
}

var relaseDatemod, relaseDatemod1;

$(document).ready(function () {
	
	setCookie('lang', 'en', 360);
	
	
    $( ".datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });

    $(".relaseDatemod").change(function() {
    	relaseDatemod= $(this).val();
        console.log(relaseDatemod);
    });
    $(".relaseDatemod1").change(function() {
    	relaseDatemod1= $(this).val();
        console.log(relaseDatemod1);
    });
    
	  $("body").popover({ selector: '[data-toggle="popover"]' });
	  $("body").tooltip({ selector: '[data-toggle=tooltip]' });
	
	$(".js-example-basic-single").select2();
	
		// Graph Data ##############################################
		var graphData = [{
				// Returning Visits
				data: [ [4, 4500], [5,3500], [6, 6550], [7, 7600],[8, 4500], [9,3500], [10, 6550], ],
				color: '#FFCA28',
				points: { radius: 7, fillColor: '#fff' }
			}
		];
	
		// Lines Graph #############################################
		try {

			$.plot($('#graph-lines'), graphData, {
				series: {
					points: {
						show: true,
						radius: 1
					},
					lines: {
						show: true
					},
					shadowSize: 0
				},
				grid: {
					color: '#fff',
					borderColor: 'transparent',
					borderWidth: 10,
					hoverable: true
				},
				xaxis: {
					tickColor: 'transparent',
					tickDecimals: false
				},
				yaxis: {
					tickSize: 1200
				}
			});
		
			// Graph Toggle ############################################
			$('#graph-bars').hide();
			

			
			$('#lines').on('click', function (e) {
				$('#bars').removeClass('active');
				$('#graph-bars').fadeOut();
				$(this).addClass('active');
				$('#graph-lines').fadeIn();
				e.preventDefault();
			});
		
			$('#bars').on('click', function (e) {
				$('#lines').removeClass('active');
				$('#graph-lines').fadeOut();
				$(this).addClass('active');
				$('#graph-bars').fadeIn().removeClass('hidden');
				e.preventDefault();
			});

			new Chart(document.getElementById("line").getContext("2d")).Line(lineChartData);
			

			new Chart(document.getElementById("bar").getContext("2d")).Bar(barChartData);
				
			new WOW().init();
			
			new UISearch( document.getElementById( 'sb-search' ) );
			
			
		} catch (e) {
			// TODO: handle exception
		}

		var lineChartData = {
			labels : ["Mon","Tue","Wed","Thu","Fri","Sat","Mon"],
			datasets : [
				{
					fillColor : "#fff",
					strokeColor : "#F44336",
					pointColor : "#fbfbfb",
					pointStrokeColor : "#F44336",
					data : [20,35,45,30,10,65,40]
				}
			]
			
		};
		

		var barChartData = {
			labels : ["Mon","Tue","Wed","Thu","Fri","Sat","Mon","Tue","Wed","Thu"],
			datasets : [
				{
					fillColor : "#8BC34A",
					strokeColor : "#8BC34A",
					data : [25,40,50,65,55,30,20,10,6,4]
				},
				{
					fillColor : "#8BC34A",
					strokeColor : "#8BC34A",
					data : [30,45,55,70,40,25,15,8,5,2]
				}
			]
			
		};
		
		
	
		"use strict";

	    // custom scrollbar

	    $("html").niceScroll({styler:"fb",cursorcolor:"#27cce4", cursorwidth: '5', cursorborderradius: '10px', background: '#424f63', spacebarenabled:false, cursorborder: '0',  zindex: '1000'});

	    $(".left-side").niceScroll({styler:"fb",cursorcolor:"#27cce4", cursorwidth: '3', cursorborderradius: '10px', background: '#424f63', spacebarenabled:false, cursorborder: '0'});


	    $(".left-side").getNiceScroll();
	    if ($('body').hasClass('left-side-collapsed')) {
	        $(".left-side").getNiceScroll().hide();
	    }



	    // Toggle Left Menu
	   jQuery('.menu-list > a').click(function() {
	      
	      var parent = jQuery(this).parent();
	      var sub = parent.find('> ul');
	      
	      if(!jQuery('body').hasClass('left-side-collapsed')) {
	         if(sub.is(':visible')) {
	            sub.slideUp(200, function(){
	               parent.removeClass('nav-active');
	               jQuery('.main-content').css({height: ''});
	               mainContentHeightAdjust();
	            });
	         } else {
	            visibleSubMenuClose();
	            parent.addClass('nav-active');
	            sub.slideDown(200, function(){
	                mainContentHeightAdjust();
	            });
	         }
	      }
	      return false;
	   });

	   
	   function visibleSubMenuClose() {
	      jQuery('.menu-list').each(function() {
	         var t = jQuery(this);
	         if(t.hasClass('nav-active')) {
	            t.find('> ul').slideUp(200, function(){
	               t.removeClass('nav-active');
	            });
	         }
	      });
	   }

	   function mainContentHeightAdjust() {
	      // Adjust main content height
	      var docHeight = jQuery(document).height();
	      if(docHeight > jQuery('.main-content').height())
	         jQuery('.main-content').height(docHeight);
	   }

	   //  class add mouse hover
	   jQuery('.custom-nav > li').hover(function(){
	      jQuery(this).addClass('nav-hover');
	   }, function(){
	      jQuery(this).removeClass('nav-hover');
	   });


	   // Menu Toggle
	   jQuery('.toggle-btn').click(function(){
	       $(".left-side").getNiceScroll().hide();
	       
	       if ($('body').hasClass('left-side-collapsed')) {
	           $(".left-side").getNiceScroll().hide();
	       }
	      var body = jQuery('body');
	      var bodyposition = body.css('position');

	      if(bodyposition != 'relative') {

	         if(!body.hasClass('left-side-collapsed')) {
	            body.addClass('left-side-collapsed');
	            jQuery('.custom-nav ul').attr('style','');

	            jQuery(this).addClass('menu-collapsed');

	         } else {
	            body.removeClass('left-side-collapsed chat-view');
	            jQuery('.custom-nav li.active ul').css({display: 'block'});

	            jQuery(this).removeClass('menu-collapsed');

	         }
	      } else {

	         if(body.hasClass('left-side-show'))
	            body.removeClass('left-side-show');
	         else
	            body.addClass('left-side-show');

	         mainContentHeightAdjust();
	      }

	   });
	   

	   searchform_reposition();

	   jQuery(window).resize(function(){

	      if(jQuery('body').css('position') == 'relative') {

	         jQuery('body').removeClass('left-side-collapsed');

	      } else {

	         jQuery('body').css({left: '', marginRight: ''});
	      }

	      searchform_reposition();

	   });

	   function searchform_reposition() {
	      if(jQuery('.searchform').css('position') == 'relative') {
	         jQuery('.searchform').insertBefore('.left-side-inner .logged-user');
	      } else {
	         jQuery('.searchform').insertBefore('.menu-right');
	      }
	   }
		
	});
	

          // Dropdowns Script
			/*
			$(document).ready(function() {
			  $(document).on('click', function(ev) {
			    ev.stopImmediatePropagation();
			    $(".dropdown-toggle").dropdown("active");
			  });
			});
			*/
		
	     
	  /************** Search ****************/
			$(function() {
		    var button = $('#loginButton');
		    var box = $('#loginBox');
		    var form = $('#loginForm');
		    button.removeAttr('href');
		    button.mouseup(function(login) {
		        box.toggle();
		        button.toggleClass('active');
		    });
		    form.mouseup(function() { 
		        return false;
		    });
		    $(this).mouseup(function(login) {
		        if(!($(login.target).parent('#loginButton').length > 0)) {
		            button.removeClass('active');
		            box.hide();
		        }
		    });
		});
		
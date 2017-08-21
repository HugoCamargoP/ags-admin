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
	  	    },2000);
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
	  	    },2000);
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
	if(parseInt(limitetalla) < 4)
	{
		var metelo = '<div class="col-xs-12">'+
		  '<div class="form-group col-xs-12 col-sm-3">'+
		    '<label for="pwd"><i class="fa fa-barcode"></i><b>SKU:</b></label>'+ 
		    '<input ng-required="true" ng-model="newformssize.sku" class="form-control form-control-min" type="text" />'+
		  '</div>'+
		  '<div class="form-group col-xs-12 col-sm-3">'+
		    '<label for="pwd"><i class="fa fa-object-group"></i><b> <s:message code="admin.size"/>:</b></label>'+ 
		    '<select name="" id="" ng-model="newformssize.size" class="form-control form-control-min" ng-required="true">';
			for ( var a in tallasperronas) {

			    metelo = metelo + '<option value="'+tallasperronas[a].id+'">'+tallasperronas[a].name+'</option>';
			}
		    metelo = metelo + '</select>'+
		  '</div>'+
		  '<div class="form-group col-xs-12 col-sm-3">'+
		    '<label for="pwd"><i class="fa fa-usd"></i><b> <s:message code="admin.price"/>:</b></label>'+
		    '<input  ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/"  ng-model="newformssize.price"  ng-required="true" class="form-control form-control-min" type="text" />'+
		  '</div>'+
		  '<div class="form-group col-xs-12 col-sm-3">'+
		    '<label for="pwd"><i class="fa fa-filter"></i><b> <s:message code="admin.stock"/>:</b></label>'+
			'<input type="number"  ng-required="true" ng-model="newformssize.stock" class="form-control form-control-min" type="text" />'+
		  '</div>'+
		'</div>';
		    
		   metelo ='<tr>'+
				  		'<td>'+
						    '<input ng-required="true" ng-model="newformssize.sku" class="form-control form-control-min" type="text" />'+
						'</td>'+
				  		'<td>'+
				  		    '<select name="" id="" ng-model="newformssize.size" class="form-control form-control-min" ng-required="true">';
						for ( var a in tallasperronas) {

						    metelo = metelo + '<option value="'+tallasperronas[a].id+'">'+tallasperronas[a].name+'</option>';
						}
		  metelo = metelo + '</select>'+
						'</td>'+
				  		'<td>'+
				  		    '<input  ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/"  ng-model="newformssize.price"  ng-required="true" class="form-control form-control-min" type="text" />'+
						'</td>'+
				  		'<td>'+
				  			'<input type="number"  ng-required="true" ng-model="newformssize.stock" class="form-control form-control-min" type="text" />'+
						 '</td>'+
				  	'</tr>';
		$('.extra').append(metelo);
		limitetalla++;
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

$(document).ready(function () {
	
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
		
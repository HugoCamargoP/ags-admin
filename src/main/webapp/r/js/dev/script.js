/*Globales*/
		  var ventana_ancho;
		  var ventana_alto;
		  var born = "";
		  var mensajesglobal = 0;
		  var productosporpagina;
		  var nservice;
		  var menos;
		  var todoalazador = 0 ;
/*Globales fin*/

/*svg*/
  $("svg").click(function(){
	    alert("Lolol");
	});
/*svg*/
  
		  
function refnevo()
{
	angular.element(  document.querySelector('#ref')).scope().checkCar();
}		  

function updateCarWish(us)
{
	angular.element(  document.querySelector('#car')).scope().cuantosProd(us,2);
	angular.element(  document.querySelector('#wish')).scope().cuantosProd(us,1);
}
		  
function msjerror(error)
	{
	  	mensajesglobal++;
	  	$("#mensajes").append('<div id="mensaje'+mensajesglobal+'" class="alert alert2 alert-danger text-center">'+error+'</div>');
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
	  	$("#mensajes").append('<div id="mensaje'+mensajesglobal+'" class="alert alert2 alert-success text-center">'+exito+'</div>');
	  	setTimeout(function() {
	  	       eliminaThis();
	  	    },2000);
	}
	
function expande()
{
	$("button.navbar-toggle").click();
}

function active(a,b)
{
    $('.'+a).removeClass("active");
    $('.'+a+"-"+b).addClass("active");
    //console.log(a+'.'+b);
}

$(function()
	{
		$('[data-toggle="popover"]').popover();
		
		$(".born").datepicker({
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			yearRange: "-100:+0",
		    changeYear: true,
			onClose: function (selectedDate)
			{
				born = selectedDate;
			}
		});
	
	//Slider propio responsivo
	
	//console.log($('.arrow-slider > *').length);

	//fin slider arrow
		  var toggles = document.querySelectorAll(".c-hamburger");
	
		  for (var i = toggles.length - 1; i >= 0; i--) {
		    var toggle = toggles[i];
		    toggleHandler(toggle);
		  };
	
		  function toggleHandler(toggle) {
		    toggle.addEventListener( "click", function(e) {
		      e.preventDefault();
		      (this.classList.contains("is-active") === true) ? this.classList.remove("is-active") : this.classList.add("is-active");
		    });
		  }
		 
	    $.get = function(key) {  
	        key = key.replace(/[\[]/, '\\[');  
	        key = key.replace(/[\]]/, '\\]');  
	        //var pattern = "[\\?&]" + key + "=([^&#]*)";  
	        var pattern = "home";  
	        var regex = new RegExp(pattern);  
	        var url = unescape(window.location.href);  
	        var results = regex.exec(url);  
	        
	        var pattern1 = "products";  
	        var regex1 = new RegExp(pattern1);  
	        var url1 = unescape(window.location.href);  
	        var results1 = regex1.exec(url1);  

        	console.log('antes de entrar al return');
        	
	        if (results === null && results1 === null) {  
	        	console.log('entro al if yes');
	        	return "no";
	        	//return null;  
	        } else { 
	        	console.log('entro al esle no');
	        	return "yes";
	            //return results[1]; 
	        } 
	    }   
	    var a = $.get("m");
	    //console.log($.get("m")+"Esto tiene la la funcion");
		re();
		
		$(window).resize(function (){
			re();
			});
		
		
		$('#lang').html(zcookie);
		
		$('.img-home').hover(function (){
			$('.visornewimg').html('<img src="'+$(this).attr('src')+'" alt="" class="img-responsive" />');
		});

		 $('.container-add').hover(
					
		           function () {
		        	   $(this).find('.visor > * > img').addClass('hoveropacidad');
		        	   $(this).find('.visor > * > img').addClass('scale');
		        	   $(this).find('.dataimg').addClass('dataimg1');
		           }, 
					
		           function () {
		        	   $(this).find('.visor > * > img').removeClass('hoveropacidad');
		        	   $(this).find('.visor > * > img').removeClass('scale');
		        	   $(this).find('.dataimg').removeClass('dataimg1');
		           }
		        );

		 $('.sociales').hover(
					
		           function () {
		        	   $(this).find('.imgsocial').addClass('hidden');
		        	   $(this).find('.imgsocial1').removeClass('hidden');
		           }, 
					
		           function () {
		        	   $(this).find('.imgsocial').removeClass('hidden');
		        	   $(this).find('.imgsocial1').addClass('hidden');
		           }
		        );
		 

		 $("#slideshow1").fadeSlideShow();
		 

		 $( document ).scroll(function() {
			 	  if($( document ).scrollTop() > (ventana_alto - 300))
			 		  {
			 			//$('#imglogo').addClass('imglogo2');
			 	  		$('.navbarstylo').addClass('navbarstylo2');
			 		  }
			 	  else
			 		  {
			 	  		//$('#imglogo').removeClass('imglogo2');
			 	  		$('.navbarstylo').removeClass('navbarstylo2');
			 		  }
	
					 if($( document ).scrollTop() > 1)
						 {
					 		   	$(".logo").addClass('logo1');
						 }
					 else
						 {
					 		   	$(".logo").removeClass('logo1');
						 }
					 
					 if (ventana_ancho < 768 || a != "yes") 
					 	{
				 			//$('#imglogo').addClass('imglogo2');
				 	  		$('.navbarstylo').addClass('navbarstylo2');
				 		   	$(".logo").addClass('logo1');
				 	  	} 
					 
		 });

	 	  if($( document ).scrollTop() > 300)
	 		  {
	 			//$('#imglogo').addClass('imglogo2');
	 	  		$('.navbarstylo').addClass('navbarstylo2');
	 		  }
	 	  
		 if (ventana_ancho < 768 || a != "yes") 
		 	{

	 		   	$(".logo").addClass('logo1');
	 			//$('#imglogo').addClass('imglogo2');
	 	  		$('.navbarstylo').addClass('navbarstylo2');
	 	  	}
		 
         $('#ei-slider').eislideshow();
         
         $('.responsive').slick({
        	  dots: false,
        	  infinite: true,
        	  speed: 200,
        	  autoplay:true,
        	  slidesToShow: 8,
        	  slidesToScroll: 1,
        	  responsive: [
        	    {
        	      breakpoint: 600,
        	      settings: {
        	        slidesToShow: 3,
        	        slidesToScroll: 1
        	      }
        	    },
        	    {
        	      breakpoint: 480,
        	      settings: {
        	        slidesToShow: 2,
        	        slidesToScroll: 1
        	      }
        	    }
        	  ]
        	});
	});

function re() 
	{
		ventana_ancho = $(window).width();
		ventana_alto = $(window).height();
		//console.log(ventana_alto);
		$(".ancho").css('width', ventana_ancho);
		$(".alto").css('height', ventana_alto);
		$(".fullscreen").css('width', ventana_ancho);
		$(".fullscreen").css('height',ventana_alto);
		tam('visor','modeloyetibera');
		//console.log($("#ei-slider").width());
		$(".ei-slider-large  img").css('width', $("#ei-slider").width());	
		$(".min-height").css('min-height',(ventana_alto-500));
		$(".max-height").css('max-height',(ventana_alto-500));
		$(".anchomas").css('width',(ventana_ancho+310));
		
	}


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
	}

function tam(who,target)
{
	$("."+target).css('max-height', $("."+who).height());	
}


function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

var zcookie;
function checkCookie(param) {
    var cookie = getCookie(param);
    if (cookie != "" && cookie != null) 
    	{
        	if(param == 'lang')
	            {
		    		if(cookie == 'en')
	            	{zcookie = '<img src="/yetibera/r/img/flags/UK.png" alt="" />';}
		    		else if(cookie == 'fr')
	            	{zcookie = '<img src="/yetibera/r/img/flags/France.png" alt="" />';}
		            else
		            	{zcookie = '<img src="/yetibera/r/img/flags/Spain.png" alt="" />';}	
	            }
    	} 
    else
    	{
        	if(param == 'lang') 
            	{
            		setCookie("lang", 'en', 1);
            		zcookie = '<img src="/yetibera/r/img/flags/UK.png" alt="" />';
            	}
    	}
}

function changeCookie(param)
{
	setCookie("lang", param , 1);
	location.reload();
}


checkCookie('lang');


function validaregistro()
{
	var status = false;
	if($("#password").val() == $("#repass").val() )
	{
		status = true;
	}
	else
	{
		$("#repass").val('');
		$("#password").val('');
		$("#password").focus();
		msjerror("las contraseñas no coinciden");
	}
	return status;
}

function direcciones(a)
{
	switch(a){
	case 1:
		a = 25;
		$('#countries').focus();
		break;
	case 2:
		a = a*25;
		break;
	case 3:
		a = a*25;
		break;
	}
	$('.fase1').css('width',a+'%');
}

if( $(window).width() < 768 )
{
	productosporpagina = 6;
	menos = 3;
}
else
{
	menos = 7;
	productosporpagina = 9;
}
/*
Paypal

https://www.sandbox.paypal.com/checkoutnow?locale.x=en_US&version=4&env=sandbox&uid=0261ed7ab0&token=PAY-6XK41897MM1535148LE7KUOQ&xcomponent=1#/checkout/login
https://www.sandbox.paypal.com/checkoutnow?locale.x=es_MX&version=4&env=sandbox&uid=c244beabb9&token=PAY-3MK37740M2746880XLE7KVTA&xcomponent=1#/checkout/login
*/

	        paypal.Button.render({

	            env: 'sandbox', // sandbox | production

	            style: { size: 'responsive' },
	            // PayPal Client IDs - replace with your own
	            // Create a PayPal app: https://developer.paypal.com/developer/applications/create
	            client: {
	                //sandbox:    'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R',
	                sandbox:	'AUXQHyLkVRKv_XKtaly04VlR3hwWxAN9AmnTpn9F5QfNVN2MU_dYTYgMD0OsS6ARVnHq4p80Avrual57',
	                production: '<insert production client id>'
	            },

	            // Show the buyer a 'Pay Now' button in the checkout flow
	            commit: true,

	            // payment() is called when the button is clicked
	            payment: function(data, actions) {

	            	angular.element(  document.querySelector('#n')).scope().pago();
	                // Make a call to the REST api to create the payment
	                return actions.payment.create({
	                    transactions: [
	                        {
	                            amount: { total: todoalazador, currency: 'USD' }
                            	//amount: { total: '0.01', currency: 'USD' }
	                        }
	                    ]
	                });
	            },

	            // onAuthorize() is called when the buyer approves the payment
	            onAuthorize: function(data, actions) {

	                // Make a call to the REST api to execute the payment
	                return actions.payment.execute().then(function() {
	                    window.alert('Payment Complete!');
	                });
	            }

	        }, '#paypal-button-container');
	          
		  
 /* 
Paypal
 */
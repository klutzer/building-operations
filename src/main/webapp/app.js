new Vue({
  el: '#app',
  data: {
    teste: 'Mas olha só que coisa!',
	customers: undefined
  },
  methods: {
	  alertName: function() {
		  alert('Seu nome é ' + this.teste);
	  },
	  getCustomers: function() {
		  this.$http.get('api/customer').then(response => {
			  this.customers = response.body;
			  this.teste = 'Executei!';
		  }, response => {
			  this.customers = 'Error!'
		  });
	  }
  }
})
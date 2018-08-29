new Vue({
  el: '#app',
  data: {
    teste: 'Mas olha só que coisa!'
  },
  methods: {
	  alertName: function() {
		  alert('Seu nome é ' + this.teste);
	  }
  }
});
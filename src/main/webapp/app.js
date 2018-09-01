Vue.http.options.root = '/api';

new Vue({
	el: '#app',
	data: {
		dadosCalculo: {},
		veiculos: [],
		resultado: null,
		error: false
	},
	methods: {
		getVeiculos() {
			this.$http.get('veiculo').then(response => {
				this.veiculos = response.body
			})
		},
		calcular() {
			this.$http.post('custoTransporte', this.dadosCalculo).then(response => {
				this.resultado = response.body.resultado
				this.error = false;
			}, response => {
				this.resultado = response.body.message
				this.error = true;
			})
		}
	},
	beforeMount() {
		this.getVeiculos()
	}
})
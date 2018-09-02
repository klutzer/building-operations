Vue.http.options.root = '/api';

new Vue({
	el: '#app',
	data: {
		calcData: {},
		vehicles: [],
		result: null,
		error: false
	},
	methods: {
		getVehicles() {
			this.$http.get('veiculo').then(response => {
				this.vehicles = response.body
			})
		},
		calculate() {
			this.$http.post('custoTransporte', this.calcData).then(response => {
				this.result = response.body.result
				this.error = false;
			}, response => {
				this.result = response.body.message
				this.error = true;
			})
		}
	},
	beforeMount() {
		this.getVehicles()
	}
})
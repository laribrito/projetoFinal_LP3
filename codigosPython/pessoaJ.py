from conta import Conta

class PessoaJ(Conta):
	def __init__(self, cnpj, razao_social, tel, endereco):
		super().__init__(tel, endereco)
		self.__cnpj = cnpj
		self.__razao_social = razao_social
		self.__limite = -50000
		self.__tipo = "J"

	def getIdentificador(self):
		return self.__cnpj

	def getNome(self):
		return self.__razao_social

	def getLimite(self):
		return self.__limite

	def getTipo(self):
		return self.__tipo

	def setNovoLimite(self, nvLimite):
		self.__limite = nvLimite
		
	def testarLimite(self):
		if self.getSaldo() >= 0:
			self.__limite = -50000
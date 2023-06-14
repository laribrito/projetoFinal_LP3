from conta import Conta

class PessoaF(Conta):
	def __init__(self, cpf, nomeTitular, tel, endereco):
		super().__init__(tel, endereco)
		self.__cpf = cpf
		self.__nomeTitular = nomeTitular
		self.__limite = -1000
		self.__tipo = "F"

	def getIdentificador(self):
		return self.__cpf

	def getNome(self):
		return self.__nomeTitular

	def getLimite(self):
		return self.__limite

	def getTipo(self):
		return self.__tipo

	def setNovoLimite(self, nvLimite):
		self.__limite = nvLimite
		
	def testarLimite(self):
		if self.getSaldo() >= 0:
			self.__limite = -1000
			
			



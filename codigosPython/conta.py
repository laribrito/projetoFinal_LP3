#Documento para a classe conta, que abriga 
#atributos comuns entre as classes de cliente


class Conta:
	__numeroLivre = 1011
	def __init__(self, tel, endereco): 
		self.__numero = Conta.__numeroLivre
		Conta.__numeroLivre += 1
		self.__tel = tel
		self.__endereco = endereco
		self.__saldo = 0
		self.__extrato = []
		
	def getSaldo(self):
		return self.__saldo
	
	def getNumero(self):
		return self.__numero
		
	def getEndereco(self):
		return self.__endereco
		
	def getTel(self):
		return self.__tel
		
	def getExtrato(self):
		return self.__extrato

	def sacar(self, valor):
		limite = self.getLimite()
		subtracao = self.__saldo - valor

		if valor <= 0:
			return 1    # Código de erro pra valor negativo
		elif valor > self.__saldo and limite <= subtracao:
			print("\n\nCrédito especial!")
			op = input("\nVocê não tem saldo suficiente, mas pode entrar no crédito especial. Deseja realizar a operação? [S] ou [N]\n > ")
			if op.lower() == "s": 
				self.__saldo -= valor
				saque = ["Saque", valor]
				self.__extrato.append(saque)
				nvLimite = self.getLimite() - self.__saldo
				self.setNovoLimite(nvLimite)
				return 0
			else:
				return 3 
		elif valor > self.__saldo:
			return 2    # Código de erro pra saldo insuficiente e não pode entrar no crédito especial
		else:
			self.__saldo -= valor
			saque = ["Saque", valor]
			self.__extrato.append(saque)
			return 0    # Código de erro pra sucesso - ele tem saldo para sacar
				

	def depositar(self, valor):
		if valor > 0:
			self.__saldo += valor
			deposito = ["Depósito", valor]
			self.__extrato.append(deposito)
			return 0  # Código de erro pra sucesso
		else:
			return 1 # Código de erro pra valor negativo
		
	def transferir(self, valor, destino):
		limite = self.getLimite()
		subtracao = self.__saldo - valor

		if valor <= 0:
			return 1    # Código de erro pra valor negativo
		elif valor > self.__saldo and limite <= subtracao:
			print("\n\nCrédito especial!")
			op = input("\nVocê não tem saldo suficiente, mas pode entrar no crédito especial. Deseja realizar a operação? [S] ou [N]\n > ")
			if op.lower() == "s": 
				self.__saldo -= valor
				destino.__saldo += valor

				transferencia = ["Transferência para", valor, destino.getNumero()]
				self.__extrato.append(transferencia)

				transferencia = ["Transferência de", valor, self.getNumero()]
				destino.__extrato.append(transferencia)
				
				nvLimite = self.getLimite() - self.__saldo
				self.setNovoLimite(nvLimite)
				return 0
			else:
				return 3 
		elif valor > self.__saldo:
			return 2    # Código de erro pra saldo insuficiente e não pode entrar no crédito especial
		else:
			self.__saldo -= valor
			#deposito na conta destino
			destino.__saldo += valor

			transferencia = ["Transferência para", valor, destino.getNumero()]
			self.__extrato.append(transferencia)
			transferencia = ["Transferência de", valor, self.getNumero()]
			destino.__extrato.append(transferencia)
			return 0  # Código de erro pra sucesso


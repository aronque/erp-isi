# Bem vindo!

# Para poder rodar o sistema, siga os seguintes passos:

## Clonar o repositório ou baixar o .zip por meio do botão <> Code - verde - acima deste README

## Backend:
Obter o Docker Desktop (em caso de sistema Windows) ou a engine do Docker em seu sistema. Os passos para instalação se encontram em https://docs.docker.com/engine/install/.
Com o Docker devidamente instalado, deve-se rodar o comando **docker-compose up --build**. 
Após finalização da configuração dos containeres, o backend e banco de dados já estarão disponíveis para utilização em host local, através da porta **8080**

## Frontend:
Seguir as instruções especificadas em [frontend](./frontend/README.md) 

##
Após a inicialização da aplicação, entre no sistema utilizando o usuário de email: adm@gmail.com e senha: admisierp. Após adentrar o sistema, é possível inserir um usuário próprio fornecendo o dado de um email REAL, onde chegará em sua caixa de entrada as informações confirmando a criação do usuário. As funcionalidades que utilizam email podem demorar um pouco (até 5min) para adentrarem o email fornecido, por conta do servidor de despache dos emails, então conferir de tempos em tempos e conferir também a pasta de spam. As funcionalidades estarão disponíveis de acordo com o mapeamentos abaixo:

### Usuario ADM E GERENTE: acesso total

### Usuario ESTOQUE: acesso total MENOS criação de usuários

### Usuário COMUM: somente consulta de dados e criação de pedidos de retirada do estoque.

## Usuários para acessar a aplicação

### Comum: 
  email: joao.silva@gmail.com
  senha: joao123

### ADM:
  email: adm@gmail.com
  senha: admisierp

### Estoque
  email: alberto@empresa.com
  senha: alberto123

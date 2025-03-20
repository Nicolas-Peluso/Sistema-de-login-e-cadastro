## Projeto simples de cadastro e login de usuario. sera feita:

* validação por email (no exemplo vou usar o mailtrap)
* criptografia da senha do usuário
* implementação de JWT

projeto consiste em uma api, que no momento permite o cadastro com email, senha e nome, e um login
nesse projeto aplico conceitos como injeção de dependencias.
tambem aplico boas praticas como: Baixo acoplamento, separação de responsabilidades, objetos pequenos (para serem coletados 
pelo GC mais rapidamente)
Vou usar o JPA para manipular o banco. O banco é mysql.
Esse é meu primeiro projeto Spring, estou aprendendo pela documentação e por tutoriais.

## endpoints
* /user
  * requisições post sao tratadas como cadastro recebe um json com email, nome e senha, é feito uma validação de enail e senha com regexp. o retorno é um json.

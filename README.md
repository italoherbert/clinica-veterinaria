# Sistema de Clinica Veterinaria

O sistema de clínica veterinária foi compilado em Java 1.8 com Spring Boot 2.5.0 no backend e Angular 12 no frontend. 
O servidor tomcat embutido na aplicação roda na porta 8080.

# O banco de dados

O banco de dados utilizado foi o H2 e os dados são persistidos em memória. Logo, após reiniciar ou parar a aplicação, os dados são perdidos.
O arquivo import.sql é carregado e executado com a inicialização do spring boot para registrar alguns veterinários, tutores e animais.

O banco de dados está com a seguinte configuração:

driver=org.h2.Driver

url=jdbc:h2:mem:testdb

username=sa

password=

Foi criada uma tabela para registrar pessoas, chamada de pessoa. Isso porque os campos do tutor e do veterinário são quase iguais. Foi configurada uma 
relação de um-para-um entre as tabelas: veterinario e pessoa, tutor e pessoa. 

Uma sujestão seria criar um relacionamento de muitos para muidos entre a tabela animal e a tabela tutor para o caso do mesmo tutor 
ser tutor de mais de um animal. No entanto, essas tabelas foram deixadas conforme a expecificação: um-para-muitos.

# Executando o projeto

Para rodar o projeto basta fazer clone do projeto, navegar até a pasta raiz e, então, executar o seguinte comando:

java -Dfile.encoding=UTF-8 -jar clinica-veterinaria-1.0.war

A opção -Dfile.encoding é necessária para solucionar um problema de codificação.

# Executando o swagger

Para acessar o swagger, após executado o projeto, utilize a seguinte url: http://localhost:8080/swagger-ui.html

# Executando o h2 console

Para acessar o h2-console, após executado o projeto, utilize a seguinte url: http://localhost:8080/h2-console

# Limitações

Não foi utilizada solução de paginação, pois, achei desnecessário para um pequeno projeto.
Os comboboxes são carregados com todos os registros do banco de dados da tabela correspondente. Não houve otimização quanto a isso, achei desnecessário!


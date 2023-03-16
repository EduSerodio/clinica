<h1> SPRING BOOT </h1>

Realizei a criação do projeto pelo "https://start.spring.io/" usando a ferramenta Maven, e adicionando as dependencias:

 - Spring Boot DevTools - Que fornece reinicializações rápidas de aplicativos, LiveReload e configurações para uma 
 experiência de desenvolvimento aprimorada.
 
 - Lombok - Biblioteca de anotações java que ajuda a reduzir o código clichê
 
 - Spring Web - Cria aplicativos Web, inclui restful, usando Spring MVC, usa o Apache Tomcat como o contêiner 
 incorporado padrão
 
 - h2 Database - Fornece um banco de dados na memória rápida que suporta API JDBC e acesso R2DBC, com uma pegada pequena
 (2 MB). Suporta modos incorporados e de servidor, bem como um aplicativo de console baseado em navegador.
 
 Foi criado o projeto para uma getão de usuários adicionando os packpages necessários, foi criado três métodos no serviço: um que cadastra
 usuários, um que retorna todos os usuários e o outro que exclui o usuário de acordo com o seu ID. (Inicializando o projeto com três registros no
 banco de dados H2).

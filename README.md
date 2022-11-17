mvn spring-boot:run -Drun.arguments=--customArgument=custom

Examples:

    file:
    
     mvn spring-boot:run -Drun.arguments=--type.path=/home/marialutteur/code/alfa-test/src/main/resources/example.xml
     
    classpath:
    
     mvn spring-boot:run -Drun.arguments=--type.path=classpath:example.xml
     
    url:
    
     mvn spring-boot:run -Drun.arguments=--type.url=file:/home/marialutteur/code/alfa-test/src/main/resources/example.xml
     
1. http://localhost:8080/load - загрузить xml
2. http://localhost:8080/test - получить id итемов
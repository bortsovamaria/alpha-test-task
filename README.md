Программные сущности^
Основные сущности приложения - Item и коробка Box:
- Ящики могут быть пустыми или содержать предметы или другие ящики
- У каждого ящика и предмета есть id
- ID какого-либо предмета и какого-либо ящика могут совпадать, но в совокупности предметов они уникальны (как и в совокупности
ящиков)
- Предметы могут не иметь цвета
- Предметы могут быть не в ящике
- Вложенность ящиков может быть любой;

Входные данные

На вход в виде параметра командной строки приложению передается ссылка на XML-файл, в котором задано взаимное положение предметов
(Item) и ящиков (Box).

Пример такого файла:

<?xml version="1.0" encoding="UTF-8"?>
<Storage>
    <Box id="1">
        <Item id="1"/>
        <Item color="red" id="2"/>
    <Box id="3">
        <Item id="3" color="red" />
        <Item id="4" color="black" />
    </Box>
    <Box id="6"/>
        <Item id="5"/>
    </Box>
    <Item id="6"/>
</Storage>

Ссылка имеет следующий формат: type:path, где:
    type - тип ссылки
    path - путь к файлу
Ссылка определяет источник, из которого загружаются данные в XML-формате.
Тип ссылки (type):
    file (внешний файл)
    classpath (файл в classpath)
    url (URL)
    
После загрузки файла приложение работает, как REST-сервис, который возвращает id предметов заданного цвета (Color),
содержащиеся в ящике c заданным идентификатором (Box) с учётом того, что в ящике может быть ещё ящик с предметами требуемого цвета.

Например, на POST-запрос с телом запроса в JSON вида:
    POST /test HTTP/1.1
    Host: localhost
    Accept: application/json
    Content-Type:application/json
    Content-Length: 25
    {"box":"1","color":"red"}
для вышеприведённого XML ответ вида:
    HTTP/1.1 200
    Content-Type: application/json;charset=UTF-8
    Transfer-Encoding: chunked
    Date: Mon, 01 Sep 2019 12:00:26 GMT
    [2,3]

Запуск приложения:

mvn spring-boot:run -Drun.arguments=--customArgument=custom

Примеры:

    file:
    
     mvn spring-boot:run -Drun.arguments=--type.path=file:/home/marialutteur/code/alfa-test/src/main/resources/example.xml
     
    classpath:
    
     mvn spring-boot:run -Drun.arguments=--type.path=classpath:example.xml
     
    url:
    
     mvn spring-boot:run -Drun.arguments=--type.path=url:file:/home/marialutteur/code/alfa-test/src/main/resources/example.xml
     
1. http://localhost:8080/load - загрузить xml (GET) - параметры задаются в аргументах

2. http://localhost:8080/test - получить id итемов (POST) - request содержит id коробки, в котором содержатся итемы, и цвет итема.

# CarShowRoomApp
Приложение для автосалона, написанное на языке программирования Java.
Функционал:
Пользователь
1) Имеет возможность выбрать и заказать автомобиль, посмотреть свой список уже заказанных автомобилей.
2) Имеет возможность увидеть весь список автомобилей, быстрый поиск по марке или расширенный поиск по параметрам.
Менеджер
1) Имеет возможность отредактировать данные об автомобиле, добавить новый автомобиль.
Админ
1) Имеет все права менеджера + может просматривать список пользователей и редактировать его.


Аккаунты для тестирования:
1) Admin - login: admin@a.ua password: 12qwaszx
2) Manager - login: manager@a.ua password: 12qwaszx
3) User - login: user@a.ua password: 12qwaszx

Создано REST-API при помощью JWT токенов. 

/api/login для логина.
Форма логина:
{ 
  "email" : "value",
  "password" : "value"
}


/api/signup для регистрации

Форма регистрации {
	"email" : "bill@ya.ua",
	"password" : "12qwaszx",
	"phone_number" : "89261234567",
	"firstname" : "Sanya",
	"lastname" : "Programmist" 
	}


/api/admin/** для админа
- /api/admin/users & /api/admin/users/{id} - выводит список юзеров или юзера по ид соответственно.
- /api/admin/change-role/{id}/{role} - меняет роль юзеру, нужно указать ид юзера и имя роли (MANAGER,ADMIN,USER).

/api/operation/** для менеджера
- /api/operation/cars/new - добавить новый автомобиль
- /api/operation/cars/edit/{id} - изменить параметры автомобиля по ид
- /api/operation/cars/delete/{id} - удалить автомобиль по ид.

/api для юзера
- /api/cars - выводит список всех автомобилей
- /api/cars/{maker} - выводит автомобили по названию производителя. Например - BMW.
- /api/cars/adv-search - расширрений поиск по параметрам: 
Форма поиска по параметрам: 
{
  "fromYearParam" : "value",
  "toYearParam" : "value",
  "fromPriceParam" : "value",
  "toPriceParam" : "value",
  "colorNameParam" : "value",
  "fromHpParam" : "value",
  "toHpParam" : "value"
}

- /api/cars/to-order/{carId}/{enhId} - для заказа автомобиля по ид, и по ид улучшения (0 - default).

Используемые технологии:
-WebLogic WebServer
-PostgreSQL database
-JDBC
-Maven
-Spring MVC
-Spring Security
-JavaScript, Ajax, jQuery
-Bootstrap, JSP
-Slf4j


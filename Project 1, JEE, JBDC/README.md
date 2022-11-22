# Hotel booking.

Application provides an opportunity to book apartment if you are guest, and either manage users requestUsers if you are manager.

## Feature
Available functionality depend on the user's role (guest, manager). Implemented layered architecture with design patterns are: Singelton, Command, Proxy, Factory, Builder.
Used technologies : Maven, Tomcat, JSP, JSTL, MySQL, JDBC, jBCrypt, JUnit, Mockito.

## Guest:
- Placing requestUser with ability of choosing check-in date, check-out date, quantity persons, apartment category, transfer and menu.
- View the list of confirmed requestUsers.
- View photo of apartments.
- Log in.
- Sign Up.
- Change interface language RU<->EN.

## Manager:
Same features as guest with ability to:
- View list of requestUsers.
- Update requestUser.
- Delete requestUser.
- Select list of only new requestUsers.
- View guest data.
- Assemble list of offers for each requestUser.
- Select suitable apartment for each requestUser.
- View offer list.
- Update offer statuses.
- Update offer price.
- View apartment list.
- Add new apartment.
- Update apartment data.
- Delete apartment.
- View timesheet of booked apartments.
- Update guest data and VIP/NonGrata/Manager statuses.
- Pagination on each table.
- Sorting on each table.
- Search on each table.




